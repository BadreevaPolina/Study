package com.company.NegativeArraySizeException;

import com.company.NegativeArraySizeException.NegativeArraySizeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NegativeArraySizeExceptionTest {

    @Test
    void negativeArraySizeException() {
        NegativeArraySizeException N = new NegativeArraySizeException();
        assertArrayEquals(new int[]{5},new int[]{N.NegativeArraySizeException(new int[]{3,3,3,3,3},-5) });
        assertArrayEquals(new int[]{3},new int[]{N.NegativeArraySizeException(new int[]{1,2,3},3) });
        assertArrayEquals(new int[]{2},new int[]{N.NegativeArraySizeException(new int[]{3,3},2) });
    }
}