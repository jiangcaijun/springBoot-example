package com.backstage.zeus.api;

import com.backstage.zeus.base.clean.InsertWebLog2MysqlThread;
import com.backstage.zeus.base.clean.WebLogProcess2;
import com.backstage.zeus.base.models.PageDo;
import com.backstage.zeus.base.models.Weblog;
import com.backstage.zeus.base.service.ISpecialManagement;
import com.backstage.zeus.base.service.IWeblogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZeusApiApplicationTests {

	@Resource
	private ISpecialManagement iSpecialManagement;

	@Resource
	private IWeblogService iWeblogService;

	private	static WebLogProcess2 webLogProcess2 = new WebLogProcess2();

	private static List<Weblog> weblogList = new ArrayList<Weblog>();

	@Test
	public void queryZcdc() {
		PageDo pageDo = new PageDo(10, 0);
		iSpecialManagement.findDataGrid(pageDo);
		System.out.println("获取到的数据条数" + pageDo.getTotal());
		System.out.println(pageDo.getRows());
	}

	@Test
	public void insertWeblog() {
		Weblog weblog = new Weblog();
		System.out.println("结果：" + iWeblogService.insert(weblog));
	}

	@Test
	public void analysisWebLogAndInsert2Mysql() {

		String path = "";

		path = "D:\\Program Files\\DBAPPSecurity\\YJCZToolBox\\YJCZToolBox\\null\\shanghai.kltong.com_access_20.log"; //4M  sum:20142  time:17855  批量插入time:11044 插入数据库部分多线程  time:8055

//        path = "D:\\Program Files\\DBAPPSecurity\\YJCZToolBox\\YJCZToolBox\\null\\shanghai.kltong.com_access_19.log";//50M  sum:218867 	 time:157496 批量插入time:93456 插入数据库部分多线程 time:79836


		// memoryMappedFile	 sum:893201  	 time:203302  读取并weblogVO解析，计算行号，并输出
		// memoryMappedFile	 sum:0 	         time:111996  读取并weblogVO解析，但不计算行号，不输出（包括行号）
		// memoryMappedFile	 sum:893201 	 time:112484  读取并weblogVO解析，计算行号，但不输出（包括行号）
//        path = "D:\\安恒信息\\天鉴事业部\\录屏20171010\\xx事件\\web日志\\shanghai.kltong.com_access.log"; //200M   sum:893201 time:113871 批量插入 time:332762
		// memoryMappedFile	 sum:4410631 	 time:6523  读取不weblogVO解析，计算行号，但不输出（包括行号）
//        行数：4410631
//        memoryMappedFile	 sum:4410631 	 time:39911  读取不weblogVO解析，计算行号，但只输出行号
//        行数：4410631
//        memoryMappedFile	 sum:4410631 	 time:616414 读取并weblogVO解析，计算行号，但只输出行号
//        memoryMappedFile	 sum:0 	 time:539426  读取并weblogVO解析，计算行号，但不输出（包括行号）
//        path = "D:\\安恒信息\\天鉴事业部\\录屏20171010\\开联通\\shnginx\\shanghai.kltong.com_access.log"; //900M  4410631  批量插入 time:1765466

		memoryMappedFile(path);
	}

	private void memoryMappedFile(String path){

		try {
			RandomAccessFile memoryMappedFile = null;

			memoryMappedFile = new RandomAccessFile(path,"r");

			int size =(int)memoryMappedFile.length();
			MappedByteBuffer out = memoryMappedFile.getChannel().map(FileChannel.MapMode.READ_ONLY,0,size);

			long start = System.currentTimeMillis();
			//要根据文件行的平均字节大小来赋值
			final int extra = 200;
			int count = extra;
			byte[] buf = new byte[count];
			int j=0;
			char ch ='\0';
			boolean flag = false;
			int i =0;
			while(out.remaining()>0){
				byte by = out.get();
				ch =(char)by;
				switch(ch){
					case '\n':
						flag = true;
						break;
					case '\r':
						flag = true;
						break;
					default:
						buf[j] = by;
						break;
				}
				j++;
				//读取的字符超过了buf 数组的大小，需要动态扩容
				if(flag ==false && j>=count){
					count = count + extra;
					buf = copyOf(buf,count);
				}
				if(flag){
					//这里的编码要看文件实际的编码
					String line = new String(buf,"utf-8");
					weblogList.add(webLogProcess2.analyze(line));
					if(weblogList.size() > 500){
//						iWeblogService.insertBatch( weblogList);
						InsertWebLog2MysqlThread.analysis(weblogList,iWeblogService);
						weblogList = new ArrayList<Weblog>();
						System.gc();
					}
					flag = false;
					buf = null;
					++i;
					count = extra;
					buf = new byte[count];
					j =0;
				}

			}
			//处理最后一次读取
			if(j>0){
				String line = new String(buf,"utf-8");
				weblogList.add(webLogProcess2.analyze(line));
//				iWeblogService.insertBatch( weblogList);
				InsertWebLog2MysqlThread.analysis(weblogList,iWeblogService);
				weblogList = null;
			}

			long end = System.currentTimeMillis();
			System.out.println("解析文件" + "\t 总行数sum:" + i + " \t time:" + (end-start));
			memoryMappedFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		System.out.println("插入到数据库" + "\t 总行数sum:"  + iWeblogService.queryCount());
	}
	//扩充数组的容量
	public static byte[] copyOf(byte[] original,int newLength){
		byte[] copy = new byte[newLength];
		System.arraycopy(original,0,copy,0,Math.min(original.length,newLength));
		return copy;
	}

	private void insert2Mysql(String line){
		iWeblogService.insert( webLogProcess2.analyze(line));
	}

	private void insertBatch2Mysql(String line){
		weblogList.add(webLogProcess2.analyze(line));
		iWeblogService.insertBatch( weblogList);
	}

}