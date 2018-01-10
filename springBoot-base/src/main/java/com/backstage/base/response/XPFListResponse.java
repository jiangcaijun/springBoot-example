package com.backstage.base.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表页的返回值
 */
@Data
public class XPFListResponse extends XPFBaseResponse {

    /**
     * 构建列表页的返回值
     * @param objects 列表页的数据
     * @param offset 结果开始的位移，从0开始
     * @param total 数据的总数量
     */
    public XPFListResponse(List<?> objects, int offset, int total) {
        super(true);
        objects = (objects == null) ? new ArrayList<>() : objects;
        super.put("offset", offset);
        super.put("total", total);
        super.put("count", objects.size());
        super.put("list", objects);
    }

}
