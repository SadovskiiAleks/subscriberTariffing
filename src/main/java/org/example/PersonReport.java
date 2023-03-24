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

    // Устнановить тариф

    public void addToList(String[] arrayOfString) {


        list.add(arrayOfString);

        // после каждого добавления элемента провести расчет для каждой минуты по тарифному плану и добавить в строку 6

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
