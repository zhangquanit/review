package com.terry.java_demo.jdk7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangquan
 */
public class JDK7 {
    public static void main(String[] args) {

    }

    private static void tryWithResource() {
        try (BufferedReader br = new BufferedReader(new FileReader("D://obj.txt"))) {
            String line = br.readLine();
            System.out.println("line=" + line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void switchString() {
        String va = "a";
        switch (va) {
            case "a":
                break;
            default:
                break;
        }
    }

    private static void genernic() {
        List<String> list = new ArrayList<>();
    }
}
