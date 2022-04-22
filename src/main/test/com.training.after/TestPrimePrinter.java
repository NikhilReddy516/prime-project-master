package com.training.test;

import com.training.after.PrimePrinter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;

public class TestPrimePrinter {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @Test
    public void testPrimePrinter() {
        String[] args = null;
        String filePath = "/Users/nikhil.reddy/Downloads/Workspace/moveLater/prime-project-master/expectedoutput.txt";
        PrimePrinter.main(args);
        String expectedOutput = readFile(filePath);
        Assert.assertEquals(expectedOutput,outContent.toString());
    }

    public String readFile(String filePath){
        File file = new File(filePath);
        byte[] bFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new String(bFile);
    }
}
