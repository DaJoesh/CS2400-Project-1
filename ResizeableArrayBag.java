public class ResizeableArrayBag<T> implements BagInterface<T>
{
    private final T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private int MAX_CAPACITY = 10000;
    public ResizeableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    }
    public ResizeableArrayBag(int desiredCapacity)
    {
        if(desiredCapacity <= MAX_CAPACITY)
        {
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[])new Object[desiredCapacity];
            bag=tempBag;
            numberOfEntries=0;
            integrityOK=true;
        }
        else
        throw new IllegalStateException("Attempt to create bag whose capacity exceeds allowed maximum.");
    }

    private void checkIntegrity()
    {
        if(!integrityOK)
        {
            throw new SecurityException("ArrayBag object is corrupt");
        }
    }

    public boolean add(T newEntry)
    {
        boolean result = true;
        if (isFull())
        {
            result = false;
        }
        else
        {
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
        }
        return result;
    }

    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        for (int i=0; i < numberOfEntries; i++)
        {
            result[i]=bag[i];
        }
        return result;
    }

    public boolean isFull()
    {
        return numberOfEntries == bag.length;
    }

    public int getCurrentSize()
    {
        return numberOfEntries;
    }

    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }

    public int getFrequencyOf(T anEntry)
    {
        checkIntegrity();
        int counter = 0;
        for(int i=0; i < numberOfEntries; i++)
        {
            if(anEntry.equals(bag[i]))
            {
                counter++;
            }
        }
        return counter;
    }

    public boolean remove(T anEntry)
    {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }

    private int getIndexOf(T anEntry)
    {
        int where = -1;
        boolean found = false;
        int index = 0;

        while(!found && (index < numberOfEntries))
        {
            if(anEntry.equals(bag[index]))
            {
                found=true;
                where=index;
            }
            index++;
        }
        return where;
    }

    private T removeEntry(int givenIndex)
    {
        T result = null;

        if(!isEmpty() && (givenIndex >= 0 ))
        {
            result = bag[givenIndex];
            bag[givenIndex] = bag[numberOfEntries - 1];
            bag[numberOfEntries-1]=null;
            numberOfEntries--;
        }
        return result;
    }

    public T remove()
    {
        checkIntegrity();
        T result = removeEntry(numberOfEntries-1);
        return result;
    }

    public void clear()
    {
        while (!isEmpty())
        {
            remove();
        }
    }

    public boolean contains(T anEntry)
    {
        checkIntegrity();
        return getIndexOf(anEntry) > -1;
    }

    public ResizeableArrayBag<T> union(BagInterface<T> input1, BagInterface<T> input2)
    {   
        int counter = 0;
        @SuppressWarnings("unchecked")
        T[] tempBag1 = (T[])new Object[input1.getCurrentSize()];
        @SuppressWarnings("unchecked")
        T[] tempBag2 = (T[])new Object[input2.getCurrentSize()];
        tempBag1 = input1.toArray();
        tempBag2 = input2.toArray();
        @SuppressWarnings("unchecked")
        T[] tempBag3 = (T[])new Object[tempBag1.length+tempBag2.length];
        ResizeableArrayBag<T> last = new ResizeableArrayBag<T>(tempBag3.length);
            for (int i = 0; i<tempBag1.length;i++)
            {   
                tempBag3[i] = tempBag1[i];
                counter++;
            }
            for (int j = 0; j<tempBag2.length;j++)
            {
                tempBag3[counter] = tempBag2[j];
                counter++;
            }
            for (int k = 0; k<tempBag3.length;k++)
            {
                last.add(tempBag3[k]);
            }
        return last;
        
    }

    public BagInterface<T> intersection(BagInterface<T> input1, BagInterface<T> input2)
    {   
        int counter = 0;
        @SuppressWarnings("unchecked")
        T[] tempBag1 = (T[])new Object[input1.getCurrentSize()];
        @SuppressWarnings("unchecked")
        T[] tempBag2 = (T[])new Object[input2.getCurrentSize()];
        tempBag1 = input1.toArray();
        tempBag2 = input2.toArray();
        ResizeableArrayBag<T> last = new ResizeableArrayBag<T>(tempBag3.length);

        int i = 0;
        int j = 0;
        Set<Integer> temp = new HashSet<>();
        while(i < tempBag1.length)
        {
            temp.add(tempBag1[i++])
        }
        while(i < tempBag2.length)
        {
            if(temp.contains(tempBag2[j]))
            {
                // // last.add(tempBag2[j]);
            }
            j++;
        }
        return last;

    }

    public BagInterface<T> difference(BagInterface<T> input1, BagInterface<T> input2)
    {
        return input1;
    }
}
