package com.company.EmptyStackException;

import java.util.Stack;

public class EmptyStackException {
    public Stack EmptyStackException(Stack st){
        try{
            Integer n = (Integer) st.pop();
            return st;
        }
        catch (java.util.EmptyStackException e)
        {
            System.out.println("Извлечение объекта из пустого стека.");
            st.push(3);
            Integer n = (Integer) st.pop();
            System.out.println(st);
            return st;
        }
    }
}
