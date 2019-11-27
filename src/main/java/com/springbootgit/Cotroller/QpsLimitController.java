package com.springbootgit.Cotroller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.google.common.util.concurrent.RateLimiter;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springbootgit.service.QpsLimitSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
public class QpsLimitController {

    @Resource
    QpsLimitSevice sevice;


    @GetMapping("/ok")
    @ResponseBody
    public String ok() {

        return "ok.com";

    }


    @SentinelResource("resource")
    @RequestMapping("/sentinel/hello")
    public Map<String, Object> hello() {
        Map<String, Object> map = new HashMap<>( 2 );
        map.put( "appName", "appName" );
        map.put( "method", "hello" );
        return map;
    }

/**
 *@Description QPS控制 --令牌桶策略 
 * @auther: Mr.man
 * @Company:weface
 * @date: 2019/11/26 10:21
 * @version V1.0
 */
    
    @SentinelResource("resource")
    @PostMapping("/guavaSeckill")
    public String guava() {
        sevice.guava();

        return "限流成功";
    }



    /*
    两者对比： 首先技术 应用的业务场景不同  一般没有可比性
    技术选型上： 要考虑到接入成本  运维成本  代码入侵度
    Hystrix 代码行多   自带fallback 回调


     */

/**
 *@Description QPS控制 --Hystic组件控制
 * @auther: Mr.man
 * @Company:weface
 * @date: 2019/11/26 10:24
 * @version V1.0
 */


@HystrixCommand(fallbackMethod = "springCHystrixFallback")
@PostMapping("/hystrix")
public String springCloundHystrix() {


    return "springCloundHystrix is ok";
}




// 服务降级

    public  String springCHystrixFallback(){


    return "service  fall back ok";
    }



    public static void main(String[] args) {
        try {
        //限流,每秒允许10个请求进入秒杀 令牌速度=0.1s
        RateLimiter limiter = RateLimiter.create( 10 );
        for (int i = 0; i < 100; i++) {
            //100 个线程 同时抢购
            int finalI = i;
            new Thread( new Runnable() {
                @Override
                public void run() {
                    if (limiter.tryAcquire( 50, TimeUnit.MILLISECONDS )) {
                        System.out.println( finalI+"恭喜你,秒杀成功！！！" );
                    } else {
                        System.out.println( finalI+"秒杀失败,继续加油~" );
                    }
                }
            } ).start();
//给lim
                Thread.sleep( 10 );
          }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}