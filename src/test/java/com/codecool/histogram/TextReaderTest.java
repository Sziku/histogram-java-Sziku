package com.codecool.histogram;

import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TextReaderTest {
    TextReader textReader;
    String emptyText = "src/test/resources/empty.txt";
    String testText = "src/test/resources/test.txt";
    String textText = "src/test/resources/text.txt";
    String wrongFile = "src/test/resources/wrong.txt";

    /*@BeforeEach
    void setup() {
        textReader = new TextReader("test.txt");
    }*/
    @Test
    void readTest() throws IOException {
        textReader = new TextReader(testText);
        String readed = textReader.read();
        assertEquals("Harry Potter and the Sorcerer's Stone\r\n", readed);
    }

    @Test
    void readEmptyTest() throws IOException {
        textReader = new TextReader(emptyText);
        String readed = textReader.read();
        assertEquals("", readed);
    }

    @Test
    void readBigTextTest() throws IOException {
        textReader = new TextReader(textText);
        String readed = textReader.read().substring(0,40);
        assertEquals("SORTING HAT SONG\r\n" +
                "\r\n" +
                "One thousand years a", readed);
    }

    @Test
    void readwrongFileTest()  {
        textReader = new TextReader(wrongFile);
        assertThrows(IOException.class, () -> {textReader.read();});
    }





}
