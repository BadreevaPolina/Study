package com.company.NullPointerException;

public class NullPointerException {
    public int  NullPointerException(int mas[]){
        int i;
        try{
            i = mas.length;
            return i;
        }
        catch(java.lang.NullPointerException e)
        {
            System.out.println("Использование в программе ссылки, содержащая значение null.");
            mas = new int[5];
            i = mas.length;
            return i;
        }
    }
}
