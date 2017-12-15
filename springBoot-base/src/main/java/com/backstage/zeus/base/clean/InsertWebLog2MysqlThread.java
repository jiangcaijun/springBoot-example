package com.backstage.zeus.base.clean;

import com.backstage.zeus.base.models.Weblog;
import com.backstage.zeus.base.service.IWeblogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * webshell 相关请求与调用
 * Created by jiangcaijun on 2017/9/5.
 */
public class InsertWebLog2MysqlThread implements Runnable{

    private static final Log logger = LogFactory.getLog(InsertWebLog2MysqlThread.class);

    private IWeblogService iWeblogService;

    private List<Weblog> weblogList;

    public InsertWebLog2MysqlThread( List<Weblog> weblogList,IWeblogService iWeblogService) {
        this.weblogList = weblogList;
        if(this.iWeblogService == null){
            this.iWeblogService = iWeblogService;
        }
    }

    @Override
    public void run() {
        iWeblogService.insertBatch(weblogList);

//      System.out.println("我批量插入结束");
    }

    /**
     * 留给玮姐调用的静态方法
     */
    public static void analysis(List<Weblog> weblogList,IWeblogService iWeblogService) {
        InsertWebLog2MysqlThread insertWebLog2MysqlThread = new InsertWebLog2MysqlThread(weblogList,iWeblogService);
        Thread thread = new Thread(insertWebLog2MysqlThread);
        thread.start();
    }
}