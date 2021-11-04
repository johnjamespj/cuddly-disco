package datastructure.heap;

import java.util.ArrayList;

enum HeapType {
    max(1), min(-1);

    private int offset;

    HeapType(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return this.offset;
    }
}

public class Heap<T extends Comparable<T>> {
    private ArrayList<T> storage;
    private HeapType heapType;

    public Heap() {
        this(HeapType.max);
    }

    public Heap(HeapType heapType) {
        this.heapType = heapType;
        this.storage = new ArrayList<>();
    }

    public Heap(ArrayList<T> arrayList) {
        this(HeapType.max, arrayList);
    }

    public Heap(HeapType heapType, ArrayList<T> arrayList) {
        this.heapType = heapType;
        this.storage = new ArrayList<>(arrayList);
        constructHeap();
    }

    private void constructHeap() {
        for (int i = size() - 1; i >= 0; i--) {
            heapyfiyShiftDown(i, size());
        }
    }

    private void heapyfiyShiftDown(int currentIndex, int size) {
        if (currentIndex >= size || currentIndex * 2 + 1 >= size) {
            return;
        }

        int currentNodeProposedIndex = 0;
        T proposedParent, current, left, right;

        current = storage.get(currentIndex);
        proposedParent = current;
        left = storage.get(currentIndex * 2 + 1);
        right = null;

        if (left.compareTo(current) * heapType.getOffset() > 0) {
            proposedParent = left;
            currentNodeProposedIndex = currentIndex * 2 + 1;
        }

        if (currentIndex * 2 + 2 < size) {
            right = storage.get(currentIndex * 2 + 2);

            if (right.compareTo(current) * heapType.getOffset() > 0
                    && right.compareTo(left) * heapType.getOffset() > 0) {
                proposedParent = right;
                currentNodeProposedIndex = currentIndex * 2 + 2;
            }
        }

        if (proposedParent != current) {
            storage.set(currentNodeProposedIndex, current);
            storage.set(currentIndex, proposedParent);
            heapyfiyShiftDown(currentNodeProposedIndex, size);
        }
    }

    private void heapyfiyShiftUp(int currentIndex) {
        if (currentIndex <= 0) {
            return;
        }

        T parentNode, currentNode;
        int parent = (currentIndex - 1) / 2;

        parentNode = storage.get(parent);
        currentNode = storage.get(currentIndex);

        if (currentNode.compareTo(parentNode) * heapType.getOffset() > 0) {
            storage.set(currentIndex, parentNode);
            storage.set(parent, currentNode);
            heapyfiyShiftUp(parent);
        }
    }

    public boolean insert(T data) {
        if (data == null) {
            return false;
        }

        storage.add(data);
        heapyfiyShiftUp(size() - 1);
        return true;
    }

    public T removeFirst() {
        if (size() == 0) {
            return null;
        }

        if (size() == 1) {
            return storage.remove(0);
        }

        T data = storage.get(0);
        T lastItem = storage.remove(size() - 1);
        storage.set(0, lastItem);
        heapyfiyShiftDown(0, size());

        return data;
    }

    public boolean hasNext() {
        return size() > 0;
    }

    public int size() {
        return storage.size();
    }

    public ArrayList<T> sort() {
        for (int i = size() - 1; i >= 0; i--) {
            T data = storage.get(0);
            T lastItem = storage.get(i);
            storage.set(0, lastItem);
            heapyfiyShiftDown(0, i + 1);
            storage.set(i, data);
        }

        ArrayList<T> sortedArray = storage;
        this.storage = new ArrayList<>();

        return sortedArray;
    }

    @Override
    public String toString() {
        return storage.toString();
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
        ArrayList<Integer> sampleArray = new ArrayList<Integer>();

        int c = 20;
        for (int i = 0; i < c; i++) {
            heap.insert(randInt(-100, 100));
            sampleArray.add(randInt(-100, 100));
        }

        while (heap.hasNext()) {
            System.out.println(heap.removeFirst());
        }
        System.out.println();

        heap = new Heap<>(HeapType.min, sampleArray);
        System.out.println(heap.sort());
    }

    private static int randInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
