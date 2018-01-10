package com.backstage.base.mapper;

import com.backstage.base.models.PageDo;
import com.backstage.base.models.ZcdcZx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZcdcZxMapper {
    int deleteByPrimaryKey(String zxUuid);

    int deleteByPrimaryKeyList(List<String> zxUuid);

    int insert(ZcdcZx record);

    int insertSelective(ZcdcZx record);

    ZcdcZx selectByPrimaryKey(String zxUuid);

    int updateByPrimaryKeySelective(ZcdcZx record);

    int updateByPrimaryKey(ZcdcZx record);

    List<Object> findPageDo(PageDo pageDo);

    int findPageDoCount(PageDo pageDo);

    /**
     * 获取最大的zxCode
     * @return
     */
    String queryMaxZxCode();

    int updateStatusu2Stop(@Param(value = "list") List<String> idList);
}