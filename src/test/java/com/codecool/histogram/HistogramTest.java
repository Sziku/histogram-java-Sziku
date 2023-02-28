package com.codecool.histogram;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistogramTest {
    Histogram histogramTest = new Histogram();
    List<Range> testRange = new ArrayList<>() {{
        add(new Range(1, 3));
        add(new Range(4, 6));
        add(new Range(7, 9));
    }};

    @Test
    void generateRanges() {


        assertThrows(IllegalArgumentException.class, () -> histogramTest.generateRanges(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> histogramTest.generateRanges(1, -1));


        assertEquals(testRange, histogramTest.generateRanges(3, 3));
    }

    @Test
    void generate() {
        Map<Range, Integer> histogram = new HashMap<>();
        for(Map.Entry<Range, Integer> entry : histogram.entrySet()){
            assertEquals(histogram.get(entry.getKey()), histogramTest.generate("", testRange).get(entry.getKey()));
        }

        histogram.put(new Range(1, 3),3);
        for(Map.Entry<Range, Integer> entry : histogram.entrySet()){
            assertEquals(histogram.get(entry.getKey()), histogramTest.generate("a ab abc", testRange).get(entry.getKey()));
        }

        histogram.put(new Range(4, 6),1);
        for(Map.Entry<Range, Integer> entry : histogram.entrySet()){
            assertEquals(histogram.get(entry.getKey()), histogramTest.generate("a ab abc abcd abcdefghijklmnop", testRange).get(entry.getKey()));
        }

        assertThrows(IllegalArgumentException.class, () -> histogramTest.generate("1234",null));
        assertThrows(IllegalArgumentException.class, () -> histogramTest.generate(null,testRange));

    }

    @Test
    void getHistogram() {
        Map<Range, Integer> histogram = new HashMap<>();
        assertEquals(histogram, histogramTest.getHistogram());

        histogram.put(new Range(1, 3),3);
        histogramTest.generate("a ab abc", testRange);
        for(Map.Entry<Range, Integer> entry : histogram.entrySet()){
            assertEquals(histogram.get(entry.getKey()), histogramTest.getHistogram().get(entry.getKey()));
        }
    }

    @Test
    void HistogramBeforeGenerate() {
        assertEquals("", histogramTest.toString());
    }

    @Test
    void HistogramAfterGenerate() {
        histogramTest.generate("a ab abc", testRange);
        assertEquals("1  - 3 | ***\r\n", histogramTest.toString());
    }

    @Test
    void min() {
        List<Range> ranges = Arrays.asList(new Range(0, 1), new Range(2, 3), new Range(4, 6), new Range(7, 10));
        histogramTest.generate("a ab abc", ranges);
        assertEquals(1, histogramTest.min());
    }
    @Test
    void max() {
        histogramTest.generate("a ab abc", testRange);
        assertEquals(3, histogramTest.max());
    }
    @Test
    void HistogramAfterGenerateNoraml() {
        List<Range> ranges = Arrays.asList(new Range(0, 1), new Range(2, 3), new Range(4, 6), new Range(7, 10));
        histogramTest.generate("a ab abc", ranges);
        histogramTest.normalizeValues();
        assertEquals("0  - 1 | **************************************************\r\n" +
                "2  - 3 | ****************************************************************************************************\r\n", histogramTest.toString());
    }

}
