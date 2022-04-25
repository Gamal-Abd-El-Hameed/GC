
// Below is a Java program to create
// a Unit tuple from Constructor

import org.javatuples.Unit;

public class Main {
    public static void main(String[] args)
    {
        Unit<String> unit = new Unit<String>("GeeksforGeeks");

        System.out.println(unit);
    }
}