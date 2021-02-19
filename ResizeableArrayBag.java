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

    /** adds the contents of an input bag to the original bag
    @param input1 The bag that you would like add to the original bag
    @return contents of both bags in the form of a bag*/
    public BagInterface<T> union(BagInterface<T> input1/*, BagInterface<T> input2*/)
    {   
        /* First Attempt***********

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
        */


        //Attempt 2*********

        ResizeableArrayBag<T> tempBag1 = (ResizeableArrayBag<T>) input1;
        BagInterface<T> tempBag2 = new ResizeableArrayBag<T>();
        for (int i = 0; i<numberOfEntries; i++)
        {
            tempBag2.add(bag[i]);
        }
        for (int j = 0; j<tempBag1.getCurrentSize();j++)
        {
            tempBag2.add(tempBag1.bag[j]);
        }

        return tempBag2;
        
    }

    /** adds the contents of the items that both the original and the input bag share. If they share the same item multiple times, it adds the least amount (i.e. bag 1 has item a twice and bag 2 has item b three times - it will add it only twice, since they don't both share it three times)
    @param input1 The bag that you would like to compare to the original bag
    @return contents that both bags share in the form of a bag*/
    
    public BagInterface<T> intersection(BagInterface<T> input1 /*, BagInterface<T> input2*/)
    {
        /* Attempt 1*****


        int counter = 0;
        int counter2 = 0;
        @SuppressWarnings("unchecked")
        T[] tempBag1 = (T[])new Object[input1.getCurrentSize()];
        @SuppressWarnings("unchecked")
        T[] tempBag2 = (T[])new Object[input2.getCurrentSize()];
        tempBag1 = input1.toArray();
        tempBag2 = input2.toArray();
        @SuppressWarnings("unchecked")
        T[] tempBag3 = (T[])new Object[tempBag1.length+tempBag2.length]; 
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
        ResizeableArrayBag<T> last = new ResizeableArrayBag<T>(counter2)
        return last;
        */


        //Attempt 2*********

        BagInterface<T> bag1 = new ResizeableArrayBag<>();
        BagInterface<T> bag2 = new ResizeableArrayBag<>();
        ResizeableArrayBag<T> bag3 = (ResizeableArrayBag<T>)input1;
        for (int i = 0; i<bag3.numberOfEntries;i++)
        {
            bag2.add(bag3.bag[i]);
        }
        for (int j = 0; j< getCurrentSize();j++)
        {
            if(bag2.contains(bag[j]))
            {
                bag1.add(bag[j]);
                bag2.remove(bag[j]);
            }
        }
        return bag1;
    }

    /** adds the difference of the amount of times a certain item occurs in both bags (i.e. if bag 1 has an item occur five times and bag 2 has the item occur three times, it will return that item in a bag two times)
    @param input1 The bag that you would like add to the original bag
    @return the difference of the contents that both bag 1 and 2 share in the form of a bag*/
    public BagInterface<T> difference(BagInterface<T> input1)
    {
        //Attempt 1********

        BagInterface<T> bag1 = new ResizeableArrayBag<>();
        ResizeableArrayBag<T> bag2 = (ResizeableArrayBag<T>)input1;
        for(int i = 0; i < numberOfEntries; i++)
        {
            bag1.add(bag[i]);
        }
        for(int j = 0; j < bag2.getCurrentSize(); j++)
        {
            if(bag1.contains(bag2.bag[j]))
            {
                bag1.remove(bag2.bag[j]);
            }
        }
        return bag1;
    }
}
