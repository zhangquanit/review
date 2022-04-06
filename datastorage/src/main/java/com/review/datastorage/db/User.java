package com.review.datastorage.db;

/**
 * @author 张全
 */

public class User {
    private int id;
    private String name;
    private int age;
    private float salery;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getSalery() {
        return salery;
    }

    public void setSalery(float salery) {
        this.salery = salery;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salery=" + salery +
                '}';
    }
}
