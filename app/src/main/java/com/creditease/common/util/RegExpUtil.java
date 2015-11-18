package com.creditease.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式格式工具
 * Created by DT on 2015/11/18.
 */
public class RegExpUtil {
    /**
     * 判断是否为纯数字，允许为空字符串
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 判断是否为纯数字，且不为空字符串
     */
    public static boolean isNumericNotNone(String str) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 判断座机电话
     * 可匹配000-0000000前三或四后七或八
     * 可匹配(000)0000000前三或四后七或八
     * 可匹配000-00000000-000或(000)0000000-000分机为三至五位
     * 亦可匹配(000) 0000000及(000)-0000000格式
     */
    public static boolean isTelephone(String str) {
        Pattern pattern = Pattern.compile("0\\d{2,3}-\\d{7,8}"
                + "|\\(0\\d{2,3}\\)[- ]?\\d{7,8}|0\\d{2,3}[- ]?\\d{7,8}|0\\d{2,3}-\\d{7,8}-\\d{3,5}"
                + "|\\(0\\d{2,3}\\)[- ]?\\d{7,8}-\\d{3,5}");
        Matcher invalid = pattern.matcher(str);
        return invalid.matches();
    }

    /**
     * 验证手机号是否合法
     * 目前已包含177号段以及147号段等，有可能需要更新
     */
    public static boolean isMobileStrict(String str){
//        Pattern pattern1 = Pattern.compile("((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}");
        Pattern pattern = Pattern.compile("1[3|4|5|7|8]\\d{9}");//用Matcher.matches()时不需要前后的"^"和"$"
        Matcher invalid = pattern.matcher(str);
        return invalid.matches();
    }
    /**
     * 验证手机号码是否合法
     * 简单匹配，仅检测长度和首位
     */
    public static boolean isMobileNO(String str) {
        Pattern pattern = Pattern.compile("[1]\\d{10}");
        Matcher invalid = pattern.matcher(str);
        return invalid.matches();
    }

    /**
     * 18位或者15位身份证验证 18位的最后一位可以是字母x
     */
    public static boolean personIdValidation(String str) {
        Pattern pattern = Pattern.compile("(\\d{15})|(\\d{17}([0-9]|X|x))");
        Matcher invalid = pattern.matcher(str);
        return invalid.matches();
    }

    /**
     * 验证邮箱格式
     */
    public static boolean emailValidation(String str) {
        Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher invalid = pattern.matcher(str);
        return invalid.matches();
    }

    /**
     * 验证是否是全角字符
     */
    public static boolean quanjiaoValidation(String quanjiao) {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]*");
        Matcher invalid = pattern.matcher(quanjiao);
        return invalid.matches();
    }

    /**
     * 验证是否是汉字
     */
    public static boolean hanziValidation(String hanzi) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]*");
        Matcher invalid = pattern.matcher(hanzi);
        return invalid.matches();
    }

    /**
     * 验证是否是中文名字,包含少数民族的间隔符，如爱新觉罗·玄烨
     */
    public static boolean xingmingValidation(String hanzi) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5·]*");
        Matcher invalid = pattern.matcher(hanzi);
        return invalid.matches();
    }

    /**
     * 验证是否是中文英文数字
     */
    public static boolean ZYSValidation(String hanzi) {
        Pattern pattern = Pattern.compile("([a-zA-Z0-9]|[\u4E00-\u9FA5])*");
        Matcher invalid = pattern.matcher(hanzi);
        return invalid.matches();
    }

    /**
     * 验证是否是公司名称，可以带括号
     */
    public static boolean company_nameValidation(String hanzi) {
        Pattern pattern = Pattern.compile("([a-zA-Z0-9]|[()]|[\uFF08\uFF09]|[\u4E00-\u9FA5])*");
        Matcher invalid = pattern.matcher(hanzi);
        return invalid.matches();
    }

    /**
     * 验证是否是地址
     */
    public static boolean addressValidation(String hanzi) {
        Pattern pattern = Pattern.compile("([a-zA-Z0-9]|[-—‐]|[\u002d\u2014\u2010]|[\u4E00-\u9FA5])*");
        Matcher invalid = pattern.matcher(hanzi);
        return invalid.matches();
    }

    /**
     * 验证车牌号，要求字母大写，如：鄂A-66666,中间可为"·"或"-"或"－"或空格或无
     */
    public static boolean carCardValidation(String str){
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]{1}[A-Z]{1}[\u0020\u002d\u2014\u2010\u003A\uFF1A·]?[A-Z0-9]{5}");
        Matcher invalid = pattern.matcher(str);
        return invalid.matches();
    }
}
