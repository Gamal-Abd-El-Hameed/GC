package CopyGC;

import CollectedObject.CollectedObject;
import DataLoader.IDataLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CopyGC implements ICopyGC{

    private final IDataLoader dataLoader;
    private final HashMap<Integer, CollectedObject> collectedObjectList;
    private final HashMap<Integer, Boolean> allocated;
    private final List<CollectedObject> newHeap;
    private int newHeapStartIndex;

    public CopyGC(IDataLoader dataLoader) {

        this.dataLoader = dataLoader;
        collectedObjectList = new HashMap<>();
        allocated = new HashMap<>();
        this.newHeapStartIndex = 0;
        this.newHeap = new ArrayList<CollectedObject>();
    }

    @Override
    public void initializeGarbageCollector() {

        initializeObjectsList();

        initializeObjectsPointers();

    }

    @Override
    public List<CollectedObject> execute() {

        this.initializeGarbageCollector();

        List<Integer> roots = this.dataLoader.extractRoots("roots.txt");

        for (int i = 0; i < roots.size(); i++)
                moveToNewHeap(roots.get(i));

        int served = 0, allocated = this.newHeap.size();

        while (served < allocated){

            for (int i = 0; i < this.newHeap.get(served).pointers.size(); i++)
                allocated += moveToNewHeap(this.newHeap.get(served).pointers.get(i));

            served++;

        }

        return this.newHeap;

    }

    @Override
    public int moveToNewHeap(int collectedObjectIdentifier) {

        CollectedObject oldObject = collectedObjectList.get(collectedObjectIdentifier);

        if (!allocated.get(collectedObjectIdentifier)){

            CollectedObject newAllocatedObject = new CollectedObject( oldObject.identifier,
                                                                          newHeapStartIndex,
                                                                  newHeapStartIndex + (oldObject.endIndex - oldObject.startIndex));
            newAllocatedObject.pointers = oldObject.pointers;
            this.newHeap.add(newAllocatedObject);
            this.newHeapStartIndex = newAllocatedObject.endIndex + 1;
            allocated.put(collectedObjectIdentifier, true);

            return 1;

        }

        return 0;
    }

    public void initializeObjectsList(){

        List<CollectedObject> objectData = this.dataLoader.extractObjectsData("heap.csv");

        for (int i = 0; i < objectData.size(); i++){

            collectedObjectList.put(objectData.get(i).identifier, objectData.get(i));
            allocated.put(objectData.get(i).identifier, false);

        }
    }

    public void initializeObjectsPointers(){

        List<List<Integer>> pointersList = this.dataLoader.extractPointers("pointers.txt");

        for (int i = 0; i < pointersList.size(); i++)

            collectedObjectList.get(pointersList.get(i).get(0)).pointers.add(pointersList.get(i).get(1));

    }




}
