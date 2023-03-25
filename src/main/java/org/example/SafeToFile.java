package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SafeToFile {
    private HashMap<Long, PersonReport> hashMap;

    public SafeToFile(HashMap<Long, PersonReport> hashMap, String way) {
        this.hashMap = hashMap;
        safe(way);
    }

    private void safe(String way){
            for(Map.Entry<Long, PersonReport> entry: hashMap.entrySet()) {

            String newWay = way+entry.getValue().getNumber()+".txt";
            PersonReport personReport = entry.getValue();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(newWay))){

                bw.write(String.format("Tariff index: %d \n", personReport.getRate()));
                bw.write("----------------------------------------------------------------------------");
                bw.newLine();
                bw.write(String.format("Report for phone number %d:\n", personReport.getNumber()));
                bw.write("----------------------------------------------------------------------------");
                bw.newLine();
                bw.write(String.format("| %-9s | %-19s | %-19s | %-9s | %-4s |%n",
                        "Call Type","Start Time","End Time","Duration","Cost"));
                bw.write("----------------------------------------------------------------------------");
                bw.newLine();
                for (Coll coll: personReport.getList()){
                    String startTime = String.format("%tY-%<tm-%<td %<tT",
                            coll.getStartColl());
                    String endTime = String.format("%tY-%<tm-%<td %<tT",
                            coll.getEndColl());
                    long hour = coll.getDifference() / 3600,
                            min = coll.getDifference() / 60 % 60,
                            sec = coll.getDifference() / 1 % 60;
                    String duration = String.format("%02d:%02d:%02d", hour, min, sec);

                    Formatter formatter = new Formatter(Locale.US);
                    bw.write(String.format("| %9d | %-19s | %-19s | %-9s | %-4s | %n",
                            coll.getInOutColl(),
                            startTime,
                            endTime,
                            duration,
                            formatter.format("%.2f", coll.getPriceCall())
                    ));
                }
                Formatter formatter = new Formatter(Locale.US);
                bw.write("----------------------------------------------------------------------------");
                bw.newLine();
                bw.write(String.format("|                                           Total Cost: | %9s rubles |\n",
                        formatter.format("%.2f", personReport.getAllCost())
                        ));
                bw.write("----------------------------------------------------------------------------");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }



    }
}
