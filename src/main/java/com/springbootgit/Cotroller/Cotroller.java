package com.springbootgit.Cotroller;


import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Cotroller {

    /**
     *@Description alibaba  sentinel 限流 模拟阿里控制台
     * @auther: Mr.man
     * @Company:weface
     * @date: 2019/11/27 10:49
     * @version V1.0
     */
    
    @SentinelResource("resource")
    @RequestMapping("/hello")
    public  String success(){
     //从代码层面 配置限流规则  则sentinel控制台 临时配置不起作用
        initFlowRules();
        while (true){
            try(Entry entry= SphU.entry( "Hello" )){
                // 被保护的逻辑
                System.out.println("hello world");
                return "hello world";
            }catch (BlockException ex){
                //处理被流的逻辑  服务降级
                System.out.println("blocked!");
                return "blocked";
            }
        }



    }



    /*
    模拟QPS请求 来验证限流规则
     */
    public static void main(String[] args) {
        // 配置规则.
        initFlowRules();

        while (true) {
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性
            try (Entry entry = SphU.entry("HelloWorld")) {
                // 被保护的逻辑
                System.out.println("hello world");
            } catch (BlockException ex) {
                // 处理被流控的逻辑
                System.out.println("blocked!");
            }
        }
    }


    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 10.
        rule.setCount(10);

        //设置白名单  但是无效！！
        rule.setLimitApp("172.16.10.133,172.16.10.88");

        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
