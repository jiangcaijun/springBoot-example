package com.backstage.constant;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式常量类
 * Created by jiangcaijun on 2017/9/26.
 */
public class RegConstant {
    /**
     * 正确，则返回true
     * @param str
     * @param reg
     * @return
     */
    public static boolean isReg(String str , String reg){
        if(StringUtils.isBlank(str)){
            return false;
        }
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        return mat.find();
    }
    //ipv4的正则表达式，参考自：http://www.cnblogs.com/helloshrek/p/6018902.html
    public static final String IPV4 = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
            +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
    //身份证号的正则表达式，参考自：http://ontheway-lyl.iteye.com/blog/2268132
    public static final String ID_CARD = "^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$";
}
