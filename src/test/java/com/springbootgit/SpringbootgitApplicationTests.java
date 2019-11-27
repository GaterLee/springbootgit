package com.springbootgit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootgitApplicationTests {

    @Test
    void contextLoads() {

    }


//java 基础

    /**
     *@Description 代码优化 性能策略
     * @auther: Mr.man
     * @Company:weface
     * @date: 2019/11/7 10:17
     * @version V1.0
     */


    @Test
    public  void efficientCode(){

     long startTime=System.currentTimeMillis();
       String str=new String(  );
        for (int i = 0; i < 10000; i++) {
            str=str+i;
        }
     long endTime=System.currentTimeMillis();
        System.out.println("当前程序耗时："+(endTime-startTime)+"ms");



        long startTime02=System.nanoTime();
        for (int i = 0; i < 100; i++) {
            str=str+i;
        }
        long endTime02=System.nanoTime();
        System.out.println("当前程序耗时："+(endTime02-startTime02)+"ns");


    }

/*try中执行完return的语句后，不返回，执行finally块，finally块执行结束后，
返回到try块中，返回try块中最后return的值*/
//    ACBD
    @Test
     public void flow(){
        System.out.println(fun1());
    }
  public  static  String fun1(){
      try {
          System.out.println("A");
          return  fun2();
      }finally {
          System.out.println("B");
      }
      }
      public  static String fun2(){
          System.out.println("C");
          return "D";
  }

//    前三个参数：核心线程有5个，最大线程数是10个，keepAliveTime是15s，如果线程池中的线程大于5，
//    那么超15s的空闲线程就会被结束，也就是说，一定会保持5个线程不会被结束。当所有任务完成后，会保持5个空闲的线程
@Test
    public  void  execuPool(){
    ThreadPoolExecutor executor=new ThreadPoolExecutor( 5,10,15, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(5),new ThreadPoolExecutor.CallerRunsPolicy() );
}


@Test
    public  void sendEmail(){



}

/*
判断一个int数组中的元素是否存在重复，方法声明如下
 解说：
Set 集合 元素 是不能重复的 无序
Set 对象本身是没有顺序的，Set首先保证元素的唯一性
LinkedHashSet 和 TreeSet具有顺序
list与set方法的区别有：
list可以允许重复对象和插入多个null值，而set不允许；
list容器是有序的，而set容器是无序的等等
Collection 允许重复
 */
@Test
 public boolean isRepeat(){

   int m[] ={1,8,9};
    Set h=new HashSet( m.length );
    for (int i = 0; i < m.length; i++) {
        h.add( new Integer( m[i] ) );
    }
     if(h.size()==m.length){
         return false;
     }else{
         return true;
     }

}




}
