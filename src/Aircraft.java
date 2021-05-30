public class Aircraft {

    private int weight, flightTime, delay;
    private String name;
    private  final int schFlightTime;
    private int maxDelay;

    public Aircraft(int weight, int flightTime, String name){
        this.weight =weight;
        this.flightTime= flightTime;
        this.name = name;
        this.schFlightTime=flightTime;

        if(weight==1){
            maxDelay=45;
        }
        else if(weight == 2){
            maxDelay= 30;
        }
        else maxDelay=15;

    }

    public int getMaxDelay(){
        return  maxDelay;
    }

    public void changeMaxDelay(int delay ){
        this.maxDelay-=delay;
    }

    public int getSchFlightTime(){return schFlightTime;}

    public int getWeight() {
        return weight;
    }

    public int getFlightTime() {
        return flightTime;
    }

    public void setFlightTime() {
        this.flightTime+=delay;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
        setFlightTime();
    }

    public String getName() {
        return name;
    }

}
