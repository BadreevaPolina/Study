package com.company.NullPointerException;

import com.company.NullPointerException.NullPointerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NullPointerExceptionTest {

    @Test
    void nullPointerException() {
        NullPointerException N = new NullPointerException();
        assertArrayEquals(new int[]{5},new int[]{N.NullPointerException(new int[]{3,3,3,3,3}) });
        assertArrayEquals(new int[]{3},new int[]{N.NullPointerException(new int[]{1,2,3}) });
        int t[] = null;
        assertArrayEquals(new int[]{5},new int[]{N.NullPointerException(t) });
    }
}