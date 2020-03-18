package dev.punchcafe.implementations.scheduler;

public class ReoccuringTime implements Scheduling {
    long reoccuringSeconds;

    public void setReoccuringSeconds(long millis){
        this.reoccuringSeconds = millis;
    }

    public long getReoccuringSeconds(){
        return this.reoccuringSeconds;
    }
}
