
public class ArrayBagTest
{
    public static void main(String[] args)
    {   //UNION START
        ResizeableArrayBag<String> array1= new ResizeableArrayBag<String>();
        ResizeableArrayBag<String> array2= new ResizeableArrayBag<String>();
        array1.add("cat");
        array1.add("dog");
        array1.add("human");
        array2.add("poop");
        array2.add("pee");
        array2.add("fart");
        BagInterface<String> arrayUnion= new ResizeableArrayBag<String>(array1.getCurrentSize()+array2.getCurrentSize());
        arrayUnion = array1.union(array2);
        printOut("union", arrayUnion);
        //UNION END

        //INTERSECTION START
        ResizeableArrayBag<String> array3= new ResizeableArrayBag<String>();
        ResizeableArrayBag<String> array4= new ResizeableArrayBag<String>();
        array3.add("funny");
        array3.add("haha");
        array3.add("funny");
        array4.add("no");
        array4.add("funny");
        array4.add("haha");
        BagInterface<String> arrayIntersection= new ResizeableArrayBag<String>(array3.getCurrentSize()+array4.getCurrentSize());
        arrayIntersection = array3.intersection(array4);
        printOut("intersection", arrayIntersection);
        //INTERSECTION END

        //DIFFERENCE START
        ResizeableArrayBag<String> array5= new ResizeableArrayBag<String>();
        ResizeableArrayBag<String> array6= new ResizeableArrayBag<String>();
        array5.add("funny");
        array5.add("haha");
        array5.add("funny");
        array6.add("no");
        array6.add("funny");
        array6.add("haha");
        BagInterface<String> arrayDifference = new ResizeableArrayBag<String>(array5.getCurrentSize()+array6.getCurrentSize());
        arrayDifference = array5.difference(array6);
        printOut("difference", arrayDifference);
        //DIFFERENCE END        
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
