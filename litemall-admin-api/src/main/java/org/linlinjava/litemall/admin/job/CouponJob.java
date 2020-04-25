package org.linlinjava.litemall.admin.job;

import lombok.extern.slf4j.Slf4j;
import org.linlinjava.litemall.db.domain.LitemallCoupon;
import org.linlinjava.litemall.db.domain.LitemallCouponUser;
import org.linlinjava.litemall.db.service.LitemallCouponService;
import org.linlinjava.litemall.db.service.LitemallCouponUserService;
import org.linlinjava.litemall.db.util.CouponConstant;
import org.linlinjava.litemall.db.util.CouponUserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 检测优惠券过期情况
 */
@Component
@Slf4j
public class CouponJob {

    @Autowired
    private LitemallCouponService couponService;
    @Autowired
    private LitemallCouponUserService couponUserService;

    /**
     * 每隔一个小时检查
     * TODO
     * 注意，因为是相隔一个小时检查，因此导致优惠券真正超时时间可能比设定时间延迟1个小时
     */
    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void checkCouponExpired() {
        log.info("系统开启任务检查系统优惠券和用户优惠券是否已经过期");

        //查找已经过期的优惠券
        List<LitemallCoupon> couponList = couponService.queryExpired();
        for (LitemallCoupon coupon : couponList) {
            coupon.setStatus(CouponConstant.STATUS_EXPIRED);
            couponService.updateById(coupon);
        }

        //查找用户已经过期的优惠券
        List<LitemallCouponUser> couponUserList = couponUserService.queryExpired();
        for (LitemallCouponUser couponUser : couponUserList) {
            couponUser.setStatus(CouponUserConstant.STATUS_EXPIRED);
            couponUserService.update(couponUser);
        }

        log.info("系统结束任务检查系统优惠券和用户优惠券是否已经过期");
    }

}
