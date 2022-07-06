import java.util.*;

public class TestMain {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> dataMap = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String src = equations.get(i).get(0);
            String des = equations.get(i).get(1);
            double val = values[i];

            if (dataMap.containsKey(src)) {
                dataMap.get(src).put(des, val);
            } else {
                Map<String, Double> tempMap = new HashMap<>();
                tempMap.put(des, val);
                dataMap.put(src, tempMap);
            }

            if (dataMap.containsKey(des)) {
                dataMap.get(des).put(src, 1.0 / val);
            } else {
                Map<String, Double> tempMap = new HashMap<>();
                tempMap.put(src, 1.0 / val);
                dataMap.put(des, tempMap);
            }
        }

        System.out.println("==-=-=-=-=-=");
        System.out.println(dataMap);

        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String des = queries.get(i).get(1);
            if (!dataMap.containsKey(src) || !dataMap.containsKey(des)) {
                result[i] = -1.0;
            } else {
                Set<String> exist = new HashSet<>();
                exist.add(src);
                result[i] = dfs(dataMap, src, des, exist);
            }
        }
        return result;
    }

    private double dfs(Map<String, Map<String, Double>> dataMap, String src, String des, Set<String> exist) {
        System.out.println("src = " + src + ", des = " + des);
        if (src.equals(des)) {
            return 1.0;
        }

        Map<String, Double> tempMap = dataMap.get(src);
        if (tempMap == null) {
            return -1;
        }

        double res = -1;

        for (Map.Entry<String, Double> entry : tempMap.entrySet()) {
            String des1 = entry.getKey();
            if (exist.contains(des1)) {
                continue;
            }
            if (des1.equals(des)) {
                return entry.getValue();
            }
            exist.add(des1);
            double tempRes = dfs(dataMap, des1, des, exist);
            exist.remove(des1); // 回溯

            res = (tempRes == -1) ? -1 : entry.getValue() * tempRes;
            if (res != -1) {
                return res;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<List<String>> equations = Arrays.asList(Arrays.asList("x1","x2"), Arrays.asList("x2","x3"), Arrays.asList("x1","x4"), Arrays.asList("x2","x5"));
        double[] values = new double[] {3.0,0.5,3.4,5.6};
        List<List<String>> queries = Arrays.asList(Arrays.asList("x2","x4"));
        TestMain mm = new TestMain();
        mm.calcEquation(equations, values, queries);
    }
}
