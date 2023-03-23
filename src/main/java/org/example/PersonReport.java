package org.example;

import java.util.LinkedList;

public class PersonReport {
    private String number = "";
//    private String typeOfColl = "";
//    private String startTimeAndDateOfColl = "";
//    private String endTimeAndDateOfColl = "";
//    private String typeOfRate = "";
    private LinkedList<String[]> list = new LinkedList();

    private String[] TimeAndDateOfColl = new String[5];

    public void addToList(String[] arrayOfString) {
        list.add(arrayOfString);
    }

    @Override
    public String toString() {
        for (String[] s :list){
            for (String sed: s){
                System.out.print(sed + ",");
            }
            System.out.println();
        }
        return null;
    }
}
