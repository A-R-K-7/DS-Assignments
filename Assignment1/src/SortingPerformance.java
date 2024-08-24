import java.util.*;

public class SortingPerformance {
    static Random ran = new Random();

    public static void main(String[] args) {
        int[] array1 = new int[1000];
        int[] array2 = new int[100000];
        int[] array3 = new int[1000000];

        addRandom(array1);
        addRandom(array2);
        addRandom(array3);

        measureSortingPerformance(array1, "Array1");
        measureSortingPerformance(array2, "Array2");
        measureSortingPerformance(array3, "Array3");
    }

    public static void addRandom(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = ran.nextInt(1000000);
        }
    }

    public static void measureSortingPerformance(int[] array, String arrayName) {
        int[] arrayClone;

        arrayClone = array.clone();
        long startTime = System.currentTimeMillis();
        bubbleSort(arrayClone);
        long endTime = System.currentTimeMillis();
        System.out.println("Bubble Sort Time for " + arrayName + ": " + (endTime - startTime) + " ms");

        arrayClone = array.clone();
        startTime = System.currentTimeMillis();
        selectionSort(arrayClone);
        endTime = System.currentTimeMillis();
        System.out.println("Selection Sort Time for " + arrayName + ": " + (endTime - startTime) + " ms");

        arrayClone = array.clone();
        startTime = System.currentTimeMillis();
        insertionSort(arrayClone);
        endTime = System.currentTimeMillis();
        System.out.println("Insertion Sort Time for " + arrayName + ": " + (endTime - startTime) + " ms");

        arrayClone = array.clone();
        startTime = System.currentTimeMillis();
        quickSort(arrayClone, 0, arrayClone.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick Sort Time for " + arrayName + ": " + (endTime - startTime) + " ms");

        arrayClone = array.clone();
        startTime = System.currentTimeMillis();
        mergeSort(arrayClone, 0, arrayClone.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Merge Sort Time for " + arrayName + ": " + (endTime - startTime) + " ms");
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;
        }
    }

    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }
}
