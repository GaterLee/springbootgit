package com.springbootgit.Stu.Collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class StuList {
    /*
List：一种有序列表的集合，例如，按索引排列的Student的List；
Set：一种保证没有重复元素的集合，例如，所有无重复名称的Student的Set；
Map：一种通过键值（key-value）查找的映射表集合，例如，根据Student的name查找对应Student的Map

     */
   private  static void list(){
       List<String> list =new ArrayList<>();

       //往集合中添加元素
       list.add("湖南" );
       list.add( "上海" );

       //往集合中插入指定下标元素
       list.add( 2,"北京" );

       //修改 list 集合 元素
       list.set( 0,"长沙" );

       //查找指定元素  第一次出现的下标
       int i=list.indexOf( "长沙" );

       //删除 list 集合
        list.remove( "长沙" );


       // 截取特定 元素
          list.subList( 0,1 );


       System.out.println(list.size());
       Iterator<String> iters=  list.iterator();

       ListIterator<String> listIterator = list.listIterator();

       for (String obj:list
            ) {
           System.out.println("for循环遍历list集合元素："+obj);
       }


       while(iters.hasNext())
       {
           System.out.println("iterator迭代器遍历List中元素，方法二："+iters.next());
       }


       while(listIterator.hasNext())
       {
           System.out.println("listIterator迭代器遍历List中元素，方法二："+listIterator.next());
       }

       List<Object> list2=new ArrayList<>( );

   }

// -----------------------------------------------------------------------------------------------------------




    public static void main(String[] args) {
        StuList.list();
    }

}
