package QueuePackage;

import TreePackage.MinHeap;
import TreePackage.MinHeapInterface;

public class PriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T>
{
    private MinHeapInterface<T> minHeap;
    public PriorityQueue()
    {
        minHeap = new MinHeap<>();
    }

    public void add(T newEntry)
    {
        minHeap.add(newEntry);
    }

    public T remove()
    {
        return minHeap.removeMin();
    }
}
