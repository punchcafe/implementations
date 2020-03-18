package dev.punchcafe.implementations.scheduler;

public interface Scheduler {
    static Scheduler newScheduler(){
        return new SchedulerImp();
    }
    void schedule(SchedulerMethod method, Scheduling scheduling);
}
