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
    private int checkLine = 0;
    private HashMap<String, PersonReport> hashMap = new HashMap<>();

    public ReaderCDR readFileToClass(String fileWay, String splitBy) {
        try {
            br = new BufferedReader(new FileReader(fileWay));
            while ((line = br.readLine()) != null) {
                String[] arrayOfFile = line.split(splitBy);
                //Определить параметры
                for (String s : arrayOfFile) {
                    if (s.length() == 11 && s.toCharArray()[0] ==7) {
                        String number = s;
                        if (hashMap.containsKey(number)) {
                            // нужно получить по ключю объект и добавить элемент в массив
                            hashMap.get(number).addToList(arrayOfFile);
                            break;
                        } else {
                            // НУжно создать объект и добавить элемент
                            PersonReport personReport = new PersonReport();
                            personReport.addToList(arrayOfFile);
                            hashMap.put(number,personReport);
                        }
                    }
                }
                checkLine++;
                System.out.println("CheckLine= " + checkLine);
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

    @Override
    public String toString() {
        for (Map.Entry entry: hashMap.entrySet())
            entry.getValue().toString();
        return null;
    }

}
