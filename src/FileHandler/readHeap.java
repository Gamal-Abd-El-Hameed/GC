package FileHandler;

import java.io.FileReader;
import org.javatuples.Triplet;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class readHeap {

    void mergeSort(List<Triplet<Integer, Integer, Integer>>  data, int left, int right){
        if (left < right){
            int mid = (left + right) / 2;
            mergeSort(data, left, mid);            // sort the left subarray
            mergeSort(data, mid + 1, right);   // sort the right subarray
            merge(data, left, right, mid);         // merge the two sorted arrays
        }
    }

    void merge(List<Triplet<Integer, Integer, Integer>>  data, int left, int right, int mid){

        int leftStart = left;
        int midStart = mid + 1;
        List<Triplet<Integer, Integer, Integer>>  newData = new ArrayList<>();

        while(leftStart <= mid && midStart <= right){
            if (data.get(leftStart).getValue1() < data.get(midStart).getValue1())         // add the smallest of both sorted sub-arrays into the new array
                newData.add(data.get(leftStart++));
            else
                newData.add(data.get(midStart++));
        }
        while(leftStart <= mid)                     // add remaining elements in left subarray
            newData.add(data.get(leftStart++));

        while (midStart <= right)                   // add remaining elements in right subarray
            newData.add(data.get(midStart++));

        for (int i = 0; i < newData.size(); i++)
            data.set(i + left, newData.get(i));
    }

    public List<Triplet<Integer, Integer, Integer>> read(String fileName) {
        String line = "";    
        List<Triplet<Integer, Integer, Integer>> heapList = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                List<Integer> intValues = Arrays.asList(values).stream()
                        .map(Double::parseDouble)
                        .map(Double::intValue)
                        .collect(Collectors.toList());
                Triplet<Integer, Integer, Integer> tmp
                        = Triplet.with(intValues.get(0), intValues.get(1), intValues.get(2));
                heapList.add(tmp);
            }

            mergeSort(heapList, 0, heapList.size()-1);
            return heapList;

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!!\n");
            return null;
            // throw new RuntimeException(e);
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }


}