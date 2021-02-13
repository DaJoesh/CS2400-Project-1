public class ArrayBagTest<T> implements BagInterface<T>
{
    private final T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private int MAX_CAPACITY = 10000;
    public ArrayBagTest()
    {
        this(DEFAULT_CAPACITY);
    }
    public ArrayBagTest(int desiredCapacity)
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

    public BagInterface<T> union(BagInterface<t>)
    {

    }

    public BagInterface<T> intersection(BagInterface<t>)
    {

    }

    public BagInterface<T> difference(BagInterface<t>)
    {

    }
   
}
