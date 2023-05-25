package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Integer[] x = { 8, 0, 4, 7, 3, 7, 10, 12, -3 };
        System.out.println("Было");
        System.out.println(Arrays.toString(x));
        int low = 0;
        int high = x.length - 1;
        Sort<Integer> sort = new Sort<>();
        sort.sort(x,low, high);
        System.out.println("Стало");
        System.out.println(Arrays.toString(x));
        Sort<Double> s = new Sort<>();
        Double[] w = {2.3,4.5,1.3,6.7,5.8};
        s.sort(w,0,4);
        System.out.println(Arrays.toString(w));
        Sort<String> e = new Sort<>();
        String[] r = {"dcs","dvd","d","f"};
        e.sort(r,0,3);
        System.out.println(Arrays.toString(r));
    }
}
