import java.util.List;

import org.javatuples.Pair;
import FileReader.readHeap;

public class Main {
//    public static void main(String[] args)
//    {
//        Unit<String> unit = new Unit<String>("GeeksforGeeks");
//
//        System.out.println(unit);
//    }
//    public static void main(String[] args) throws Exception {
//
//        Scanner sc = new Scanner(new File(fileName));
//        sc.useDelimiter(",");
//        while (sc.hasNext())  //returns a boolean value
//        {
//            System.out.print(sc.next());  //find and returns the next complete token from this scanner
//        }
//        sc.close();  //closes the scanner
//    }
    public static void main(String[] args)
    {
        String fileName = "F:\\heap.csv";
        List<Pair<Integer, Integer>> ans = readHeap.read(fileName);
        System.out.println(ans);
    }
}
