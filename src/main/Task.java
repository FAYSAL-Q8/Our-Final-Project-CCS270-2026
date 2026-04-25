/*
Group Project : Smart Task Scheduler
Members:
FAYSAL ALIBRAHIM  ID: 2231165155
KHALED ALMARJAN   ID: 2231119438
OMAR   ALHAJRI    ID: 2231152612
Course code & section: CCS 270-02A
Supervised by Prof. Dr. Iyad Abu Doush
 */
/**
 * Represents a task in the scheduler.
 * Each task has a name, duration, and deadline.
 */
public class Task {
    String name;
    String priorityLevel;
    int deadline;
    int duration; // Execution time
/**
     * Constructor to initialize a task.
     * @param name Name of the task
     * @param duration Execution time (burst time)
     * @param deadline Deadline for the task
     */
    public Task(String name, String priorityLevel, int deadline, int duration) {
        this.name = name;
        this.priorityLevel = priorityLevel;
        this.deadline = deadline;
        this.duration = duration;
    }
}
