package microsoft;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyJson {

    private Map<String, Object> dataMap;

    public MyJson() {
        dataMap = new HashMap<>(16);
    }

    public MyJson(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public void put(String name, Object data) {
        dataMap.put(name, data);
    }

    public void get(String name) {
        dataMap.get(name);
    }

    public Set<Map.Entry<String, Object>> getEntrySet() {
        return dataMap.entrySet();
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public MyJson merge(MyJson json1, MyJson json2) {
        Map<String, Object> data1 = json1.getDataMap();
        Map<String, Object> data2 = json2.getDataMap();
        merge(data1, data2);
        return new MyJson(data1);
    }

    private Map<String, Object> merge(Map<String, Object> data1, Map<String, Object> data2) {
        for (String key : data2.keySet()) {
            if (data1.containsKey(key) && (data2.get(key) instanceof Map || data1.get(key) instanceof Map)) {
                // 执行合并
                data1.put(key, merge((Map)data1.get(key), (Map)data2.get(key)));
            } else if (data1.containsKey(key)) {
                continue;
            } else {
                data1.put(key, data2.get(key));
            }
        }
        return data1;
    }

    public static void main(String[] args) {
        MyJson myJson1 = new MyJson();
        MyJson myJson11 = new MyJson();
        myJson11.put("B1", 10);
        myJson1.put("A", 10);
        myJson1.put("B", 10);


        MyJson myJson2 = new MyJson();
        MyJson myJson22 = new MyJson();
        myJson11.put("B2", 1);
        myJson1.put("A", 20);
        myJson1.put("B", myJson2);

        myJson1.merge(myJson1, myJson2);
    }
}
