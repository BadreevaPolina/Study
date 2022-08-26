package com.company.StringIndexOutOfBoundsException;

import com.company.StringIndexOutOfBoundsException.StringIndexOutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringIndexOutOfBoundsExceptionTest {

    @Test
    void stringIndexOutOfBoundsException() {
        StringIndexOutOfBoundsException S = new StringIndexOutOfBoundsException();
        assertArrayEquals(new char[]{'f'},new char[]{S.StringIndexOutOfBoundsException("efefer",3) });
        assertArrayEquals(new char[]{'r'},new char[]{S.StringIndexOutOfBoundsException("efefer",5) });
        assertArrayEquals(new char[]{'e'},new char[]{S.StringIndexOutOfBoundsException("efefer",9) });
    }
}