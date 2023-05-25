package com.company.NegativeArraySizeException;

public class NegativeArraySizeException {
    public int NegativeArraySizeException(int mas[],int t){
        try {
            int i ;
            mas = new int[t];
            System.out.println(t);
            return t;
        }
        catch(java.lang.NegativeArraySizeException e) {
            System.out.println("Попытка создать массив отрицательного размера.");
            t = -t;
            mas = new int[t];
            System.out.println(t);
            return t;
        }
    }
}
