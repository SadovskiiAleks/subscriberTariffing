package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class SafeToFile {
    private HashMap<Long, PersonReport> hashMap;

    public SafeToFile(HashMap<Long, PersonReport> hashMap, String way) {
        this.hashMap = hashMap;
        safe(way);
    }

    private void safe(String way) {
        for (Map.Entry<Long, PersonReport> entry : hashMap.entrySet()) {
            //Сортировка данных перед печатью
            ComparatorCall comparatorCall = new ComparatorCall();
            entry.getValue().getList().sort(comparatorCall);

            String newWay = way + entry.getValue().getNumber() + ".txt";
            PersonReport personReport = entry.getValue();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(newWay))) {

                bw.write(String.format("Tariff index: %d \n", personReport.getRate()));
                bw.write("----------------------------------------------------------------------------");
                bw.newLine();
                bw.write(String.format("Report for phone number %d:\n", personReport.getNumber()));
                bw.write("----------------------------------------------------------------------------");
                bw.newLine();
                bw.write(String.format("| %-9s |     %-15s |     %-15s | %-9s | %-4s |%n",
                        "Call Type", "Start Time", "End Time", "Duration", "Cost"));
                bw.write("----------------------------------------------------------------------------");
                bw.newLine();
                for (Call call : personReport.getList()) {
                    String startTime = String.format("%tY-%<tm-%<td %<tT",
                            call.getStartColl());
                    String endTime = String.format("%tY-%<tm-%<td %<tT",
                            call.getEndColl());
                    long hour = call.getDifference() / 3600,
                            min = call.getDifference() / 60 % 60,
                            sec = call.getDifference() / 1 % 60;
                    String duration = String.format("%02d:%02d:%02d", hour, min, sec);

                    Formatter formatter = new Formatter(Locale.US);
                    bw.write(String.format("|    %02d     | %-19s | %-19s | %-9s | %-4s | %n",
                            call.getInOutColl(),
                            startTime,
                            endTime,
                            duration,
                            formatter.format("%.2f", call.getPriceCall())
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
