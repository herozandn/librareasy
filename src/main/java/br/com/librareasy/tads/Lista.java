package br.com.librareasy.tads;

public interface Lista<E>{
    public void add(int pos, E val);
    public E remove(int pos);
    public void set(int pos, E val);
    public E get(int pos);
    public int size();
    public boolean isEmpty();
    public boolean isFull();
}

