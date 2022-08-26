package com.company.StringIndexOutOfBoundsException;

public class StringIndexOutOfBoundsException {
    public char  StringIndexOutOfBoundsException(String w, int t){
        try {
            char chr = w.charAt(t);
            System.out.println(chr);
            return chr;
        }
      catch(java.lang.StringIndexOutOfBoundsException e)
        {
            System.out.println("Указана позиция, лежащая за границей строки.");
            t = 0;
            char chr = w.charAt(t);
            System.out.println(chr);
            return chr;
        }
    }
}
