package com.be.expert.helper;

public class User {

    public enum Sex {
        MALE, FEMALE
    }

    private static final String PREFIX_MSG = "Hi, Current is ".intern();

    private final Sex sex;
    private final String name;

    public User(String userName, Sex userSex) {
        this.name = userName;
        this.sex = userSex;
    }

    public Sex getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return PREFIX_MSG + "User{" +
                "sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }
}
