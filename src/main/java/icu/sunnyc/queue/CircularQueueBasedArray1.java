package icu.sunnyc.queue;

import java.util.Arrays;

/**
 * 这个版本使用 head == tail 来判断队空 使用 (tail + 1) % n == head 判断队满
 * 就是有个缺点 数组会始终浪费一个空间作为 队满后 tail 的存放位置
 *
 * @author hczs8
 * @ClassName: CircularQueueBasedArray
 * @Description: 基于数组实现循环队列
 */
public class CircularQueueBasedArray1 {

    /**
     * 数组对象 存储底层数据
     */
    private final String[] items;

    /**
     * 数组大小
     */
    private final int n;

    /**
     * 头指针
     */
    private int head;

    /**
     * 尾指针
     */
    private int tail;

    public CircularQueueBasedArray1(int capacity) {
        this.items = new String[capacity];
        this.n = capacity;
        this.head = this.tail = 0;
    }

    /**
     * 入队
     *
     * @param item
     */
    public void enqueue(String item) {
        int nextTail = (tail + 1) % n;
        if (nextTail == head) {
            System.out.println("队列已满，无法入队数据");
            return;
        }
        items[tail] = item;
        tail = nextTail;
        System.out.println("入队成功！当前队列状态：" + Arrays.toString(items));
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        int nextHead = (head + 1) % n;
        if (head == tail) {
            System.out.println("队列空，无出队数据");
            return null;
        }
        String res = items[head];
        head = nextHead;
        return res;
    }

    public static void main(String[] args) {
        CircularQueueBasedArray1 queue = new CircularQueueBasedArray1(6);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue("7");
        queue.enqueue("8");
        queue.enqueue("9");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue("10");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

}