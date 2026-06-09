package br.com.librareasy.tads;

public interface Fila<E>{
    public boolean isEmpty();
    public boolean isFull();
    public E peek();
    public void enqueue(E obj);
    public E dequeue();
    public boolean contem(E obj);
}
