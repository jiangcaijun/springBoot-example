package com.backstage.base.service;

/**
 * Created by jiangcaijun on 2017/10/27.
 */
public interface IRedisService {
    boolean setString(String key, String value);

    String getString(String key);

    boolean expire(String key, long expire);
}