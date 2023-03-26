package org.example;

import java.util.Comparator;

public class ComparatorCall implements Comparator<Call> {
    @Override
    public int compare(Call o1, Call o2) {
        if (o1.getInOutColl() == o2.getInOutColl()){
            if (o1.getStartColl().after(o2.getStartColl())){
                return 1;
            } else return -1;
        } if (o1.getInOutColl() > o2.getInOutColl()){
            return 1;
        }if (o1.getInOutColl() < o2.getInOutColl()){
            return -1;
        }
        return 0;
    }

//        число больше 0 -> объект с которым сравнивают больше текущего
//        число равно 0 -> объекты одинаковые
//        число меньше 0 -> объект с которым сравнивают меньше текущего
}
