package com.backstage.zeus.base.service.impl;

import com.backstage.zeus.base.mapper.WeblogMapper;
import com.backstage.zeus.base.mapper.ZcdcZxMapper;
import com.backstage.zeus.base.models.PageDo;
import com.backstage.zeus.base.models.Weblog;
import com.backstage.zeus.base.service.IWeblogService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Autor jiangcaijun
 * @Date 2017/11/17
 * @Time 11:24
 */
@Service
public class WeblogServiceImpl implements IWeblogService{
    private final Logger LOG = Logger.getLogger(this.getClass());
    @Resource
    private WeblogMapper weblogMapper;

    @Override
    public int insert(Weblog weblog) {
        return weblogMapper.insert(weblog);
    }

    @Override
    public int insertBatch(List<Weblog> weblogList) {
        return weblogMapper.insertBatch(weblogList);
    }

    @Override
    public int queryCount() {
        return weblogMapper.queryCount();
    }
}
