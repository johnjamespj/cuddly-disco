package datastructure.heap;

public enum HeapType {
    max(1), min(-1);

    private int offset;

    HeapType(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return this.offset;
    }
}