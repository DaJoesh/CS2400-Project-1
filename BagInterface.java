public interface BagInterface<T>
{
    /** Gets current number of entries in this bag
    @returns the integer number of entries currently in the bag */
    public int getCurrentSize();
    
    /** sees whether this bag is empty
    @return True id the bag is empty, false if not */
    public boolean isEmpty();

    /** adds a new entry to this bag.
    @param newEntry the object to be added as a new entry
    @return True if the addition was successful, false if not */
    public boolean add(T newEntry);

    /** removes one occurence of a given entry from this baf if possible
    @return either the removeved entry, if the removal was successful or null*/
    public T remove();

    /** removes one occurence of a given entry from this baf if possible
    @param anEntry The entry to be removed
    @return True od the removal was successful, false if not */
    public boolean remove(T anEntry);

    /** removes all entries in this bag */
    public void clear();

    /** counts the number of times a given entry appears in this bag
    @param anEntry The entry to find
    @return True if the bag contains anEntry, false is not */
    public int getFrequencyOf(T anEntry);

    /** Test whether this bag contains a given entry
    @param anEntry The entri to find
    @return True if the bad contains anEntry, false if not */
    public boolean contains(T anEntry);

    /** retrieves all entries that are in this bag 
    @returns a newly allocated array of all the entries in the bag. note: if the bag is empty the returned array is emptry */
    public T[] toArray();
    
    /** find the union of two bags
    @param inputBag1 The entries in bag one to be retrieved
    @param inputBag2 The entries in bag two to be retrieved
    @return A newly created bag with the union of the two bag entries */
    public BagInterface<T> union(BagInterface<T> inputBag1/*, BagInterface<T> inputBag2*/);

    /** find the intersection of two bags
    @param inputBag1 The entries in bag one to be searched
    @param inputBag2 The entries in bag two to be searched
    @return A newly created bag with the union of the two bag entries */
    public BagInterface<T> intersection(BagInterface<T> inputBag1/*, BagInterface<T> inputBag2*/);

    /** find the differnece of two bags
    @param inputBag1 The entries in bag one to be searched
    @param inputBag2 The entries in bag two to be searched
    @return A newly created bag with the difference of the two bag entries */
    public BagInterface<T> difference(BagInterface<T> inputBag1/*, BagInterface<T> inputBag2*/);

}
