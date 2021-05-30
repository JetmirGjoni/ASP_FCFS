import java.util.*;

public class AircraftManagerFCFS  {

    public static List<Aircraft>  aircrafts = new ArrayList<Aircraft>();
    public static int conflicts;
    public static int conflictCost;

    public AircraftManagerFCFS(){
        initAircraft();
        checkTime();
    }

    public void initAircraft(){
        aircrafts.add(new Aircraft(1,1600,"NA"));
        aircrafts.add(new Aircraft(3,1601,"FF"));
        aircrafts.add(new Aircraft(2,1607,"KK"));
        aircrafts.add(new Aircraft(2,1601,"NN"));
        aircrafts.add(new Aircraft(2,1604,"LL"));
        aircrafts.add(new Aircraft(1,1600,"AA"));
        aircrafts.add(new Aircraft(3,1607,"JJ"));
        aircrafts.add(new Aircraft(2,1601,"PP"));
        aircrafts.add(new Aircraft(1,1600,"NB"));
        aircrafts.add(new Aircraft(3,1601,"FG"));
        aircrafts.add(new Aircraft(2,1603,"KL"));
        aircrafts.add(new Aircraft(2,1601,"NF"));
        aircrafts.add(new Aircraft(2,1604,"LA"));
        aircrafts.add(new Aircraft(1,1600,"AV"));
        aircrafts.add(new Aircraft(3,1607,"JP"));
        aircrafts.add(new Aircraft(2,1647,"PJ"));
        sort();
    }

    public static void sort(){
        WeightCompare weightCompare= new WeightCompare();
        Collections.sort(aircrafts,weightCompare);
    }

    public void checkTime(){
        for(int i = 0 ; i < aircrafts.size() ; i ++){
            for(int j = i+1 ; j < aircrafts.size() ; j++){
                int difference = Math.abs((aircrafts.get(i).getFlightTime()-aircrafts.get(j).getFlightTime()));
                if(difference < 3){
                    if(difference==2){
                        delay(aircrafts.get(i), aircrafts.get(j), 1);
                    }
                    else if(difference ==1){
                        delay(aircrafts.get(i), aircrafts.get(j),2);
                    }
                    else delay(aircrafts.get(i), aircrafts.get(j),3);

                    addConflict();
                    checkTime();
                    sort();
                }
            }
        }
    }

    public void addConflict(){
        conflicts++;
    }

    public static void setConflictCost(Aircraft a){

            if(a.getWeight()==3){
                    conflictCost+=30*(a.getFlightTime()-a.getSchFlightTime());
            }
            else if(a.getWeight()==2){
                    conflictCost+=20*(a.getFlightTime()-a.getSchFlightTime());
            }
            else{
                conflictCost+=10*(a.getFlightTime()-a.getSchFlightTime());
            }
        }

        public void addDelay(Aircraft a, int delay){
        a.setDelay(delay);
        a.changeMaxDelay(delay);
        }

    public  void delay(Aircraft a, Aircraft b, int delay){
         if(a.getFlightTime() < b.getFlightTime()){
             if(b.getMaxDelay() -(b.getFlightTime()-b.getSchFlightTime()) >0) {
                 addDelay(b,delay);
             }
             else if(a.getMaxDelay()-(a.getFlightTime()-b.getSchFlightTime())>0){
                 addDelay(a,delay);
             }
             else if(a.getMaxDelay() < b.getMaxDelay()){
                 addDelay(b,delay);
             }
             else{
                 addDelay(a,delay);
             }
        }

        else if(a.getFlightTime() > b.getFlightTime()){
            if(a.getMaxDelay()-(a.getFlightTime()-b.getSchFlightTime())>0) {
                addDelay(a, delay);
            }
            else if(b.getMaxDelay() -(b.getFlightTime()-b.getSchFlightTime()) >0){
                addDelay(b,delay);
            }
            else if(a.getMaxDelay() > b.getMaxDelay()){
                addDelay(a,delay);
            }
            else{
                addDelay(b,delay);
            }
        }

        else {
                if(a.getWeight() < b.getWeight()) {
                    if (a.getMaxDelay() - (a.getFlightTime() - b.getSchFlightTime()) > 0) {
                        addDelay(a, delay);
                    }
                    else if (b.getMaxDelay() -(b.getFlightTime()-b.getSchFlightTime()) >0){
                        addDelay(b,delay);
                    }
                    else if(a.getMaxDelay()>b.getMaxDelay()){
                        addDelay(a,delay);
                    }
                    else{
                        addDelay(b,delay);
                    }
                }

                else {
                    if(b.getMaxDelay() -(b.getFlightTime()-b.getSchFlightTime()) >0) {
                        addDelay(b, delay);
                    }
                    else if(a.getMaxDelay() - (a.getFlightTime() - b.getSchFlightTime()) > 0){
                        addDelay(a,delay);
                    }
                    else if(a.getMaxDelay() < b.getMaxDelay()){
                        addDelay(b,delay);
                    }
                    else{
                        addDelay(a,delay);
                    }
                }
         }
    }

    public static void main(String[] args) {
        new AircraftManagerFCFS();
       sort();

       for(Aircraft a : aircrafts){
           setConflictCost(a);
       }
        System.out.println("-------------------------------------------------\n"+"After Sorting");
        for(Aircraft a : aircrafts){
            System.out.println("Flight Name: "+ a.getName() +" | Scheduled Flight Time: "+a.getSchFlightTime()+ " | Actual Flight Time : "+ a.getFlightTime() +
                    "|Wheight: "+a.getWeight() +" |Delay left:" + a.getMaxDelay() );
        }
        System.out.println("Conflicts:" + conflicts +" | With a cost of:"+ (conflictCost));
    }
}