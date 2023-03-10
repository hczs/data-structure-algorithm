package icu.sunnyc.queue;

import java.util.Arrays;

/**
 * 这个版本维护一个当前数据数量的变量 curCapacity 来做判空 判满
 * 可以放满数组 满足强迫症
 *
 * @author hczs8
 * @ClassName: CircularQueueBasedArray
 * @Description: 基于数组实现循环队列
 */
public class CircularQueueBasedArray2 {

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

    /**
     * 当前数组中数据长度
     */
    private int curCapacity;

    public CircularQueueBasedArray2(int capacity) {
        this.items = new String[capacity];
        this.n = capacity;
        this.head = this.tail = 0;
        this.curCapacity = 0;
    }

    /**
     * 入队
     *
     * @param item
     */
    public void enqueue(String item) {
        if (curCapacity == n) {
            System.out.println("队列已满，无法入队数据");
            return;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
        curCapacity++;
        System.out.println("入队成功！当前队列状态：" + Arrays.toString(items));
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        if (curCapacity == 0) {
            System.out.println("队列空，无出队数据");
            return null;
        }
        String res = items[head];
        head = (head + 1) % n;
        curCapacity--;
        return res;
    }

    public static void main(String[] args) {
        CircularQueueBasedArray2 queue = new CircularQueueBasedArray2(6);
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