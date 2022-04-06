package com.terry.java_demo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Vector;

/**
 * @author zhangquan
 */
public class Test {


    public static void main(String[] args) throws Exception {
        Vector<Integer> objects = new Vector<>(10, 1);
        print(objects);

        for (int i = 0; i < 10; i++) {
            objects.add(i);
        }
        print(objects);

        objects.add(11);
        print(objects);

        System.out.println("----------");
        for (int i = 12; i <= 20; i++) {
            objects.add(i);
        }
        print(objects);

        objects.add(21);
        print(objects);
    }

    private static void print(Vector<Integer> objects) throws Exception {
        Class<? extends Vector> aClass = objects.getClass();
        Field elementData = aClass.getDeclaredField("elementData");
        elementData.setAccessible(true);
        Object[] value = (Object[]) elementData.get(objects);
        System.out.println("数组长度" + value.length);
        System.out.println("数组=" + Arrays.toString(value));
    }

    private static void switchTest() {
        char a = 'a';
        switch (a) {
            case 'a':
                break;
        }

        byte b = 1;
        switch (b) {

        }

        Integer i = 1;
        switch (i) {

        }


    }

    public static class Father {
        public void fun() {

        }

        public void fun(String param) {

        }

        public void fun(int param) {

        }

        public void fun(int param, String param2) {

        }


    }
}
