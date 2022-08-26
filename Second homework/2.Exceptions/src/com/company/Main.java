package com.company;

import com.company.ArithmeticException.ArithmeticException;
import com.company.ArrayIndexOutOfBoundsException.ArrayIndexOutOfBoundsException;
import com.company.ArrayStoreException.ArrayStoreException;
import com.company.ClassCastException.ClassCastException;
import com.company.EmptyStackException.EmptyStackException;
import com.company.NegativeArraySizeException.NegativeArraySizeException;
import com.company.NullPointerException.NullPointerException;
import com.company.NumberFormatException.NumberFormatException;
import com.company.StringIndexOutOfBoundsException.StringIndexOutOfBoundsException;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	ArithmeticException ArithmeticException = new ArithmeticException();
    ArithmeticException.ArithmeticException(9,0);

    int mas[] = {1, 2, 3};
    ArrayIndexOutOfBoundsException ArrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException();
    ArrayIndexOutOfBoundsException.ArrayIndexOutOfBoundsException(mas,4);

    Object a[] = new Double[2];
    a[0] = 1.2; a[1] = 2.3;
    ArrayStoreException ArrayStoreException = new ArrayStoreException();
    ArrayStoreException.ArrayStoreException(a);

    Object i=Integer.valueOf(1);
    ClassCastException ClassCastException = new ClassCastException();
    ClassCastException.ClassCastException(i);

    String s = "std";
    NumberFormatException NumberFormatException = new NumberFormatException();
    NumberFormatException.NumberFormatException(s);

    String q = "string";
    StringIndexOutOfBoundsException StringIndexOutOfBoundsException = new StringIndexOutOfBoundsException();
    StringIndexOutOfBoundsException.StringIndexOutOfBoundsException(q,9);

    int mass[];
    mass = null;
    NullPointerException NullPointerException=new NullPointerException();
    NullPointerException.NullPointerException(mass);

    NegativeArraySizeException NegativeArraySizeException = new NegativeArraySizeException();
    NegativeArraySizeException.NegativeArraySizeException(mas,-5);

    Stack st = new Stack();
    EmptyStackException EmptyStackException = new EmptyStackException();
    EmptyStackException.EmptyStackException(st);
    }
}
