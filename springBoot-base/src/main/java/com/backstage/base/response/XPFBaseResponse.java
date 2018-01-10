package com.backstage.base.response;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * 统一基础的回复格式
 */
public abstract class XPFBaseResponse extends HashMap<String, Object> {

    protected XPFBaseResponse(boolean success) {
        super.put("success", success);
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
