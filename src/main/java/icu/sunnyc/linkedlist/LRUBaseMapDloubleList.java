package icu.sunnyc.linkedlist;

import java.util.HashMap;

/**
 * 基于 HashMap + 双向链表 实现 LRU 缓存
 *
 * @author houcheng
 * @version V1.0
 */
public class LRUBaseMapDloubleList<T> {

    /**
     * 默认缓存的链表最大长度
     */
    private static final int DEFAULT_CAPACITY = 3;

    /**
     * 链表长度
     */
    private int length;

    /**
     * 头节点
     */
    private DLinkList<T> head;

    /**
     * 尾节点
     */
    private DLinkList<T> tail;

    /**
     * 哈希表 key 是缓存 key，value 是对应 node
     */
    private HashMap<String, DLinkList<T>> cache;

    public LRUBaseMapDloubleList() {
        // 初始化
        this.head = new DLinkList<>();
        this.tail = new DLinkList<>();
        this.head.next = this.tail;
        this.tail.pre = this.head;
        this.length = 0;
        this.cache = new HashMap<>((int) (DEFAULT_CAPACITY / 0.75) + 1);
    }

    /**
     * 新增缓存
     *
     * @param key 缓存 key
     * @param data 缓存值
     */
    public void add(String key, T data) {
        DLinkList<T> node = cache.get(key);
        // 不为空需要更新 后面put会更新，这里先把链表里面的节点删除
        if (node != null) {
            deleteNode(node);
        }
        // 看是否缓存已经满了
        if (length == DEFAULT_CAPACITY) {
            deleteNode(this.tail.pre);
            this.cache.remove(key);
        }
        // 放到 head 后面
        DLinkList<T> newNode = new DLinkList<>();
        newNode.key = key;
        newNode.data = data;
        putToHead(newNode);
        this.cache.put(key, newNode);
    }


    /**
     * 根据缓存key获取值
     *
     * @param key 缓存 key
     * @return 缓存值
     */
    public T get(String key) {
        DLinkList<T> node = cache.get(key);
        if (node == null) {
            return null;
        }
        deleteNode(node);
        putToHead(node);
        return node.data;
    }

    public void printAll() {
        DLinkList<T> tmp = this.head;
        while (tmp.next != null && tmp.next.key != null) {
            System.out.printf(tmp.next + " --> ");
            tmp = tmp.next;
        }
        System.out.println();
        System.out.println("==================================");
    }

    /**
     * 删除节点
     *
     * @param node
     */
    private void deleteNode(DLinkList<T> node) {
        // 把这个节点的前后节点联系到一起 达到删除这个节点的效果
        node.pre.next = node.next;
        node.next.pre = node.pre;
        this.length--;
    }

    /**
     * 在 头节点后插入节点
     *
     * @param node 指定节点
     */
    private void putToHead(DLinkList<T> node) {
        DLinkList<T> next = this.head.next;
        // 和 head 连接
        this.head.next = node;
        node.pre = this.head;
        // 和 之前的第一个节点连接
        node.next = next;
        next.pre = node;
        this.length++;
    }


    /**
     * 双向链表
     */
    public class DLinkList<T> {
        /**
         * 缓存 key
         */
        private String key;

        /**
         * 缓存 value
         */
        private T data;

        /**
         * 前一个节点
         */
        private DLinkList<T> pre;

        /**
         * 下一个节点
         */
        private DLinkList<T> next;

        @Override
        public String toString() {
            return "DLinkList{" +
                    "key='" + key + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    public static void main(String[] args) {
        LRUBaseMapDloubleList<String> lruList = new LRUBaseMapDloubleList<>();
        System.out.println("新增缓存a 100");
        lruList.add("a", "100");
        lruList.printAll();
        System.out.println("新增缓存b 200");
        lruList.add("b", "200");
        lruList.printAll();
        System.out.println("新增缓存c 300");
        lruList.add("c", "300");
        lruList.printAll();
        System.out.println("新增缓存d 400");
        lruList.add("d", "400");
        lruList.printAll();
        System.out.println("新增缓存e 500");
        lruList.add("e", "500");
        lruList.printAll();
        System.out.println("新增缓存d 600");
        lruList.add("d", "600");
        lruList.printAll();
        System.out.println("访问缓存c");
        System.out.println(lruList.get("c"));
        lruList.printAll();
    }
}
