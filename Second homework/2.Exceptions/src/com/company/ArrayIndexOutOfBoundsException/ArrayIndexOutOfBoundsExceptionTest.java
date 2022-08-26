package com.company.ArrayIndexOutOfBoundsException;

import com.company.ArrayIndexOutOfBoundsException.ArrayIndexOutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayIndexOutOfBoundsExceptionTest {

    @Test
    void arrayIndexOutOfBoundsException() {
        ArrayIndexOutOfBoundsException A = new ArrayIndexOutOfBoundsException();
        assertArrayEquals(new int[]{0},new int[]{A.ArrayIndexOutOfBoundsException(new int[]{1,2,3},3) });
        assertArrayEquals(new int[]{0},new int[]{A.ArrayIndexOutOfBoundsException(new int[]{5,5,5,5},7) });
        assertArrayEquals(new int[]{0},new int[]{A.ArrayIndexOutOfBoundsException(new int[]{1},0) });
    }
}