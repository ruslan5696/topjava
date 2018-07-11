package ru.javawebinar.topjava;

import java.util.Arrays;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello Topjava Enterprise!");
        int[] ints = {1, 2, 3};
        int i = Arrays.stream(ints).findAny().orElse(10);
        System.out.println(i);
    }
}
