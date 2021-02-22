//By Josh and Russell
public class LinkedBagTest
{   public static void main(String[] args)
    {
        LinkedBag<String> bag1 = new LinkedBag<String>();
        LinkedBag<String> bag2 = new LinkedBag<String>();
        bag1.add("Plant");
        bag1.add("Animal");
        bag1.add("Bacteria");
        bag1.add("Bacteria");
        bag2.add("notPlant");
        bag2.add("notAnimal");
        bag2.add("Animal");
        bag2.add("notBacteria");
        bag2.add("Bacteria");
        //In bag1: Plant, *Animal, *Bacteria, *Bacteria
        //In bag2: notPlant, notAnimal, *Animal, notBacteria, *Bacteria
        BagInterface<String> unionBag = new LinkedBag<String>();
        BagInterface<String> intersectionBag = new LinkedBag<String>();
        BagInterface<String> differenceBag = new LinkedBag<String>();
        unionBag = bag1.union(bag2);
        intersectionBag = bag1.intersection(bag2);
        differenceBag = bag1.difference(bag2);
        printOut("Linked Union", unionBag);
        printOut("Linked Intersection", intersectionBag);
        printOut("Linked Difference", differenceBag);
    }
    public static void printOut(String title, BagInterface<String> input)
    {
        System.out.println(title + ": ");
        while(!input.isEmpty())
        {
            System.out.println(input.remove());
        }
        System.out.println();
    }
}
