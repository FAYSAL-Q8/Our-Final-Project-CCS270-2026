public class Task {
    String name;
    String priorityLevel;
    int deadline;
    int duration; // Execution time

    public Task(String name, String priorityLevel, int deadline, int duration) {
        this.name = name;
        this.priorityLevel = priorityLevel;
        this.deadline = deadline;
        this.duration = duration;
    }
}