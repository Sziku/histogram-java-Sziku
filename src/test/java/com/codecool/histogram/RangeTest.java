package com.codecool.histogram;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {

    Range rangeTest;
    int from = 0;
    int to = 10;
    @Test
    void rangeArgumentTest(){
        assertThrows(IllegalArgumentException.class, () -> {rangeTest = new Range(to,from);});
        assertThrows(IllegalArgumentException.class, () -> {rangeTest = new Range(-1,from);});


    }

    @Test
    void isInRangeTest(){
        String sortText = "123456";
        String longText = "123456789101112";
        String noText = "";

        rangeTest = new Range(from,to);

        assertTrue(rangeTest.isInRange(sortText));
        assertTrue(rangeTest.isInRange(noText));
        assertFalse(rangeTest.isInRange(longText));
    }

    @Test
    void toStringTest(){
        rangeTest = new Range(from,to);
        assertEquals("0  - 10", rangeTest.toString());
    }

    @Test
    void equalsTest(){
        rangeTest = new Range(from, to);
        Range cloneTest = new Range(from, to);

        assertEquals(cloneTest.equals(rangeTest), rangeTest.equals(cloneTest));
        assertEquals(cloneTest.hashCode(), rangeTest.hashCode());
    }



}
