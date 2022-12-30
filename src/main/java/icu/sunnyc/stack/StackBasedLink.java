package icu.sunnyc.stack;

/**
 * 基于单链表实现栈数据结构
 *
 * @author houcheng
 * @version V1.0
 */
public class StackBasedLink {

    /**
     * 存储数据链表头节点
     */
    private final LinkedNode head = new LinkedNode();

    /**
     * 单纯记录栈元素个数
     */
    private int size;

    public void push(String element) {
        LinkedNode newNode = new LinkedNode();
        newNode.data = element;
        newNode.next = head.next;
        head.next = newNode;
        size++;
    }

    public String pop() {
        if (head.next == null) {
            System.out.println("The stack is have no element");
            return null;
        }
        LinkedNode topNode = head.next;
        head.next = topNode.next;
        size--;
        return topNode.data;
    }

    public int getSize() {
        return size;
    }

    private class LinkedNode {

        /**
         * 存储数据
         */
        private String data;

        /**
         * 下一节点
         */
        private LinkedNode next;
    }

    public static void main(String[] args) {
        StackBasedLink stack = new StackBasedLink();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        // 不会报错
        stack.push("4");
        stack.push("5");
        System.out.println("stack 放入五个元素后的大小：" + stack.getSize());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        // 没有元素了 报错
        System.out.println(stack.pop());
        stack.push("aa");
        System.out.println(stack.pop());
        System.out.println("stack 最终大小：" + stack.getSize());
    }

}
