package br.com.librareasy.tads;

public interface Pilha<E>{
    public boolean isEmpty();
    public boolean isFull();
    public void push(E n);
    public E pop();
    public int size();
    public E show();
}
