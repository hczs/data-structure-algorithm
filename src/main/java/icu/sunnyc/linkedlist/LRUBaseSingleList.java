package icu.sunnyc.linkedlist;

/**
 * 基于单链表 实现 LRU缓存
 *
 * @author houcheng
 * @version V1.0
 */
public class LRUBaseSingleList<T> {

    /**
     * 默认缓存的链表最大长度
     */
    private static final int DEFAULT_CAPACITY = 3;

    /**
     * 当前链表长度
     */
    private int length = 0;

    /**
     * 链表头节点
     */
    private SingleList<T> head;

    public LRUBaseSingleList() {
        // 初始化一个空head即可
        this.head = new SingleList<>();
        this.length = 0;
    }

    /**
     * 新增缓存
     *
     * @param key 缓存key
     * @param data 缓存数据
     */
    public void add(String key, T data) {
        // 如果 key 存在，就把原先的 node 删除
        SingleList<T> pre = findPreByKey(key);
        if (pre != null) {
            pre.next = pre.next.next;
            length--;
        }
        // 如果满了 就需要删除最后一个节点
        if (length == DEFAULT_CAPACITY) {
            deleteTail();
        }
        // 最后要把这个最新的 node 放到 head 后面
        SingleList<T> newNode = new SingleList<>();
        newNode.key = key;
        newNode.data = data;
        addHead(newNode);
    }

    /**
     * 获取缓存值
     *
     * @param key 缓存 key
     * @return 缓存值
     */
    public T get(String key) {
        SingleList<T> pre = findPreByKey(key);
        if (pre == null) {
            return null;
        }
        // 删除这个缓存，并且把他搞到最前面
        SingleList<T> target = pre.next;
        pre.next = target.next;
        length--;
        addHead(target);
        return target.data;
    }

    /**
     * 展示缓存内部数据状态
     */
    public void printAll() {
        SingleList<T> tmp = head;
        while (tmp.next != null) {
            System.out.printf(tmp.next + " --> ");
            tmp = tmp.next;
        }
        System.out.println();
        System.out.println("==================================");
    }

    /**
     * 删除最后一个节点
     */
    private void deleteTail() {
        SingleList<T> tmp = head;
        SingleList<T> pre = tmp;
        while (tmp.next != null) {
            pre = tmp;
            tmp = tmp.next;
        }
        // pre == tmp 证明只有一个 head，所以没必要修改 length 和 next 这些东西了
        if (pre == tmp) {
            return;
        }
        pre.next = null;
        length--;
    }

    /**
     * 根据 key 找到前一个节点
     *
     * @param key 缓存 key
     * @return 前一个节点，如果缓存不存在则返回 null
     */
    private SingleList<T> findPreByKey(String key) {
        SingleList<T> tmp = head;
        while (tmp.next != null) {
            SingleList<T> cur = tmp.next;
            if (key != null && key.equals(cur.key)) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }

    /**
     * 将指定节点插入到头节点后面
     *
     * @param node 新增节点
     */
    private void addHead(SingleList<T> node) {
        SingleList<T> next = head.next;
        head.next = node;
        node.next = next;
        length++;
    }

    private class SingleList<T> {

        /**
         * 缓存 key（可选）
         */
        private String key;

        /**
         * 节点 data （必填）
         */
        private T data;

        /**
         * 下一个 node
         */
        private SingleList<T> next;

        @Override
        public String toString() {
            return "SingleList{" +
                    "key='" + key + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    public static void main(String[] args) {
        LRUBaseSingleList<String> lruList = new LRUBaseSingleList<>();
        lruList.add("a", "100");
        lruList.printAll();
        lruList.add("b", "200");
        lruList.printAll();
        lruList.add("c", "300");
        lruList.printAll();
        lruList.add("d", "400");
        lruList.printAll();
        lruList.add("e", "500");
        lruList.printAll();
        lruList.add("e", "600");
        lruList.printAll();
        System.out.println(lruList.get("c"));
        lruList.printAll();
    }

}
