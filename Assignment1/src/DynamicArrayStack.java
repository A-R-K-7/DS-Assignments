import java.util.Arrays;

public class DynamicArrayStack {
    private int[] stack;
    private int size;
    private int capacity;

    public DynamicArrayStack(int initialCapacity) {
        stack = new int[initialCapacity];
        size = 0;
        capacity = initialCapacity;
    }

    public void push(int value) {
        if (size == capacity) {
            resize();
        }
        stack[size++] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot pop element.");
        }
        int value = stack[--size];
        stack[size] = 0; // Optional: Reset the popped element to 0
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot peek element.");
        }
        return stack[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        capacity *= 2;
        stack = Arrays.copyOf(stack, capacity);
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            System.out.println("Stack elements: " + Arrays.toString(Arrays.copyOf(stack, size)));
        }
    }

    public static void main(String[] args) {
        DynamicArrayStack stack = new DynamicArrayStack(5);

        try {
            stack.push(10);
            stack.push(20);
            stack.push(30);
            stack.print();

            System.out.println("Peek: " + stack.peek());

            stack.push(40);
            stack.push(50);
            stack.push(60); // This will trigger a resize
            stack.print();

            System.out.println("Pop: " + stack.pop());
            stack.print();

            System.out.println("Stack size: " + stack.size());

            while (!stack.isEmpty()) {
                System.out.println("Pop: " + stack.pop());
            }
            stack.print();

            // This will throw an exception
            stack.pop();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
