package design;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {
    // 最不经常使用算法，如果空间不足，则淘汰使用次数最少的数据
    // 如果有多条数据都是使用最少，则淘汰最久的那条

    // https://blog.csdn.net/u013379553/article/details/113649998


    // 存储内容，便于获取缓存中存储的数据
    // key 到 val 的映射，我们后文称为 KV 表
    HashMap<String, String> keyToVal;

    // 存储每个key出现的次数，便于  当数据被访问时，需要更新这个数据的访问频次，要求迅速定位出他的访问次数
    // key 到 freq 的映射，我们后文称为 KF 表
    HashMap<String, Integer> keyToFreq;

    // 根据访问次数，迅速找到这个次数下，有哪些缓存key
    // freq 到 key 列表的映射，我们后文称为 FK 表
    HashMap<Integer, LinkedHashSet<String>> freqToKeys;

    // 记录最小的频次
    int minFreq;
    // 记录 LFU 缓存的最大容量
    int cap;

    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.cap = capacity;
        this.minFreq = 0;
    }

    // 在缓存中查询 key
    public String get(String key) {
        // 判断key是否存在，不存在返回-1
        // 存在则将key的频次加一
        // 返回key对应的value
        if (!keyToVal.containsKey(key)) {
            return null;
        }

        increaseFreq(key);

        return keyToVal.get(key);
    }

    // 将 key 和 val 存入缓存
    public void put(String key, String val) {
        //如果原map已存在该key，则更新频率和value
        if(keyToVal.containsKey(key)){
            keyToVal.put(key,val);
            increaseFreq(key); // 增加访问次数
            return;
        }

        // 如果插入新值之前，原来的容量已满，则移除最久的最少使用的key
        if(keyToVal.size()>=this.cap){
            removeMinFreqKey();
        }

        // cap大于0才能添加数据，这里要做处理
        if(this.cap > 0)
            putVal(key, val); // 添加key、value

        // 新插入了数据，这个新数据的访问频率肯定是最小的，所以重置最小频率
        this.minFreq=1;
    }

    private void increaseFreq(String key) {
        // 原来的访问次数, 需要更新为+1
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq+1);

        // 删除原来的访问次数，因为要加一，重新保存
        freqToKeys.get(freq).remove(key);
        // 重新保存新的访问次数
        LinkedHashSet<String> newFreqKeys = freqToKeys.get(freq+1);
        if (newFreqKeys == null) {
            newFreqKeys = new LinkedHashSet<>();
            newFreqKeys.add(key);
            freqToKeys.put(freq+1, newFreqKeys);
        } else {
            newFreqKeys.add(key);
        }

        if (freqToKeys.get(freq).isEmpty()) {
            // 说明就这一个key的访问次数为freq
            freqToKeys.remove(freq);
            if (freq == minFreq) {
                minFreq += 1;
            }
        }
    }

    private void removeMinFreqKey() {
        // 找出访问次数最少的key
        LinkedHashSet<String> keys = freqToKeys.get(minFreq);

        String oldestKey = keys.iterator().next();
        keys.remove(oldestKey);

        if (freqToKeys.get(minFreq).isEmpty()) {
            freqToKeys.remove(minFreq);
        }

        keyToFreq.remove(oldestKey);
        keyToVal.remove(oldestKey);
    }

    private void putVal(String key, String value) {
        keyToVal.put(key, value);
        keyToFreq.put(key, 1);
        LinkedHashSet<String> keys = freqToKeys.getOrDefault(1, new LinkedHashSet<String>());
        keys.add(key);
        freqToKeys.put(1, keys);
    }

}
