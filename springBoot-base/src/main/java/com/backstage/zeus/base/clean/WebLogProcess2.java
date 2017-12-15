package com.backstage.zeus.base.clean;


import com.backstage.zeus.base.models.Weblog;
import com.backstage.zeus.util.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebLogProcess2 {
	public Weblog analyze(String line) {

		Weblog weblog = new Weblog();
		String []logStr = line.split(" ");
		
        String ip = parseIP(line);
        weblog.setIp(ip);
        String []ips = ip.split(",");
        if(ips.length == 1){
        	weblog.setIp1(ips[0]);
        }
        else if(ips.length == 2){
        	weblog.setIp1(ips[0]);
        	weblog.setIp2(ips[1]);
        }
        
        String dateTime = parseTime(line);
        weblog.setTime(dateTime);
        String method = parseMethod(line);
        weblog.setMethod(method);
        weblog.setOriginalText((line));
        
//        weblog.setFileName(filepath.substring(filepath.lastIndexOf("/"), filepath.length()));
        
        weblog.setFileEditTime("");
        weblog.setLogFilePath("");
        weblog.setTaskid("");
        List<String> urls = parseURL(line);
        if(urls.size() == 1){
        	String url = urls.get(0);
        	String subUrl = "";
        	if(url.contains("?")) {
				subUrl = url.substring(0, url.indexOf("?"));
			} else {
				subUrl = url;
			}
        	weblog.setUrl1(subUrl);
        	// 从url1中清洗资源
        	List<String> resourcesList = parseResources(url);
        	if(resourcesList.size() == 1) {
        		String resources = resourcesList.get(0);
        		weblog.setUrl1Resources1(resources);
        		weblog.setUrl1Resources1Suffix(resources.substring(resources.lastIndexOf(".") + 1, resources.length()));
        	}
        	else if(resourcesList.size() > 1) {
        		String resources1 = resourcesList.get(0);
        		weblog.setUrl1Resources1(resources1);
        		weblog.setUrl1Resources1Suffix(resources1.substring(resources1.lastIndexOf(".") + 1, resources1.length()));
        		
        		String resources2 = resourcesList.get(1);
        		weblog.setUrl1Resources2(resources2);
        		weblog.setUrl1Resources2Suffix(resources2.substring(resources2.lastIndexOf(".") + 1, resources2.length()));
        	}
        	
        }
        else if(urls.size() > 1){
        	
        	String url1 = urls.get(0);
        	String subUrl1 = "";
        	if(url1.contains("?")) {
				subUrl1 = url1.substring(0, url1.indexOf("?"));
			} else {
				subUrl1 = url1;
			}

        	String url2 = urls.get(1);
        	String subUrl2 = "";
        	if(url2.contains("?")) {
				subUrl2 = url2.substring(0, url2.indexOf("?"));
			} else {
				subUrl2 = url2;
			}
        	
        	weblog.setUrl1(subUrl1);
        	weblog.setUrl2(subUrl2);
        	
        	List<String> resourcesList1 = parseResources(url1);
        	if(resourcesList1.size() == 1) {
        		String resources = resourcesList1.get(0);
        		weblog.setUrl1Resources1(resources);
        		weblog.setUrl1Resources1Suffix(resources.substring(resources.lastIndexOf(".") + 1, resources.length()));
        	}
        	else if(resourcesList1.size() > 1) {
        		String resources1 = resourcesList1.get(0);
        		weblog.setUrl1Resources1(resources1);
        		weblog.setUrl1Resources1Suffix(resources1.substring(resources1.lastIndexOf(".") + 1, resources1.length()));
        		
        		weblog.setUrl1Resources2(resourcesList1.get(1));
        		String resources2 = resourcesList1.get(1);
        		weblog.setUrl1Resources2(resources2);
        		weblog.setUrl1Resources2Suffix(resources2.substring(resources2.lastIndexOf(".") + 1, resources2.length()));
        	}
        	
        	List<String> resourcesList2 = parseResources(url2);
        	if(resourcesList2.size() == 1) {
        		String resources = resourcesList2.get(0);
        		weblog.setUrl2Resources1(resources);
        		weblog.setUrl2Resources1Suffix(resources.substring(resources.lastIndexOf(".") + 1, resources.length()));
        	}
        	else if(resourcesList2.size() > 1) {
        		String resources1 = resourcesList2.get(0);
        		weblog.setUrl2Resources1(resources1);
        		weblog.setUrl2Resources1Suffix(resources1.substring(resources1.lastIndexOf(".") + 1, resources1.length()));
        		
        		weblog.setUrl2Resources2(resourcesList2.get(1));
        		String resources2 = resourcesList2.get(1);
        		weblog.setUrl2Resources2(resources2);
        		weblog.setUrl2Resources2Suffix(resources2.substring(resources2.lastIndexOf(".") + 1, resources2.length()));
        	}
        	        	
        }
        
//        weblog.setTraffic(parseTraffic(logStr));
        weblog.setStatus(parseStatus(logStr));
        weblog.setLabel("");
    		
		return weblog;
	}
	
	
	private List<String> parseResources(String url){
		String  regx = "((((\\w+(-\\w+)*)|([#!@\\$\\%\\^\\*]*))(\\.)(((\\w+(-\\w+)*)|([#!@\\$\\%\\^\\*]*))*))+)";
		List<String> result = new ArrayList<String>();
		Matcher m = Pattern.compile(regx).matcher(url);
		while (m.find()) {
			result.add(m.group(1).trim());
		}
		return result;
	}
	
	private String parseMethod(String line){
		return StringUtil.getMethod(line);
	}
	
	
	/**
	 * 过滤流量
	 * @return
	 */
    private String parseTraffic(String []logStr) {
        return logStr[logStr.length - 1];
    }

    private String parseStatus(String []logStr) {
    	String result = "";
    	for(int i = 0; i < logStr.length; i++){
    		// 这里有待解决
    		if((i != (logStr.length -1)) && ("404".equals(logStr[i].trim()) || "200".equals(logStr[i].trim()))){
    			//
    			result = logStr[i];
    		}
    	}
        return result;
    }

    /**
     * 解析url
     * @return
     */
    private List<String> parseURL(String log) {
		String  regx = "(\\s/([^\\s]*)\\s)";
		List<String> result = new ArrayList<String>();
		Matcher m = Pattern.compile(regx).matcher(log);
		while (m.find()) {
			result.add(m.group(1).trim());
		}
		return result;
    }

    /**
     * 解析日期
     * @param line
     * @return
     */
    private String parseTime(String line) {
    	return StringUtil.getfirstStrTime(line);
    }

    /**
     * 解析ip
     * @param line
     * @return
     */
    private String parseIP(String line) {
    	List<String> ips = StringUtil.getIps(line);
    	String ip = "";
    	for(String str : ips){
    		ip += str.trim() + ",";
    	}
    	if(!"".equals(ip)) {
			ip = ip.substring(0, ip.lastIndexOf(","));
		}
    	return ip;
    }
    
    public static void main(String args[]) throws Exception {
    	WebLogProcess2 wp = new WebLogProcess2();
    	wp.getFileMap("I:\\123\\20161108~bj~yjj\\日志");
    }
    
	private void getFileMap(String path) throws Exception {
		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				System.out.println(("路径：" + path + " ,文件夹是空的!"));
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						getFileMap(file2.getAbsolutePath());
					} else {
						process(file2);
					}
				}
			}
		} else {
			System.out.println("路径：" + path + " ,文件不存在");
		}
	}  
	
	private void process(File file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		while(true) {
			String line = br.readLine();
			if(line == null) {
				Thread.sleep(10);
				break;
			}
			System.out.println(file.getAbsolutePath() + line);
			analyze(line);
		}
	}
	
}
