package com.company.ArithmeticException;

public class ArithmeticException {
    public int ArithmeticException(int n, int w) {
        int t;
        try {
            t = n / w;
            System.out.println(t);
            return t;
        } catch (java.lang.ArithmeticException e) {
            System.out.println("На 0 делить нельзя.");
            t = 0;
            System.out.println(t);
            return t;
        }
    }
}
