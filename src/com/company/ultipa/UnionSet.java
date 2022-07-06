package ultipa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UnionSet {

    public List<Integer> intersect(List<Integer> nums1, List<Integer> nums2) {
        HashMap<Integer, Integer> dataMap = new HashMap();
        for (int i = 0; i < nums1.size(); i++) {
            dataMap.put(nums1.get(i), dataMap.getOrDefault(nums1.get(i), 0) + 1);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums2.size(); i++) {
            if (dataMap.containsKey(nums2.get(i))) {
                result.add(nums2.get(i));
                Integer existCount = dataMap.get(nums2.get(i));
                if (existCount == 1) {
                    dataMap.remove(nums2.get(i));
                } else {
                    dataMap.put(nums2.get(i), existCount - 1);
                }
            }
        }
        return result;
    }

    // 思考题：如果两个数据集很大，那么不能一下子读到内存，否则内存会存不下
    // 分别将文件分割成一个个小文件，比方每个小文件200M（确保内存能加载进来一个小文件）
    // 分割的时候，要让相同范围内的数据，都映射到一个文件上。
    // 比方A文件中1---10W的数据都放在 A1文件，B文件中1--10W的数据都放在B1文件上。
    // 这样再同时加载进来后缀一样的A、B文件，然后在内存中进行判断是否有交集，
    // 此时便不会出现因为空间过大存不下的情况


    public static void main(String[] args) {
        Integer[] array1 = {1,2,2,1};
        Integer [] array2 = {2,2};

        List<Integer> nums1 = Stream.of(array1).collect(Collectors.toList());
        List<Integer> nums2 = Stream.of(array2).collect(Collectors.toList());

        UnionSet uSet = new UnionSet() ;
        List<Integer> list = uSet.intersect(nums1, nums2);
        System.out.println(list);
    }
}
