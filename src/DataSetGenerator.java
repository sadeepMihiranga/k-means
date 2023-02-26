import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSetGenerator {

    public List<DataSetObject> createDataSet() {

        List<DataSetObject> dataSet = new ArrayList<>();

        DataSetObject dataSetObject1 = new DataSetObject();
        dataSetObject1.setName("A1");
        Map<String, Double> valueSet1 =  new HashMap<>();
        valueSet1.put("X", 2d);
        valueSet1.put("Y", 10d);
        dataSetObject1.setValueMap(valueSet1);
        dataSet.add(dataSetObject1);

        DataSetObject dataSetObject2 = new DataSetObject();
        dataSetObject2.setName("A2");
        Map<String, Double> valueSet2 =  new HashMap<>();
        valueSet2.put("X", 2d);
        valueSet2.put("Y", 5d);
        dataSetObject2.setValueMap(valueSet2);
        dataSet.add(dataSetObject2);

        DataSetObject dataSetObject3 = new DataSetObject();
        dataSetObject3.setName("A3");
        Map<String, Double> valueSet3 =  new HashMap<>();
        valueSet3.put("X", 8d);
        valueSet3.put("Y", 4d);
        dataSetObject3.setValueMap(valueSet3);
        dataSet.add(dataSetObject3);

        DataSetObject dataSetObject4 = new DataSetObject();
        dataSetObject4.setName("A4");
        Map<String, Double> valueSet4 =  new HashMap<>();
        valueSet4.put("X", 5d);
        valueSet4.put("Y", 8d);
        dataSetObject4.setValueMap(valueSet4);
        dataSet.add(dataSetObject4);

        DataSetObject dataSetObject5 = new DataSetObject();
        dataSetObject5.setName("A5");
        Map<String, Double> valueSet5 =  new HashMap<>();
        valueSet5.put("X", 7d);
        valueSet5.put("Y", 5d);
        dataSetObject5.setValueMap(valueSet5);
        dataSet.add(dataSetObject5);

        DataSetObject dataSetObject6 = new DataSetObject();
        dataSetObject6.setName("A6");
        Map<String, Double> valueSet6 =  new HashMap<>();
        valueSet6.put("X", 6d);
        valueSet6.put("Y", 4d);
        dataSetObject6.setValueMap(valueSet6);
        dataSet.add(dataSetObject6);

        DataSetObject dataSetObject7 = new DataSetObject();
        dataSetObject7.setName("A7");
        Map<String, Double> valueSet7 =  new HashMap<>();
        valueSet7.put("X", 1d);
        valueSet7.put("Y", 2d);
        dataSetObject7.setValueMap(valueSet7);
        dataSet.add(dataSetObject7);

        DataSetObject dataSetObject8 = new DataSetObject();
        dataSetObject8.setName("A8");
        Map<String, Double> valueSet8 =  new HashMap<>();
        valueSet8.put("X", 4d);
        valueSet8.put("Y", 9d);
        dataSetObject8.setValueMap(valueSet8);
        dataSet.add(dataSetObject8);

        return dataSet;
    }
}
