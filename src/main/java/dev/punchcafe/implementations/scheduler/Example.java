package dev.punchcafe.implementations.scheduler;

public class Example {

    /**
     * Prints 'hello world!' to the system every 5 seconds.
     */
    public static void main(String[] args) {
        ReoccuringTime reoccuringTime = new ReoccuringTime();
        reoccuringTime.setReoccuringSeconds(5);
        SchedulerMethod method = ()-> System.out.println("hello world!");
        Scheduler scheduler = Scheduler.newScheduler();
        scheduler.schedule(method, reoccuringTime);
    }
}
