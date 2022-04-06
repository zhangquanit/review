package com.terry.java_demo.jdk7;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Vector;

/**
 * @author zhangquan
 */
public class VectorTest {
    public static void main(String[] args) throws Exception {
        capacityTest();
    }


    private static void capacityTest() throws Exception {
        //默认数组大小为10，数组满了后  0表示双倍扩容
        Vector<Integer> objects = new Vector<>(10, 0);
        print(objects);

        for (int i = 1; i <= 10; i++) {
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

}
