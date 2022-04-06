package com.terry.java_demo.jdk8;

/**
 * @author zhangquan
 */
public interface MyInterface {

    public static void fun() {
        System.out.println("静态方法");
    }

    public default void fun2() {
        System.out.println("在返回值前+default，然后其子类的实现类中进行调用，子类中也可以重写");
    }

}
