package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // Считать строки файл
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sad way to cdr.txt");
        System.out.println();
        String fileWay = scanner.nextLine();
        ReaderCDR readerCDR = new ReaderCDR().readFileToClass(fileWay, ", ");

        //Сохранить отчет
        System.out.println();
        System.out.println("Sad way to save reports");
        System.out.println();
        String wayReports = scanner.nextLine();
        //создать файл

        File directory = new File(wayReports+"\\reports\\");

        if (directory.mkdir()){
            System.out.println("Reports is created!");
        }
        else{
            System.out.println("reports already exists.");
        }
        new SafeToFile(readerCDR.getHashMap(), wayReports + "\\reports\\");
        System.out.println("Nays day!");
    }
}