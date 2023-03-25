package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class PersonReport {
    private LinkedList<Coll> list = new LinkedList();
    private long timeRate;
    private int rate = 0;
    private long number;
    private double allCost = 0;

    public PersonReport(int rate, long number) {
        this.rate = rate;
        this.number = number;
        switch (rate) {
            case (06):
                timeRate = 300*60;
                allCost = 100;
                break;
            case (03):
                timeRate = 0L;
                break;
            case (11):
                timeRate = 100*60;
                break;
        }
    }

    public void addToList(Coll coll) {
        //Получить время звонка в
        coll.setPriceCall(findCost(coll));

        list.add(coll);
        // после каждого добавления элемента провести расчет для каждой минуты по тарифному плану и добавить в строку 6

    }

    private double findCost(Coll coll) {
        double cost = 0;
        switch (rate) {
            case (06):
                cost = costRate06(coll);
                break;
            case (03):
                cost = costRate03(coll);
                break;
            case (11):
                cost = costRate11(coll);
                break;
        }
        return cost;
    }

    private double costRate06(Coll coll) {
        if (timeRate - coll.getDifference() >= 0) {
            timeRate -= coll.getDifference();
            return 0;
        } else {
            allCost += (double)coll.getDifference()/60 * 1 + (double)timeRate/60 * 1;
            timeRate = 0;
            return (double)coll.getDifference()/60 * 1;
        }
    }

    private double costRate03(Coll coll) {
        allCost += (double)coll.getDifference()/60 * 1.5;
        return (double)coll.getDifference()/60 * 1.5;
    }

    private double costRate11(Coll coll) {
        // Входящие - бесплатно, исходящие - первые 100 минут по 0.5р/минута, после по тарифу "поминутный".
        if (coll.getInOutColl() == 02) {
            // бесплатно
            return 0;
        } if (coll.getInOutColl() == 01) {
            //Платно
            if (timeRate - coll.getDifference() >= 0) {
                timeRate -= coll.getDifference();
                allCost += (double)coll.getDifference()/60 * 0.5;
                return (double)coll.getDifference()/60 * 0.5;
            } else {
                allCost += (double)coll.getDifference()/60 * 1.5 + (double)timeRate/60 * 1.5;
                timeRate = 0;
                return (double)coll.getDifference()/60 * 1.5;
            }
        }
        return 0;
    }


    @Override
    public String toString() {
        for (Coll c : list) {
            c.toString();
        }
        return null;
    }

    public long getNumber() {
        return number;
    }

    public int getRate() {
        return rate;
    }

    public LinkedList<Coll> getList(){
        return list;
    }

    public double getAllCost() {
        return allCost;
    }
}
