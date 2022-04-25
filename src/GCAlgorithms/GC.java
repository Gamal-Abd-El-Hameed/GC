package GCAlgorithms;

import java.util.List;
import org.javatuples.*;

public interface GC {
    List<Triplet<Integer, Integer, Integer>> getNewHeapList
                (List<Triplet<Integer, Integer, Integer>> heapList,
                List<Unit<Integer>> rootsList,
                List<Pair<Integer, Integer>> pointersList);
}
