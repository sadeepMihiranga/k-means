import java.util.Map;

public class DataSetObject {

    private String name;
    private Map<String, Double> valueMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Double> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, Double> valueMap) {
        this.valueMap = valueMap;
    }
}
