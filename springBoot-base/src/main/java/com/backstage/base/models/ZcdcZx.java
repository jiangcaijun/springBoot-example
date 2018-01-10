package com.backstage.base.models;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * 专项管理
 */
@Data
public class ZcdcZx {
    private String zxUuid;

    private String zxCode;

    private String zxName;

    /*全国 ：1
    省/直辖市 ：2
    区/旗/市 ：3 */
    private String zxRange;

    private String zxStatus;

    private String zxType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private Date startTime;

    @JSONField(format="yyyy-MM-dd")
    @JsonFormat (pattern="yyyy-MM-dd")
    private Date endTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat (pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String key1;

    private String key2;

    private String key3;

}