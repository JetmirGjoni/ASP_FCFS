import java.util.Comparator;

public class WeightCompare implements Comparator<Aircraft> {

    public int compare(Aircraft o1, Aircraft o2){
        int rez = 0;
        if(o1.getFlightTime() > o2.getFlightTime()){rez =1;}
        else if(o1.getFlightTime() < o2.getFlightTime()){rez =- 1;}

        return  rez;


    }
}
