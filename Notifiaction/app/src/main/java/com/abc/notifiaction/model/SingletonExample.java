package com.abc.notifiaction.model;

import java.util.ArrayList;

public class SingletonExample {
    // private instance variable
    private static SingletonExample INSTANCE = null;

    // private constructor that will be accessed inside this class only
    public SingletonExample() {
    }

    ;
    public ArrayList<Boolean> answersMap = new ArrayList<>();

    // synchronized keyword to make it thread safe
    // static method to access the object
    public static synchronized SingletonExample getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonExample();
        }
        return (INSTANCE);
    }

    // other instance variables and methods
}