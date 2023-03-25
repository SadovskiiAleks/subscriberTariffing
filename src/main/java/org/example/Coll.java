package org.example;

import java.util.Date;

public class Coll {
    private long number;
    private int inOutColl;
    private Date startColl;
    private Date endColl;
    private long difference;
    private int rate;

    private double priceCall = 0.0;

    public Coll(long number, int inOutColl, Date startColl, Date endColl, long difference, int rate) {
        this.number = number;
        this.inOutColl = inOutColl;
        this.startColl = startColl;
        this.endColl = endColl;
        this.difference = difference;
        this.rate = rate;
    }

    public long getNumber() {
        return number;
    }

    public int getInOutColl() {
        return inOutColl;
    }

    public Date getStartColl() {
        return startColl;
    }

    public Date getEndColl() {
        return endColl;
    }

    public int getRate() {
        return rate;
    }

    public long getDifference() {
        return difference;
    }


    public double getPriceCall() {
        return priceCall;
    }

    public void setPriceCall(double priceCall) {
        this.priceCall = priceCall;
    }

    @Override
    public String toString() {
        System.out.print(number + "  ");
        System.out.print(inOutColl + "  ");
        System.out.printf("%s %td.%<tm.%<tY %<tT  ", "Сегодняшняя дата:", startColl);
        System.out.printf("%s %td.%<tm.%<tY %<tT  ", "Сегодняшняя дата:", endColl);
        System.out.print(timeToString(difference)+ " ");
        System.out.print(rate + "  ");
        System.out.print("Цена- ");
        System.out.printf("%.2f\n", priceCall);
        System.out.println();
        return null;
    }

    private static String timeToString(long difference) {
        long hour = difference / 3600,
                min = difference / 60 % 60,
                sec = difference / 1 % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}
