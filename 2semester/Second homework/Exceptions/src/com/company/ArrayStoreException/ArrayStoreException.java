package com.company.ArrayStoreException;

public class ArrayStoreException {
    public int ArrayStoreException(Object mas[]){
        try {
            mas[0] = 2;
            System.out.println(mas[0]);
            return (int) mas[0];
        }
        catch (java.lang.ArrayStoreException e){
            System.out.println("Попытка сохранения в массиве объекта недопустимого типа.");
            System.out.println(2);
            mas[0] = 2.0;
            return 2;
        }

    }
}
