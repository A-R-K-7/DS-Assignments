import java.util.Arrays;

public class ArrayOperations {
    private int[] array;
    private int size;

    public ArrayOperations(int capacity) {
        array = new int[capacity];
        size = 0;
    }

    public void insert(int value) {
        if (size >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Array is full. Cannot insert new element.");
        }
        array[size++] = value;
    }

    public void remove() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("Array is empty. Cannot remove element.");
        }
        array[--size] = 0; // Optional: Reset the removed element to 0
    }

    public void insertAt(int index, int value) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index. Cannot insert element.");
        }
        if (size >= array.length) {
            throw new ArrayIndexOutOfBoundsException("Array is full. Cannot insert new element.");
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index. Cannot remove element.");
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = 0; // Optional: Reset the removed element to 0
    }

    public void print() {
        if (size == 0) {
            System.out.println("Array is empty.");
        } else {
            System.out.println("Array elements: " + Arrays.toString(Arrays.copyOf(array, size)));
        }
    }

    public static void main(String[] args) {
        ArrayOperations arrOps = new ArrayOperations(10);

        try {
            arrOps.insert(10);
            arrOps.insert(20);
            arrOps.insert(30);
            arrOps.print();

            arrOps.insertAt(1, 15);
            arrOps.print();

            arrOps.remove();
            arrOps.print();

            arrOps.removeAt(1);
            arrOps.print();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
