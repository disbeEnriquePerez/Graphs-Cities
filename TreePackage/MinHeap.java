package TreePackage;

public final class MinHeap<T extends Comparable<? super T>> implements MinHeapInterface<T>
{
    private T[] heap;
    private int lastIndex;  // Index of last entry
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public MinHeap()
    {
        this(DEFAULT_CAPACITY);
    }

    public MinHeap(T[] entries)
    {
        this(entries.length);
        lastIndex = entries.length;

        // Copy given array to data field
        for (int index = 0; index < entries.length; index++)
        {
            heap[index + 1] = entries[index];
        }
        // create heap
        for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
            reheap(rootIndex);
    }

    public MinHeap(int initialCapacity)
    {
        if (initialCapacity < DEFAULT_CAPACITY)
        {
            initialCapacity = DEFAULT_CAPACITY;
        }
        else
        {
            checkCapacity(initialCapacity);
        }

        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
    }

    @Override
    public void add(T newEntry)
    {
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) < 0)
        {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        }
        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    }

    @Override
    public T removeMin()
    {
        T root = null;

        if(!isEmpty())
        {
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        }
        return root;
    }

    private void reheap(int rootIndex)
    {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;
        while(!done && (leftChildIndex <= lastIndex))
        {
            int smallerChildIndex = leftChildIndex; // Assume larger
            int rightChildIndex = 2 * rootIndex + 1;
            if ( (rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[smallerChildIndex]) < 0)
            {
                smallerChildIndex = rightChildIndex;
            }

            if(orphan.compareTo(heap[smallerChildIndex]) > 0)
            {
                // orphan is larger than the smallest child of the current node
                heap[rootIndex] = heap[smallerChildIndex];
                rootIndex = smallerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
                done = true;
        }
        heap[rootIndex] = orphan;
    }

    @Override
    public T getMin()
    {
        T root = null;
        if(!isEmpty())
            root = heap[1];
        return root;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public int getSize()
    {
        return lastIndex;
    }

    @Override
    public void clear()
    {
        while(lastIndex > -1)
        {
            heap[lastIndex] = null;
            lastIndex--;
        }
        lastIndex = 0;
    }

    private boolean checkCapacity(int capacity)
    {
        return true;
    }

    private void ensureCapacity()
    {

    }
}
