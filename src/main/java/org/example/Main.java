package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        // Считать строки файл
        ReaderCDR readerCDR = new ReaderCDR().readFileToClass("src/main/resources/task/cdr.txt", ", ");

        //Сохранить отчет
        new SafeToFile(readerCDR.getHashMap(), "reports/");
    }
}