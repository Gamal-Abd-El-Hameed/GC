package GCAlgorithms.MarkCompact;

import java.util.*;

import org.javatuples.*;

public class MarkCompactGC implements IMarkCompactGC {
    private final List<Triplet<Integer, Integer, Integer>> heapList;
    private final List<Unit<Integer>> rootsList;
    private final List<Pair<Integer, Integer>> pointersList;
    private final List<Triplet<Integer, Integer, Integer>> newHeapList;
    private final HashMap<Integer, Boolean> visited = new HashMap<>();
    private final HashMap<Integer, ArrayList<Integer>> adjacencyList = new HashMap<>();
    private int size;

    public MarkCompactGC(List<Triplet<Integer, Integer, Integer>> givenHeapList,
                         List<Unit<Integer>> givenRootsList,
                         List<Pair<Integer, Integer>> givenPointersList) {
        heapList = givenHeapList;
        rootsList = givenRootsList;
        pointersList = givenPointersList;
        this.newHeapList = new ArrayList<>();
    }

    @Override
    public List<Triplet<Integer, Integer, Integer>> execute() {
        initializePhase();
        markPhase();
        compactPhase();
        return newHeapList;
    }

    private void initializePhase() {
        size = heapList.size();
        for(Triplet<Integer, Integer, Integer> heapElement: heapList) {
            adjacencyList.put(heapElement.getValue0(), new ArrayList<>());
            visited.put(heapElement.getValue0(), false);
        }

        for (Pair<Integer, Integer> pointer : pointersList)
            adjacencyList.get(pointer.getValue0()).add(pointer.getValue1());
    }

    @Override
    public void markPhase() {
        for (Unit<Integer> root : rootsList)
            DFS(root.getValue0());
    }

    private void DFS(int current) {
        visited.put(current, true);
        for(Integer adj: adjacencyList.get(current)) {
            if (!visited.get(adj))
                DFS(adj);
        }
    }

    @Override
    public void compactPhase() {
        int offset = 0;
        for(int i = 0; i < size; i++) {
            Triplet<Integer, Integer, Integer> heapElement = heapList.get(i);
            int identifier = heapElement.getValue0();
            int startIndex = heapElement.getValue1();
            int endIndex = heapElement.getValue2();
            if(visited.get(identifier)) {
                visited.put(identifier, false);
                Triplet<Integer, Integer, Integer> newHeapElement
                        = Triplet.with(identifier, offset, offset + endIndex - startIndex);
                newHeapList.add(newHeapElement);
                offset += endIndex - startIndex + 1;
            }
        }
    }
}