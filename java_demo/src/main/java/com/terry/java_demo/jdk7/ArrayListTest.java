package com.terry.java_demo.jdk7;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhangquan
 */
public class ArrayListTest {
    public static void main(String[] args) throws Exception {
        capacityTest();
    }


    private static void capacityTest() throws Exception {
        //默认数组大小为10，数组满了后  按size=oldSize+oldSize/2; 扩容
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
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

        objects.add(22);
        objects.add(23);
        print(objects);
    }

    private static void print(ArrayList<Integer> objects) throws Exception {
        Class<? extends ArrayList> aClass = objects.getClass();
        Field elementData = aClass.getDeclaredField("elementData");
        elementData.setAccessible(true);
        Object[] value = (Object[]) elementData.get(objects);
        System.out.println("数组长度" + value.length);
        System.out.println("数组=" + Arrays.toString(value));
    }

}
