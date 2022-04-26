import java.util.List;

import GCAlgorithms.MarkCompact.MarkCompactGC;
import GCAlgorithms.MarkSweep.MarkSweep;
import org.javatuples.*;
import FileHandler.*;

public class Main {
    public static void main(String[] args) {
//        readHeap readHeap = new readHeap();
//        String rootsFileName = "D:\\roots.txt";
//        String heapFileName = "D:\\heap.csv";
//        String pointersFileName = "D:\\pointers.csv";
//        List<Unit<Integer>> rootsList = readRoots.read(rootsFileName); // null if there is an Error
//        List<Pair<Integer, Integer>> pointersList = readPointers.read(pointersFileName); // null if there is an Error
//        List<Triplet<Integer, Integer, Integer>> heapList = readHeap.read(heapFileName); // null if there is an Error
//
//        System.out.println(heapList);
//
//        String outputFileName = "D:\\new-heap.csv";

        String rootsFileName = "F:\\roots.txt";
        String heapFileName = "F:\\heap.csv";
        String pointersFileName = "F:\\pointers.csv";
        String outputFileName = "F:\\new-heap.csv";
        List<Unit<Integer>> rootsList = readRoots.read(rootsFileName); // null if there is an Error
        List<Pair<Integer, Integer>> pointersList = readPointers.read(pointersFileName); // null if there is an Error
        List<Triplet<Integer, Integer, Integer>> heapList = readHeap.read(heapFileName); // null if there is an Error

//        boolean writeDone = fileWriter.write(outputFileName, heapList);
//        if(!writeDone) System.out.println("Error! can't Write File !\n");

//        MarkCompactGC MarkCompactGC = new MarkCompactGC(heapList, rootsList, pointersList);
//        List<Triplet<Integer, Integer, Integer>> heapListOfMarkCompact = MarkCompactGC.execute();
//        boolean markCompactFinished = fileWriter.write(outputFileName, heapListOfMarkCompact);
//        if(!markCompactFinished) System.out.println("Error! Mark Compact can't Write File!\n");
//        else System.out.println("Done\n");

        MarkSweep MarkSweepGC = new MarkSweep(heapList, rootsList, pointersList);
        List<Triplet<Integer, Integer, Integer>> heapListOfMarkSweep = MarkSweepGC.execute();
        boolean markCompactFinished = fileWriter.write(outputFileName, heapListOfMarkSweep);
        if(!markCompactFinished) System.out.println("Error! Mark Compact can't Write File!\n");
        else System.out.println("Done\n");
    }
}
