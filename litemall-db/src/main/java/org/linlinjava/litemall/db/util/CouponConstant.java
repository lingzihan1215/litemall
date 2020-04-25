package org.linlinjava.litemall.db.util;

/**
 * 优惠券相关常量
 */
public class CouponConstant {
    //优惠券类型
    public static final Short TYPE_COMMON = 0;      //通用领券
    public static final Short TYPE_REGISTER = 1;    //注册赠券
    public static final Short TYPE_CODE = 2;        //兑换码

    //商品使用范围
    public static final Short GOODS_TYPE_ALL = 0;       //全场通用
    public static final Short GOODS_TYPE_CATEGORY = 1;  //指定分类
    public static final Short GOODS_TYPE_ARRAY = 2;     //指定商品

    //优惠券状态
    public static final Short STATUS_NORMAL = 0;    //优惠券可用
    public static final Short STATUS_EXPIRED = 1;   //优惠券已过期
    public static final Short STATUS_OUT = 2;       //优惠券已领完

    //有效期类型
    public static final Short TIME_TYPE_DAYS = 0;   //相对时间，基于领取时间有效期的优惠券，则优惠券不会过期
    public static final Short TIME_TYPE_TIME = 1;   //绝对时间
}
