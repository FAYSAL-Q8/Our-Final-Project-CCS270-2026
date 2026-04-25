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
 * Represents a task used in scheduling.
 * Contains duration (SJF) and deadline (EDF).
 */
public class Task {
    String name;
    String priorityLevel;
    int deadline;
    int duration; // Execution time

    /**
     * Constructs a new Task object with the specified scheduling attributes.
     *
     * @param name          The unique string identifier for the task.
     * @param priorityLevel The general categorization of the task's importance (e.g., High, Medium, Low).
     * @param deadline      The strict time limit for task completion (used by the EDF algorithm).
     * @param duration      The total execution time required (used by the SJF algorithm).
     */
    public Task(String name, String priorityLevel, int deadline, int duration) {
        this.name = name;
        this.priorityLevel = priorityLevel;
        this.deadline = deadline;
        this.duration = duration;
    }
}
