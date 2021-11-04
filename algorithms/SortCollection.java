package algorithms;

import java.util.*;
import datastructure.heap.*;

enum SortType {
    asc(1), dec(-1);

    private int sign;

    SortType(int sign) {
        this.sign = sign;
    }

    public int getSign() {
        return sign;
    }
}

public class SortCollection {
    public static <T> void arrayShift(T[] arr, int oldsVal, int newIndex) {
        T temp;

        for (int i = oldsVal - 1; i >= newIndex; i--) {
            temp = arr[i + 1];
            arr[i + 1] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * selection sort on an Array
     */
    public static <T extends Comparable<T>> void selectionSort(T[] arr, SortType sortType) {
        T proposedNewElement = arr[0];
        int proposedIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            proposedNewElement = arr[i];
            proposedIndex = i;

            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(proposedNewElement) * sortType.getSign() < 0) {
                    proposedNewElement = arr[j];
                    proposedIndex = j;
                }
            }

            if (proposedIndex != i) {
                arr[proposedIndex] = arr[i];
                arr[i] = proposedNewElement;
            }
        }
    }

    public static <T extends Comparable<T>> void selectionSort(T[] arr) {
        selectionSort(arr, SortType.asc);
    }

    /**
     * Selection sort
     */
    public static <T extends Comparable<T>> void insertionSort(T[] arr, SortType sortType) {
        T currentItem;
        for (int i = 0; i < arr.length; i++) {
            currentItem = arr[i];

            for (int j = i - 1; j > -2; j--) {
                if (j == -1) {
                    arrayShift(arr, i, 0);
                    break;
                }

                if (arr[j].compareTo(currentItem) * sortType.getSign() < 0) {
                    arrayShift(arr, i, j + 1);
                    break;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T[] arr) {
        insertionSort(arr, SortType.asc);
    }

    /**
     * Heap sort
     */
    public static <T extends Comparable<T>> void heapSort(T[] arr, SortType sortType) {
        Heap<T> heap = new Heap<>(sortType == SortType.dec ? HeapType.min : HeapType.max, Arrays.asList(arr));
        heap.sort();
    }

    public static <T extends Comparable<T>> void heapSort(T[] arr) {
        heapSort(arr, SortType.asc);
    }

    public static void main(String[] args) {
        Integer[] ints;

        // Selection sort
        ints = dummyArray(10);
        selectionSort(ints);
        System.err.println(Arrays.toString(ints));

        ints = dummyArray(10);
        insertionSort(ints);
        System.err.println(Arrays.toString(ints));

        ints = dummyArray(10);
        heapSort(ints);
        System.err.println(Arrays.toString(ints));
    }

    private static Integer[] dummyArray(int len) {
        Integer[] ints = new Integer[len];

        for (int i = 0; i < len; i++) {
            ints[i] = randInt(-1000, 1000);
        }

        return ints;
    }

    private static int randInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
