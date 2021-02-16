
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
        ResizeableArrayBag<String> arrayUnion= new ResizeableArrayBag<String>(array1.getCurrentSize()+array2.getCurrentSize());
        arrayUnion = arrayUnion.union(array1, array2);
        ResizeableArrayBag<String> arrayIntersection= new ResizeableArrayBag<String>(array1.getCurrentSize()+array2.getCurrentSize());
        if(arrayUnion.contains("cat") && arrayUnion.contains("dog") && arrayUnion.contains("human") && arrayUnion.contains("poop") && arrayUnion.contains("pee") && arrayUnion.contains("fart"))
        {
            System.out.println("yes, arrayUnion has cat, dog, human, poop, pee, and fart.");
        }
        else
            System.out.println("no, arrayUnion doesn't have one of them.");
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
        arrayIntersection = arrayIntersection.intersection(array3, array4);
        if(arrayIntersection.contains("funny") && arrayIntersection.contains("haha"))
        {
            System.out.println("yes");
        }
        else
            System.out.println("no");
    }
}   
