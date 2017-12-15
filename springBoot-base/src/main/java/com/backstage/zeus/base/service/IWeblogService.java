package com.backstage.zeus.base.service;

import com.backstage.zeus.base.models.Weblog;

import java.util.List;

/**
 * @Autor jiangcaijun
 * @Date 2017/11/17
 * @Time 11:23
 */
public interface IWeblogService {

    int insert(Weblog weblog);

    int insertBatch(List<Weblog> weblogList);

    int queryCount();



}
