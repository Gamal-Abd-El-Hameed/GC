package GCAlgorithms.CopyGC;

import GCAlgorithms.CollectedObject.CollectedObject;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.io.IOException;
import java.util.List;

public interface ICopyGC {


    void initializeGarbageCollector(List<Triplet<Integer, Integer, Integer>> collectedObjects, List<Pair<Integer, Integer>> pointers);

    List<Triplet<Integer, Integer, Integer>> execute() throws IOException;

    int moveToNewHeap( int collectedObjectIdentifier );


}
