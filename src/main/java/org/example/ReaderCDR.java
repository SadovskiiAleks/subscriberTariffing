package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReaderCDR {
    private BufferedReader br = null;
    private String line = "";

    private HashMap<Long, PersonReport> hashMap = new HashMap<>();

    public ReaderCDR readFileToClass(String fileWay, String splitBy) {
        try {
            br = new BufferedReader(new FileReader(fileWay));
            while ((line = br.readLine()) != null) {
                String[] arrayFromFile = line.split(splitBy);
                CallBuilder callBuilder = new CallBuilder(arrayFromFile);
                Call call = callBuilder.build();

                Long number = call.getNumber();

                if (hashMap.containsKey(number)) {
                    // нужно получить по ключю объект и добавить элемент в массив
                    hashMap.get(number).addToList(call);

                } else {
                    // Нужно создать объект и добавить элемент
                    PersonReport personReport = new PersonReport(call.getRate(), call.getNumber());
                    personReport.addToList(call);
                    hashMap.put(number, personReport);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    public HashMap<Long, PersonReport> getHashMap(){
        return hashMap;
    }

    @Override
    public String toString() {
        for (Map.Entry entry : hashMap.entrySet())
            entry.getValue().toString();
        return null;
    }

}
