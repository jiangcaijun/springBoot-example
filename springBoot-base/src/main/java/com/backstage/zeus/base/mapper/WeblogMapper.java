package com.backstage.zeus.base.mapper;

import com.backstage.zeus.base.models.Weblog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WeblogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Weblog record);

    int insertBatch(@Param("weblogList")List<Weblog> weblogList);

    int insertSelective(Weblog record);

    Weblog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Weblog record);

    int updateByPrimaryKeyWithBLOBs(Weblog record);

    int updateByPrimaryKey(Weblog record);

    int queryCount();
}