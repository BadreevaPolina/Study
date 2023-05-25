package com.company.ClassCastException;

public class ClassCastException {
    public Object ClassCastException(Object i) {
        try {
            String s = (String) i;
            System.out.println(s);
            return s;
        } catch (java.lang.ClassCastException e) {
            System.out.println("Выполнена неверная операция преобразования типов.");
            if (i instanceof Integer) {
                Integer s = (Integer) i;
                System.out.println(s);
                return s;
            }
            if (i instanceof Double) {
                Double s = (Double) i;
                System.out.println(s);
                return s;
            }
        }
        return null;
    }
}
