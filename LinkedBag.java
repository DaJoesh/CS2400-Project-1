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

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }

        private T getData()
        {
            return data;
        }

        private void setData(T newData)
        {
            data = newData;
        }

        private Node getNextNode()
        {
            return next;
        }
    }

    public boolean add(T newEntry)
    {
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;
        
        firstNode = newNode;
        numberOfEntries++;

        return true;
    }

    public T remove()
    {
        T result = null;
        if(firstNode != null)
        {
            result = (T) firstNode.getData();
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
        }
        return result;
    }

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
            }
        }
        return currentNode;
    }

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
        }
        return result;
    }

    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }

    public int getCurrentSize()
    {
        return numberOfEntries;
    }

    public void clear()
    {
        while(!isEmpty())
        {
            remove();
        }
    }

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
            }
            counter++;
            currentNode = currentNode.getNextNode();
        }
        return frequency;
    }

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
            }
        }
        return found;
    }

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
        }
        return result;
    }

    
    public BagInterface<T> union(BagInterface<T> inputBag) 
    {
        BagInterface<T> tempBag = new LinkedBag<T>();
        BagInterface<T> otherBag = inputBag;
        for(int i = 0; i<numberOfEntries ; i++)
        {
            tempBag.add(this.toArray()[i]);
        }
        for (int j = 0; j<otherBag.getCurrentSize();j++)
        {
            tempBag.add(otherBag.toArray()[j]);
        }
        return tempBag;
    }


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
                }
            }
        }
        return tempBag;
    }

    
    public BagInterface<T> difference(BagInterface<T> inputBag) 
    {
        BagInterface<T> tempBag = new LinkedBag<T>();
        BagInterface<T> otherBag = inputBag;
        int counter = 0;
        for(int i = 0; i<numberOfEntries; i++)
        {
            counter = 0;
            for (int j = 0; j<otherBag.getCurrentSize();j++)
            { 
                if(this.toArray()[i].equals(otherBag.toArray()[j]))
                {
                    counter++;
                }
                if(counter == 0)
                {
                    tempBag.add(this.toArray()[i]);
                }
            }
        }
        return tempBag;
    } 
}
