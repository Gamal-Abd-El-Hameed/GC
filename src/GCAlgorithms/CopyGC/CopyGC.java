package GCAlgorithms.CopyGC;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.javatuples.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CopyGC implements ICopyGC {


    private final HashMap<Integer, Triplet<Integer, Integer, Integer>> collectedObjectList;
    private final HashMap<Integer, Boolean> allocated;
    private final HashMap<Integer, List<Integer>> pointersList;
    private final List<Unit<Integer>> roots;
    private final List<Triplet<Integer, Integer, Integer>> newHeap;
    private int newHeapStartIndex;

    public CopyGC(List<Triplet<Integer, Integer, Integer>> collectedObjects,List<Unit<Integer>> roots, List<Pair<Integer, Integer>> pointers) {

        collectedObjectList = new HashMap<>();
        allocated = new HashMap<>();
        this.roots = roots;
        pointersList = new HashMap<>();
        this.newHeapStartIndex = 0;
        this.newHeap = new ArrayList<>();

        this.initializeGarbageCollector(collectedObjects, pointers);

    }

    @Override
    public void initializeGarbageCollector (List<Triplet<Integer, Integer, Integer>> collectedObjects, List<Pair<Integer, Integer>> pointers)  {

        initializeObjectsList(collectedObjects);

        initializeObjectsPointers(pointers);

    }

    @Override
    public List<Triplet<Integer, Integer, Integer>> execute()  {

        for (int i = 0; i < roots.size(); i++)
                moveToNewHeap(roots.get(i).getValue0());

        int served = 0, allocated = this.newHeap.size();

        while (served < allocated){

            if (this.pointersList.get(this.newHeap.get(served).getValue0()) != null) {

                for (int i = 0; i < this.pointersList.get(this.newHeap.get(served).getValue0()).size(); i++)
                    allocated += moveToNewHeap(this.pointersList.get(this.newHeap.get(served).getValue0()).get(i));

            }

            served++;

        }

        return this.newHeap;

    }

    @Override
    public int moveToNewHeap(int collectedObjectIdentifier) {

        Triplet<Integer, Integer, Integer> oldObject = collectedObjectList.get(collectedObjectIdentifier);

        if (!allocated.get(collectedObjectIdentifier)){

            Triplet<Integer, Integer, Integer> newAllocatedObject = new Triplet<>(oldObject.getValue0(),
                    newHeapStartIndex,
                    newHeapStartIndex + (oldObject.getValue2() - oldObject.getValue1()));

            this.newHeap.add(newAllocatedObject);
            this.newHeapStartIndex = newAllocatedObject.getValue2() + 1;
            allocated.put(collectedObjectIdentifier, true);

            return 1;

        }

        return 0;
    }

    public void initializeObjectsList(List<Triplet<Integer, Integer, Integer>> collectedObjects) {

        for (int i = 0; i < collectedObjects.size(); i++){

            collectedObjectList.put(collectedObjects.get(i).getValue0(), collectedObjects.get(i));
            allocated.put(collectedObjects.get(i).getValue0(), false);

        }
    }

    public void initializeObjectsPointers(List<Pair<Integer, Integer>> pointers) {


        for (int i = 0; i < pointers.size(); i++)
        {
            if (this.pointersList.get(pointers.get(i).getValue0()) == null){

                List<Integer> objectPointers = new ArrayList<>();
                this.pointersList.put(pointers.get(i).getValue0(), objectPointers);
            }

            this.pointersList.get(pointers.get(i).getValue0()).add(pointers.get(i).getValue1());
        }


    }
}
