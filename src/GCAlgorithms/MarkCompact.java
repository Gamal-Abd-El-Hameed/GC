package GCAlgorithms;

import java.util.*;
import org.javatuples.*;

public class MarkCompact implements GC {
    private List<Triplet<Integer, Integer, Integer>> heapList;
    private List<Unit<Integer>> rootsList;
    private List<Pair<Integer, Integer>> pointersList;
    private List<Triplet<Integer, Integer, Integer>> newHeapList;
    private HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
    private HashMap<Integer, ArrayList<Integer>> adjacencyList = new HashMap<Integer, ArrayList<Integer>>();
    private int size;


    @Override
    public List<Triplet<Integer, Integer, Integer>> getNewHeapList(List<Triplet<Integer, Integer, Integer>> givenHeapList,
            List<Unit<Integer>> givenRootsList, List<Pair<Integer, Integer>> givenPointersList) {
        heapList = givenHeapList;
        rootsList = givenRootsList;
        pointersList = givenPointersList;   
        setAdjacencyList();     
        markPhase();
        compactPhase();
        return newHeapList;
    }    

    private void setAdjacencyList() {
        size = heapList.size();
        for (int i = 0; i < size; i++)
            adjacencyList.put(heapList.get(i).getValue0(), new ArrayList<Integer>());
        
        int pointersSize = pointersList.size();
        for(int i = 0; i < pointersSize; i++)
            adjacencyList.get(pointersList.get(i).getValue0()).add(pointersList.get(i).getValue1());
    }    

    private void markPhase() {      
        int rootsSize = rootsList.size();
        for(int i = 0; i < rootsSize; i++)
            DFS(rootsList.get(i).getValue0());
    }

    private void DFS(int current) {
        visited.put(current, true);
		// System.out.print(current + " "); ////////////
		for(Integer adj: adjacencyList.get(current)) {
            if (visited.get(adj)) continue;
			DFS(adj);	
        }			
    }

    private void compactPhase() {
        int offset = 0;
        // sort
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