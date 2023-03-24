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
                String[] arrayFromFile = line.split(splitBy);
                String[] parsingArray = parsing(arrayFromFile);

                String number = parsingArray[0];

                if (hashMap.containsKey(number)) {
                    // нужно получить по ключю объект и добавить элемент в массив
                    hashMap.get(number).addToList(parsingArray);

                } else {
                    // Нужно создать объект и добавить элемент
                    PersonReport personReport = new PersonReport();
                    personReport.addToList(parsingArray);
                    hashMap.put(number, personReport);
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

    public String[] parsing(String[] arrayFromFile) {
        String[] parsingArray = new String[arrayFromFile.length];
        Long maxLong = 0L;
        // 0 Номер абонента
        // 1 Тип вызова
        // 2 Дата и время начала звонка (YYYYMMDDHH24MMSS)
        // 3 Дата и время окончания звонка
        // 4 Тип тарфа (см. ниже)

        for (String s : arrayFromFile) {
            //Проверить каждый элемент
            switch (s.length()) {
                case (2):
                    // Входящий исходящий //01, 02
                    // Тип тарифа //11, 06, 03
                    switch (s) {
                        case ("01"):
                        case ("02"):
                            parsingArray[1] = s;
                            break;
                        case ("11"):
                        case ("06"):
                        case ("03"):
                            parsingArray[4] = s;
                            break;
                    }
                    break;
                case (11):
                    //Номер;
                    //73734435243,
                    parsingArray[0] = s;
                    break;
                case (14):
                    //Код1;
                    //  2023-07-25 14.14.48, 2023-07-25 14.21.10
                    if (maxLong == 0L){
                        maxLong = Long.parseLong(s);
                    } else {
                        if (maxLong > Long.parseLong(s)){
                            parsingArray[2] = s;
                            parsingArray[3] = maxLong.toString();
                        } else {
                            parsingArray[2] = maxLong.toString();
                            parsingArray[3] = s;
                        }
                    }
                    break;
                default:
                    //Код по умолчанию;
                    break;
            }
        }

        return parsingArray;
    }

    @Override
    public String toString() {
        for (Map.Entry entry : hashMap.entrySet())
            entry.getValue().toString();
        return null;
    }

}
