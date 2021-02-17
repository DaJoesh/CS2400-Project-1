public class ResizeableArrayBag<T> implements BagInterface<T>
{
    private final T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private int MAX_CAPACITY = 10000;
    /** creates an empty bad with given default capacity */
    public ResizeableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    } //end constructor
    /** creates an empty bag with given initial capacity.
        @param camacity the integer capacity desired */
    public ResizeableArrayBag(int desiredCapacity)
    {
        if(desiredCapacity <= MAX_CAPACITY)
        {
            //cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[])new Object[desiredCapacity]; //unchecked cast
            bag=tempBag;
            numberOfEntries=0;
            integrityOK=true;
        }
        else
        throw new IllegalStateException("Attempt to create bag whose capacity exceeds allowed maximum.");
    } // end constructor
    // throws an exception if this object is not initialized
    private void checkIntegrity()
    {
        if(!integrityOK)
        {
            throw new SecurityException("ArrayBag object is corrupt");
        }
    }// end checkIntegrity

    /** Adds a new entry to this bag.
    @param newEntry the object to be added as a new entry.
    @return True is the addition is successful, false if not */
    public boolean add(T newEntry)
    {
        boolean result = true;
        if (isFull())
        {
            result = false;
        }
        else
        { //Assertion: result is true here
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
        } //end if
        return result;
    } // end add
    /** Retrieves all entries that are in this bag
    @return A newly allocated array of all the entrie in this bag */
    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        for (int i=0; i < numberOfEntries; i++)
        {
            result[i]=bag[i];
        }// end for
        return result;
    }// end toArray

    public boolean isFull()
    {
        return numberOfEntries == bag.length;
    }
    
    /** Gets the current number of entried in this bag.
    @return The integer number of entried currently in this bag */
    public int getCurrentSize()
    {
        return numberOfEntries;
    } // end getCurrentSize
    
    /** Sees whether this bag is empty.
    @retun True id this bag is empty, false is not */
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }// end isEmpty

    /** Counts the number of times a given entry appreas in this bag
    @param anEntry The entry to be counted
    @return The number of times anEntry appears in the bag */
    public int getFrequencyOf(T anEntry)
    {
        checkIntegrity();
        int counter = 0;
        for(int i=0; i < numberOfEntries; i++)
        {
            if(anEntry.equals(bag[i]))
            {
                counter++;
            } //end if
        } // end for
        return counter;
    } // end getFrequencyOf

    /** Removes one occurence of a given entry from this bag
    @param anEntry The entry to be removed
    @return True if the removeal was successful, false if not */
    public boolean remove(T anEntry)
    {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }// end remove

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

    /** Removes all entries from this bag */
    public void clear()
    {
        while (!isEmpty())
        {
            remove();
        }
    }// end clear

    /** tests whether this bad contains a given entry
    @param anEntry The entry to locate
    @return True if this bag contains anEntry, false otherwize */
    public boolean contains(T anEntry)
    {
        checkIntegrity();
        return getIndexOf(anEntry) > -1; // or >= 0
    } //end contains

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

    public ResizeableArrayBag<T> intersection(BagInterface<T> input1, BagInterface<T> input2)
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
            tempBag3[counter]=tempBag2[j];
            counter++;
        }
        for (int k = 0; k<tempBag3.length;k++)
        {   
            for(int l = 0; l<tempBag3.length;l++)
            {
                if (l!=k)
                {
                    if(tempBag3[l].equals(tempBag3[k]))
                    {
                    System.out.println("yes it equals");
                    //tempBag3.removeEntry(k);
                    }
                }
            }
        }
        /*
        while(j < tempBag2.length)
        {
            if(tempBag3[counter].equals(tempBag2[j]))
            {
                last.add(tempBag2[j]);
                System.out.println("tempBag2 into tempBag3");
            }
            j++;
            counter++;
            System.out.println("counter is " + counter);
        }
        */
        return last;
        /*ResizeableArrayBag<T> temp = new ResizeableArrayBag<T>(input1.getCurrentSize()+input2.getCurrentSize());
        temp = temp.union(input1, input2);
        for(int i = 0; i<temp.getCurrentSize();i++)
        {   
            for(int j = 0; j<temp.getCurrentSize();j++)
            {
                if(j!=temp)
            }
            counter++;
        }*/


    }

    public BagInterface<T> difference(BagInterface<T> input1, BagInterface<T> input2)
    {
        return input1;
    }
}
