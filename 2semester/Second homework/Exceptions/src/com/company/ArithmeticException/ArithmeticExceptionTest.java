package com.company.ArithmeticException;

import com.company.ArithmeticException.ArithmeticException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticExceptionTest {


    @Test
    void arithmeticException() {
        ArithmeticException A = new ArithmeticException();
        assertArrayEquals(new int[]{3},new int[]{A.ArithmeticException(9,3) });
        assertArrayEquals(new int[]{4},new int[]{A.ArithmeticException(12,3) });
        assertArrayEquals(new int[]{35},new int[]{A.ArithmeticException(70,2) });
        assertArrayEquals(new int[]{0},new int[]{A.ArithmeticException(9,0) });
    }

}