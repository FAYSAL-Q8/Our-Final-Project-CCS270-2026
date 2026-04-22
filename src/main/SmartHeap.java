import java.util.ArrayList;

public class SmartHeap {
    private ArrayList<Task> heap = new ArrayList<>();
    private String strategy; // "SJF" or "EDF"

    public SmartHeap(String strategy) {
        this.strategy = strategy;
    }

    // --- CORE ALGORITHM: INSERT ---
    public void insert(Task task) {
        heap.add(task); // Add to the end
        heapifyUp(heap.size() - 1); // Bubble it up to the right spot
    }

    // --- CORE ALGORITHM: EXTRACT ---
    public Task extractNext() {
        if (heap.isEmpty()) return null;
        Task topTask = heap.get(0);
        Task lastTask = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastTask);
            heapifyDown(0); // Bubble it down to fix the tree
        }
        return topTask;
    }

    // --- HEAPIFY LOGIC ---
    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && isMoreUrgent(heap.get(index), heap.get(parent))) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heap.size() && isMoreUrgent(heap.get(left), heap.get(smallest))) smallest = left;
        if (right < heap.size() && isMoreUrgent(heap.get(right), heap.get(smallest))) smallest = right;

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    // The Greedy Scheduling Logic
    private boolean isMoreUrgent(Task t1, Task t2) {
        if (strategy.equals("SJF")) return t1.duration < t2.duration;
        return t1.deadline < t2.deadline; // Default to EDF
    }

    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public boolean isEmpty() { return heap.isEmpty(); }
}