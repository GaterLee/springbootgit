package com.springbootgit.Stu;

public class StringAndBuffer {

    /*
    String 与StringBuilder 类 的性能 异同
     */

    private static void str(){

        /*
            string本身是不可改变的，它只能赋值一次，
            每一次内容发生改变，都会生成一个新的对象，
            然后原有的对象引用新的对象，而每一次生成新对象都会对系统性能产生影响
         */
        String str="abc";
        String str02="abc"+"de";


        /*
            而StringBuilder类则不同，每次操作都是对自身对象进行操作，
            而不是生成新的对象，其所占空间会随着内容的增加而扩充，这样，
            在做大量的修改操作时，不会因生成大量匿名对象而影响系统性能
         */
        StringBuilder str03=new StringBuilder( "abc" );
        StringBuilder str04=new StringBuilder( "abcde" ); // 等同于 str03.Append("de");




    }

}
