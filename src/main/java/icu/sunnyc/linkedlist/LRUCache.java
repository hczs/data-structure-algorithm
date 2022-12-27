package icu.sunnyc.linkedlist;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * leetcode-146 https://leetcode.cn/problems/lru-cache/
 *
 * @author houcheng
 * @version V1.0
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache(int capacity) {
        super((int) (capacity / 0.75 + 1), 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        // 缓存是 {1=1}
        lRUCache.put(1, 1);
        System.out.println(lRUCache);
        // 缓存是 {1=1, 2=2}
        lRUCache.put(2, 2);
        System.out.println(lRUCache);
        // 返回 1
        System.out.println(lRUCache.get(1));
        // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.put(3, 3);
        System.out.println(lRUCache);
        // 返回 -1 (未找到)
        System.out.println(lRUCache.get(2));
        // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.put(4, 4);
        System.out.println(lRUCache);
        // 返回 -1 (未找到)
        System.out.println(lRUCache.get(1));
        // 返回 3
        System.out.println(lRUCache.get(3));
        // 返回 4
        System.out.println(lRUCache.get(4));

    }

}
