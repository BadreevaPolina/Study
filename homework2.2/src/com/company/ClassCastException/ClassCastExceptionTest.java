package com.company.ClassCastException;

import com.company.ClassCastException.ClassCastException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassCastExceptionTest {

    @Test
    void classCastException() {
        ClassCastException C = new ClassCastException();
        assertArrayEquals(new Object[]{C.ClassCastException(new String[]{"1","2","3"})},new Object[]{C.ClassCastException(new String[]{"1","2","3"})});
        assertArrayEquals(new Object[]{C.ClassCastException(new String[]{"qw","qw","qw"})},new Object[]{C.ClassCastException(new String[]{"qw","qw","qw"})});
        assertArrayEquals(new Object[]{C.ClassCastException(new Integer[]{1,2,3})},new Object[]{C.ClassCastException(new Integer[]{1,2,3})});
    }
}