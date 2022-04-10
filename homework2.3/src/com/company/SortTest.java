package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @org.junit.jupiter.api.Test
    void sort() {
        Sort<Integer> s = new Sort<>();
        assertArrayEquals(new Integer[]{-3, 0, 3, 4, 7, 7, 8, 10, 12},s.sort(new Integer[]{ 8, 0, 4, 7, 3, 7, 10, 12, -3},0,8));
        assertArrayEquals(new Integer[]{-3, -3, 0, 3, 4, 7, 7, 8, 10},s.sort(new Integer[]{ 8, 0, 4, 7, 3, 7, 10, -3, -3},0,8));
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9},s.sort(new Integer[]{ 9, 8, 7, 6, 5, 4, 3, 2, 1},0,8));
        assertArrayEquals(new Integer[]{-3, -3, -3, 4, 4, 4, 4, 5, 5},s.sort(new Integer[]{ 5, 4, 4, -3, -3, -3, 4, 4, 5},0,8));

        Sort<Double> d = new Sort<>();
        assertArrayEquals(new Double[]{-3.0, 0.0, 3.0, 4.0, 7.0, 7.0, 8.0, 10.0, 12.0},d.sort(new Double[]{ 8.0, 0.0, 4.0, 7.0, 3.0, 7.0, 10.0, 12.0, -3.0},0,8));
        assertArrayEquals(new Double[]{1.2,2.3,4.5,5.6,7.8},d.sort(new Double[]{2.3,4.5,7.8,1.2,5.6},0,4));
        assertArrayEquals(new Double[]{1.2,2.2,3.2,4.2,5.2},d.sort(new Double[]{5.2,4.2,3.2,2.2,1.2},0,4));
        assertArrayEquals(new Double[]{-2.2,-1.2,3.2,4.2,5.2},d.sort(new Double[]{5.2,4.2,3.2,-2.2,-1.2},0,4));

        Sort<String> w = new Sort<>();
        assertArrayEquals(new String[]{"a","b","c","d"},w.sort(new String[]{"d","a","c","b"},0,3));
        assertArrayEquals(new String[]{"ba","bd","c","d"},w.sort(new String[]{"d","ba","c","bd"},0,3));
        assertArrayEquals(new String[]{"aad","abs","ac","ader"},w.sort(new String[]{"ader","abs","ac","aad"},0,3));
        assertArrayEquals(new String[]{"w","ww","www","wwww","wwwww"},w.sort(new String[]{"wwwww","ww","wwww","w","www"},0,4));

        Sort<Short> i = new Sort<>();
        assertArrayEquals(new Short[]{-3, 0, 3, 4, 7, 7, 8, 10, 12},i.sort(new Short[]{ 8, 0, 4, 7, 3, 7, 10, 12, -3},0,8));
        assertArrayEquals(new Short[]{-3, -3, 0, 3, 4, 7, 7, 8, 10},i.sort(new Short[]{ 8, 0, 4, 7, 3, 7, 10, -3, -3},0,8));
        assertArrayEquals(new Short[]{1, 2, 3, 4, 5, 6, 7, 8, 9},i.sort(new Short[]{ 9, 8, 7, 6, 5, 4, 3, 2, 1},0,8));
        assertArrayEquals(new Short[]{-3, -3, -3, 4, 4, 4, 4, 5, 5},i.sort(new Short[]{ 5, 4, 4, -3, -3, -3, 4, 4, 5},0,8));

        Sort<Character> c = new Sort<>();
        assertArrayEquals(new Character[]{'a','b','c','d'},c.sort(new Character[]{'d','a','c','b'},0,3));
        assertArrayEquals(new Character[]{'b','c','d','p'},c.sort(new Character[]{'c','p','b','d'},0,3));
        assertArrayEquals(new Character[]{'a','b'},c.sort(new Character[]{'b','a'},0,1));
        assertArrayEquals(new Character[]{'a','a','b','b'},c.sort(new Character[]{'b','a','b','a'},0,3));

        Sort<Byte> b = new Sort<>();
        assertArrayEquals(new Byte[]{-3, 0, 3, 4, 7, 7, 8, 10, 12},b.sort(new Byte[]{ 8, 0, 4, 7, 3, 7, 10, 12, -3},0,8));
        assertArrayEquals(new Byte[]{-3, -3, 0, 3, 4, 7, 7, 8, 10},b.sort(new Byte[]{ 8, 0, 4, 7, 3, 7, 10, -3, -3},0,8));
        assertArrayEquals(new Byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9},b.sort(new Byte[]{ 9, 8, 7, 6, 5, 4, 3, 2, 1},0,8));
        assertArrayEquals(new Byte[]{-3, -3, -3, 4, 4, 4, 4, 5, 5},b.sort(new Byte[]{ 5, 4, 4, -3, -3, -3, 4, 4, 5},0,8));

        Sort<Float> f = new Sort<>();
        assertArrayEquals(new Float[]{-3.0F, 0.0F, 3.0F, 4.0F, 7.0F, 7.0F, 8.0F, 10.0F, 12.0F},f.sort(new Float[]{ 8.0F, 0.0F, 4.0F, 7.0F, 3.0F, 7.0F, 10.0F, 12.0F, -3.0F},0,8));
        assertArrayEquals(new Float[]{1.2F,2.3F,4.5F,5.6F,7.8F},f.sort(new Float[]{2.3F,4.5F,7.8F,1.2F,5.6F},0,4));
        assertArrayEquals(new Float[]{1.2F,2.2F,3.2F,4.2F,5.2F},f.sort(new Float[]{5.2F,4.2F,3.2F,2.2F,1.2F},0,4));
        assertArrayEquals(new Float[]{-2.2F,-1.2F,3.2F,4.2F,5.2F},f.sort(new Float[]{5.2F,4.2F,3.2F,-2.2F,-1.2F},0,4));

        Sort<Long> l = new Sort<>();
        assertArrayEquals(new Long[]{(long) 4,(long) 4,(long) 5,(long)649,},l.sort( new Long[]{(long)649,(long) 4,(long) 4,(long) 5},0,3));
        assertArrayEquals(new Long[]{(long) 400,(long) 4000,(long) 500000,(long)64900000},l.sort( new Long[]{(long)64900000,(long) 4000,(long) 400,(long) 500000},0,3));
        assertArrayEquals(new Long[]{(long) 4000000,(long) 40000000,(long) 50000000,(long)60000000},l.sort( new Long[]{(long)60000000,(long) 40000000,(long) 4000000,(long) 50000000},0,3));
        assertArrayEquals(new Long[]{(long) 4,(long) 40000000,(long) 40000000,(long)640000000},l.sort( new Long[]{(long)640000000,(long) 40000000,(long) 40000000,(long) 4},0,3));


        Cat barcik = new Cat("Barsik","0","Black","no");
        Cat mops = new Cat("Mops","4","Yellow","yes");
        Cat peace = new Cat("Peace","9","Black","yes");
        Cat jony = new Cat("Jony","1","White","no");
        Sort<String> o = new Sort<>();
        assertArrayEquals(new String[]{"Barsik","Jony","Mops","Peace"},o.sort(new String[]{barcik.name,mops.name,peace.name,jony.name},0,3));
        assertArrayEquals(new String[]{"0","1","4","9"},o.sort(new String[]{barcik.age,mops.age,peace.age,jony.age},0,3));
        assertArrayEquals(new String[]{"Black","Black","White","Yellow"},o.sort(new String[]{barcik.color,mops.color,peace.color,jony.color},0,3));
        assertArrayEquals(new String[]{"no","no","yes","yes"},o.sort(new String[]{barcik.swim,mops.swim,peace.swim,jony.swim},0,3));


    }
}