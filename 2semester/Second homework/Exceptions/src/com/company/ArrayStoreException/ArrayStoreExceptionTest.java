package com.company.ArrayStoreException;

import com.company.ArrayStoreException.ArrayStoreException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStoreExceptionTest {

    @Test
    void arrayStoreException() {
        ArrayStoreException A = new ArrayStoreException();
        assertArrayEquals(new int[]{2},new int[]{A.ArrayStoreException(new Integer[]{1,2,3}) });
        assertArrayEquals(new int[]{2},new int[]{A.ArrayStoreException(new Double[]{1.3,2.3,3.3}) });
        assertArrayEquals(new int[]{2},new int[]{A.ArrayStoreException(new Integer[]{4,54,35}) });
    }
}