package icu.sunnyc.stack;


/**
 * 基于数组实现栈数据结构
 *
 * 空间复杂度：O(1) 只需要入栈和出栈的临时变量
 * 时间复杂度：O(1) 都是对栈顶的单个数据进行操作
 *
 * @author houcheng
 * @version V1.0
 */
public class StackBasedArray {

    /**
     * 栈底层存储数据的数组
     */
    private final String[] elements;

    /**
     * 栈中实际元素的个数
     */
    private int size;

    /**
     * 栈容量设置
     */
    private final int capacity;

    public StackBasedArray(int capacity) {
        this.capacity = capacity;
        this.elements = new String[capacity];
        this.size = 0;
    }

    /**
     * 入栈
     *
     * @param element 入栈元素
     */
    public void push(String element) {
        if (size == capacity) {
            System.out.println("The stack has no space to store new element");
            return;
        }
        // 空间够的情况就放到数组最后（栈顶）
        elements[size] = element;
        size = size + 1;
    }

    /**
     * 出栈
     *
     * @return 栈顶元素
     */
    public String pop() {
        if (size == 0) {
            System.out.println("The stack is null, can't to pop");
            return null;
        }
        // 其他情况 有元素就弹出 size--
        size = size - 1;
        return elements[size];
    }

    public static void main(String[] args) {
        StackBasedArray stack = new StackBasedArray(3);
        stack.push("1");
        stack.push("2");
        stack.push("3");
        // 报错
        stack.push("4");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        // 报错
        System.out.println(stack.pop());
        stack.push("aa");
        System.out.println(stack.pop());
    }

}
