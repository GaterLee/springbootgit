package com.springbootgit.service.impl;

import com.google.common.util.concurrent.RateLimiter;
import com.springbootgit.service.QpsLimitSevice;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class QpsLimitServiceImpl implements QpsLimitSevice {



    @Override
    public String guava() {
        try {
            //限流,每秒允许10个请求进入秒杀 令牌速度=0.1s
            RateLimiter limiter = RateLimiter.create( 10 );
            if (limiter.tryAcquire( 50, TimeUnit.MILLISECONDS )) {
                            System.out.println( "恭喜你,秒杀成功！！！" );
                            //需要限流的代码

                        } else {
                            System.out.println( "秒杀失败,继续加油~" );
                            //服务降级 或者 服务熔断 直接抛弃  都是超过QPS限制 执行第二策略

                        }

//            for (int i = 0; i < 100; i++) {
//                //100 个线程 同时抢购
//                int finalI = i;
//                new Thread( new Runnable() {
//                    @Override
//                    public void run() {
//                        if (limiter.tryAcquire( 50, TimeUnit.MILLISECONDS )) {
//                            System.out.println( finalI+"恭喜你,秒杀成功！！！" );
//                        } else {
//                            System.out.println( finalI+"秒杀失败,继续加油~" );
//                        }
//                    }
//                } ).start();
//                Thread.sleep( 10 );
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "good job";
    }
}
