package com.yakimtsov.ih.reader;

import com.yakimtsov.ih.exception.IncorrectFileException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TextReaderTest {
    TextReader reader = new TextReader();

    @Test
    public void readPositive() {
        String text = "";
        int actualLength = 845;
        try {
            text = reader.read("Data/text.txt");
        } catch (IncorrectFileException e) {
            fail(e.getMessage());
        }
        assertEquals(actualLength, text.length());
    }


    @Test(expectedExceptions = IncorrectFileException.class)
    public void readNonexistedFile() throws IncorrectFileException {
        reader.read("incorrect_file_name");
    }
}
