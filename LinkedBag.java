public class LinkedBag<T> implements BagInterface<T>
{
    private Node firstNode;
    private int numberOfEntries;

    private class Node
    {
        private T data;
        private Node next;
        private Node(T dataPortion)
        {
            this(dataPortion, null);
        }
        /** creates a node 
            @param T dataPortion the information inside of the node
            @param nextNode the node following the previous node */
        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }//end Node

        /** retieved data in T node */
        private T getData()
        {
            return data;
        }// end getData

        /** sets the data in the new node
            @param newData gets data from note and sets it in data */
        private void setData(T newData)
        {
            data = newData;
        }//end setData

        /** finds the next node In the bag */
        private Node getNextNode()
        {
            return next;
        }//end getNextNode
    }//end class Node

    /** adds a new entry to the Bag
        @param newEntry what is being added to the bag
        @return true if new entry was added to the bag, if not, return false */
    public boolean add(T newEntry)
    {
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;
        
        firstNode = newNode;
        numberOfEntries++;

        return true;
    }//end add

    /** removes and entry from the bag
        @return the bag without what was removed */
    public T remove()
    {
        T result = null;
        if(firstNode != null)
        {
            result = (T) firstNode.getData();
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
        }//end if
        return result;
    }//end remove

    /* finds the refrence to a specified node
        @param the entry your are trying to find the refence for
        @return the refrence to the specified node */
    private Node getRefrenceTo(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;
        while(!found && (currentNode != null))
        {
            if(anEntry.equals(currentNode.getData()))
            {
                found = true;
            }
            else
            {
                currentNode = currentNode.getNextNode();
            }//end if
        }//end while
        return currentNode;
    }//end getRefrenceTo
    
    /* removes a specified entry
        @param anEntry the entry that is being removed
        @return true if the entry was removes, if not return false */
    public boolean remove(T anEntry)
    {
        boolean result = false;
        Node nodeN = getRefrenceTo(anEntry);

        if(nodeN != null)
        {
            nodeN.setData(firstNode.getData());
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
            result = true;
        }//end if
        return result;
    }//end remove
    
    /** checks to see if bag is empty
        @return True is bag is emptry, if not return false */
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }//end isEmpty
    
    /** finds the size of the bag
        @return numberOfEntries the number of entries in the bag */
    public int getCurrentSize()
    {
        return numberOfEntries;
    }//end getCurrentSize

    /** clears the bag of all entries */
    public void clear()
    {
        while(!isEmpty())
        {
            remove();
        }//end while
    }//end clear
    
    /** get the amount of times an entry occers in the bag
        @param the entry being searched
        @return the amount of times the entry is found */
    public int getFrequencyOf(T anEntry)
    {
        int frequency = 0;
        int counter = 0;
        Node currentNode = firstNode;
        while((counter < numberOfEntries) && (currentNode != null))
        {
            if(anEntry.equals(currentNode.getData()))
            {
                frequency++;
            }//end if
            counter++;
            currentNode = currentNode.getNextNode();
        }//end while
        return frequency;
    }//end getFrequencyOf

    /** checks if the bag contains a specified entry
        @param anEntry the entr
        @return True if the bag contains the entry, if not return false */
    public boolean contains(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;
        while(!found && (currentNode!=null))
        {
            if(anEntry.equals(currentNode.getData()))
            {
                found = true;
            }
            else
            {
                currentNode = currentNode.getNextNode();
            }//end if
        }//end while
        return found;
    }//end contains
    
    /** Retrieves all entries that are in this bag
        @return A newly allocated array of all the entrie in this bag */
    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while((index < numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNextNode();
        }//end while
        return result;
    }//end toArray

    /** adds the contents of an input bag to the original bag
        @param input1 The bag that you would like add to the original bag
        @return contents of both bags in the form of a bag*/
    public BagInterface<T> union(BagInterface<T> inputBag) 
    {
        BagInterface<T> tempBag = new LinkedBag<T>();
        BagInterface<T> otherBag = inputBag;
        for(int i = 0; i<numberOfEntries ; i++)
        {
            tempBag.add(this.toArray()[i]);
        }//end for
        for (int j = 0; j<otherBag.getCurrentSize();j++)
        {
            tempBag.add(otherBag.toArray()[j]);
        }//end for
        return tempBag;
    }//end union

    /** adds the contents of the items that both the original and the input bag share.
    If they share the same item multiple times, it adds the least amount 
    (i.e. bag 1 has item a twice and bag 2 has item b three times - it will add it only twice, since they don't both share it three times)
        @param input1 The bag that you would like to compare to the original bag
        @return contents that both bags share in the form of a bag*/
    public BagInterface<T> intersection(BagInterface<T> inputBag) 
    {
        BagInterface<T> tempBag = new LinkedBag<T>();
        BagInterface<T> otherBag = inputBag;
        for(int i = 0; i<numberOfEntries; i++)
        {
            for(int j = 0; j<otherBag.getCurrentSize(); j++)
            {
                if(this.toArray()[i].equals(otherBag.toArray()[j]))
                {
                    tempBag.add(this.toArray()[i]);
                }//end if
            }//end for
        }//end for
        return tempBag;
    }//end intersection

    /** adds the difference of the amount of times a certain item occurs in both bags 
    (i.e. if bag 1 has an item occur five times and bag 2 has the item occur three times, it will return that item in a bag two times)
        @param input1 The bag that you would like add to the original bag
        @return the difference of the contents that both bag 1 and 2 share in the form of a bag*/    
    public BagInterface<T> difference(BagInterface<T> inputBag) 
    {
        BagInterface<T> tempBag = new LinkedBag<T>();
        BagInterface<T> otherBag = inputBag;
        T[] array1 = this.toArray();
        T[] array2 = otherBag.toArray();

        for(int i = 0; i<numberOfEntries; i++)
        {
           boolean found = false;
            for (int j = 0; j<otherBag.getCurrentSize();j++)
            { 
                if(array1[i].equals(array2[j]))
                {
                    System.out.println("Bag 1 at spot " + i + " : " + array1[i] + " equals Bag 2 at spot " + j + " : "+ array2[j]);
                    found = true;
                }//end if
                if(!found)
                {
                    tempBag.add(this.toArray()[i]);
                }//end if
            }//end for
        }//end for
        return tempBag;
    }//end difference
}//end LinkedBag
