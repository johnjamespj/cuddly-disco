package datastructure.lists;

import java.util.Collection;

public abstract class List<T> implements Iterable<T> {
    abstract public T get(int index);

    abstract public boolean add(T data);

    abstract public boolean add(int index, T data);

    abstract public boolean set(int index, T data);

    abstract public T remove(int index);

    abstract public boolean addAll(Collection<? extends T> items);

    abstract public boolean addAll(int index, Collection<? extends T> items);

    abstract public boolean contains(T item);

    abstract public int indexOf(T item);

    abstract public T[] toArray();
}
