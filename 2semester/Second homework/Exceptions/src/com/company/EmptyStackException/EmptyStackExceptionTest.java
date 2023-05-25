package com.company.EmptyStackException;

import com.company.EmptyStackException.EmptyStackException;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class EmptyStackExceptionTest {

    @Test
    void emptyStackException() {
        EmptyStackException E = new EmptyStackException();
        Stack deck = new Stack();
        deck.push(1);
        deck.push(2);
        deck.push(3);
        Stack deck1 = new Stack();
        deck1.push(1);
        deck1.push(2);
        assertArrayEquals(new Stack[]{deck1},new Stack[]{E.EmptyStackException(deck) });
        Stack d = new Stack();
        assertArrayEquals(new Stack[]{d},new Stack[]{E.EmptyStackException(d) });
    }
}