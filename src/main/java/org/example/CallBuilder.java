package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CallBuilder {
    private long number = 0L;
    private int inOutColl = 0;
    private Date startColl = null;
    private Date endColl = null;
    private long difference = 0L;
    private int rate = 0;

    public CallBuilder(String[] arrayFromFile) {
        Date date = null;

        for (String string : arrayFromFile) {
            switch (string.length()) {
                case (2):
                    // Входящий исходящий 01, 02
                    // Тип тарифа 11, 06, 03
                    switch (string) {
                        case ("01"):
                        case ("02"):
                            inOutColl = Integer.parseInt(string);
                            break;
                        case ("11"):
                        case ("06"):
                        case ("03"):
                            rate = Integer.parseInt(string);
                            break;
                    }
                    break;
                case (11):
                    //Номер;
                    number = Long.parseLong(string);
                    break;
                case (14):
                    //  Установка дат
                    int min = 0;

                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                    Date parsingDate = null;

                    try {
                        parsingDate = df.parse(string);
                    } catch (ParseException e) {
                        System.out.println(df);
                    }

                    if (date == null) {
                        date = parsingDate;
                    } else {
                        if (date.after(parsingDate)) {
                            startColl = parsingDate;
                            endColl = date;
                        } else {
                            startColl = date;
                            endColl = parsingDate;
                        }
                    }
                    break;
            }
        }
    }

    public Call build() {
        if (number != 0L && inOutColl != 0 && startColl != null && endColl != null && rate != 0) {
            long diff = endColl.getTime() - startColl.getTime();
            difference = diff / 1000;

            Call call = new Call(number, inOutColl, startColl, endColl, difference, rate);
            return call;
        }
        return null;
    }
}
