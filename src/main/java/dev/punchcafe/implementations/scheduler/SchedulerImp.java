package dev.punchcafe.implementations.scheduler;

import java.time.LocalDateTime;

public class SchedulerImp implements Scheduler {
    @Override
    public void schedule(SchedulerMethod method, Scheduling scheduling) {
        final Thread thread = new Thread() {
            @Override
            public void run() {
                int startSecond = LocalDateTime.now().getSecond();

                if (scheduling instanceof ReoccuringTime) {
                    ReoccuringTime reoccuringTime = (ReoccuringTime) scheduling;
                    boolean triggeredWithinSecond = false;
                    int currentSecond = 0;
                    while (true) {
                        if(LocalDateTime.now().getSecond() != currentSecond){
                            currentSecond = LocalDateTime.now().getSecond();
                            triggeredWithinSecond = false;
                        }
                        if ((startSecond - currentSecond) % reoccuringTime.getReoccuringSeconds() == 0 && !triggeredWithinSecond) {
                            method.method();
                            triggeredWithinSecond = true;
                        }
                    }
                }
            }
        };
        thread.start();
    }
}
