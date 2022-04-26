package DataLoader;

import CollectedObject.CollectedObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DataLoader implements IDataLoader{

    @Override
    public List<CollectedObject> extractObjectsData() {

        List<List<Integer>> objectsData = this.readDataFromFile("heap");

        List<CollectedObject> collectedObjectList = new ArrayList<>();

        for (int i = 0; i < objectsData.size(); i++)
        {
            CollectedObject collectedObject = new CollectedObject(objectsData.get(i).get(0),
                    objectsData.get(i).get(1),
                    objectsData.get(i).get(2));

            collectedObjectList.add(collectedObject);
        }

        return collectedObjectList;
    }

    @Override
    public List<Integer> extractRoots()  {

        List<Integer> roots = new ArrayList<>();
        try {
            File file = new File("roots.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String root;
            while ((root = br.readLine()) != null)
                roots.add(Integer.parseInt(root));

            return roots;
        }
       catch (Exception e){
            e.printStackTrace();
            return null;
       }

    }

    @Override
    public List<List<Integer>> extractPointers()  {

        return this.readDataFromFile("pointers");
    }

    public List<List<Integer>> readDataFromFile(String fileName)  {

        List<List<Integer>> data = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(fileName + ".csv");

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();

                CollectedObject collectedObject = new CollectedObject();

                List<Integer> subData = new ArrayList<>();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();

                    if (cell.getCellTypeEnum() == CellType.NUMERIC) {

                        subData.add((int)cell.getNumericCellValue());

                    }
                }
                data.add(subData);
            }

            file.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return data;
    }

    public void storeData(List<CollectedObject> newHeap){

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("newHeap");

        int rowIndex = 0;

        for (int i = 0; i < newHeap.size(); i++){

            Row row = sheet.createRow(rowIndex);

            row.createCell(0).setCellValue((Integer) newHeap.get(i).identifier);

            row.createCell(1).setCellValue((Integer) newHeap.get(i).startIndex);

            row.createCell(2).setCellValue((Integer) newHeap.get(i).endIndex);

            rowIndex++;
        }

        try {
            FileOutputStream outputStream = new FileOutputStream("newHeap.csv");
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }

