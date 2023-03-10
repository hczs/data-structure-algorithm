package icu.sunnyc.queue;

import icu.sunnyc.linkedlist.LRUBaseSingleList;

/**
 * 基于链表实现队列
 *
 * @author hczs8
 * @ClassName: QueueBasedLink
 * @Description:
 */
public class QueueBasedLink {

    private LinkNode head;

    private LinkNode tail;

    public QueueBasedLink() {
        head = tail = new LinkNode();
    }

    /**
     * 入队
     *
     * @param item
     */
    public void enqueue(String item) {
        tail.next = new LinkNode(item);
        tail = tail.next;
        System.out.println("入队成功，打印队列状态");
        printAll();
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        String res = head.next.data;
        head = head.next;
        return res;
    }

    /**
     * 展示缓存内部数据状态
     */
    public void printAll() {
        LinkNode tmp = head;
        while (tmp.next != null) {
            System.out.print(tmp.next + " --> ");
            tmp = tmp.next;
        }
        System.out.println();
        System.out.println("==================================");
    }


    static class LinkNode {

        private String data;

        private LinkNode next;

        public LinkNode() {}

        public LinkNode(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "LinkNode{" +
                    "data='" + data + '\'' + "}";
        }
    }

    public static void main(String[] args) {
        QueueBasedLink queue = new QueueBasedLink();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        // 1
        System.out.println(queue.dequeue());
        // 2
        System.out.println(queue.dequeue());
        queue.enqueue("4");
        queue.enqueue("5");
        // 3
        System.out.println(queue.dequeue());
        // 4
        System.out.println(queue.dequeue());
        // 5
        System.out.println(queue.dequeue());
    }
}