package com.company.NumberFormatException;

public class NumberFormatException {

    public Integer NumberFormatException(String s){
        try {
            Object i;
            i = Integer.valueOf(s);
            System.out.println(i);
            return (Integer) i;
        }
        catch (java.lang.NumberFormatException e){;
            System.out.println("Строка, которая, как предполагалось должна содержать представление числа, не отвечает этому требованию.");
            System.out.println(-1);
            return  -1;
        }
    }
}
