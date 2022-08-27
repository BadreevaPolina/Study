package com.company.ArrayIndexOutOfBoundsException;

public class ArrayIndexOutOfBoundsException {
    public int  ArrayIndexOutOfBoundsException(int mas[],int t){
        {
            try {
                mas[t] = 0;
                System.out.println(mas[t]);
                return mas[t];
            }
            catch (java.lang.ArrayIndexOutOfBoundsException e){
                System.out.println("Задано значение индекса массива, не принадлежащее допустимому диапазону.");
                t = mas.length;
                mas[t-1] = 0;
                System.out.println(mas[t-1]);
                return mas[t-1];
            }
        }
    }
}
