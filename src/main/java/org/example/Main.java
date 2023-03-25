package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        // Считать строки файл
        ReaderCDR readerCDR = new ReaderCDR().readFileToClass("task/cdr.txt", ", ");

        new SafeToFile(readerCDR.getHashMap(),"reports/");

    }




}