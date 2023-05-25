package com.company.NumberFormatException;

import com.company.NumberFormatException.NumberFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberFormatExceptionTest {

    @Test
    void numberFormatException() {
        NumberFormatException N = new NumberFormatException();
        assertArrayEquals(new Object[]{Integer.valueOf(3)},new Object[]{N.NumberFormatException(String.valueOf("3"))});
        assertArrayEquals(new Object[]{Integer.valueOf(2)},new Object[]{N.NumberFormatException(String.valueOf("2"))});
        assertArrayEquals(new Object[]{Integer.valueOf(-1)},new Object[]{N.NumberFormatException(String.valueOf("std"))});
    }
}