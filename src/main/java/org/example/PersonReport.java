package org.example;

import java.util.LinkedList;

public class PersonReport {
    private LinkedList<Call> list = new LinkedList();
    private long timeRate;
    private int rate = 0;
    private long number;
    private double allCost = 0;

    public PersonReport(int rate, long number) {
        this.rate = rate;
        this.number = number;
        switch (rate) {
            case (06):
                timeRate = 300 * 60;
                allCost = 100;
                break;
            case (03):
                timeRate = 0L;
                break;
            case (11):
                timeRate = 100 * 60;
                break;
        }
    }

    public void addToList(Call call) {
        //Получить время звонка в
        call.setPriceCall(findCost(call));

        list.add(call);
        // после каждого добавления элемента провести расчет для каждой минуты по тарифному плану и добавить в строку 6

    }

    private double findCost(Call call) {
        double cost = 0;
        switch (rate) {
            case (06):
                cost = costRate06(call);
                break;
            case (03):
                cost = costRate03(call);
                break;
            case (11):
                cost = costRate11(call);
                break;
        }
        return cost;
    }

    private double costRate06(Call call) {
        if (timeRate - call.getDifference() >= 0) {
            timeRate -= call.getDifference();
            return 0;
        } else {
            allCost += (double) call.getDifference() / 60 * 1 + (double) timeRate / 60 * 1;
            timeRate = 0;
            return (double) call.getDifference() / 60 * 1;
        }
    }

    private double costRate03(Call call) {
        allCost += (double) call.getDifference() / 60 * 1.5;
        return (double) call.getDifference() / 60 * 1.5;
    }

    private double costRate11(Call call) {
        // Входящие - бесплатно, исходящие - первые 100 минут по 0.5р/минута, после по тарифу "поминутный".
        if (call.getInOutColl() == 02) {
            // бесплатно
            return 0;
        }
        if (call.getInOutColl() == 01) {
            //Платно
            if (timeRate - call.getDifference() >= 0) {
                timeRate -= call.getDifference();
                allCost += (double) call.getDifference() / 60 * 0.5;
                return (double) call.getDifference() / 60 * 0.5;
            } else {
                allCost += (double) call.getDifference() / 60 * 1.5 + (double) timeRate / 60 * 1.5;
                timeRate = 0;
                return (double) call.getDifference() / 60 * 1.5;
            }
        }
        return 0;
    }


    @Override
    public String toString() {
        for (Call c : list) {
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

    public LinkedList<Call> getList() {
        return list;
    }

    public double getAllCost() {
        return allCost;
    }
}
