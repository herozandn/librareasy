package br.com.librareasy.tads;

public class PilhaEstatica<E> implements Pilha<E>{
    private E[] elementos;
    private int topo;

    @SuppressWarnings("unchecked")
    public PilhaEstatica(int max){
        this.elementos = (E[]) new Object[max];
        this.topo = -1;
    }

    @Override
    public boolean isEmpty() {
        return topo<0;
    }

    @Override
    public boolean isFull() {
        return (this.topo==this.elementos.length-1);
    }

    @Override
    public void push(E n){
        if(isFull()) throw new IllegalStateException("Pilha cheia");
        topo++;
        elementos[topo] = n;
    }

    @Override
    public E pop(){
        if(isEmpty()) throw new IllegalStateException("Pilha vazia");
        E objeto = elementos[topo];
        elementos[topo] = null;
        topo--;
        return objeto;
    }

    @Override
    public int size() { return this.topo+1;}

    @Override
    public E show() {
        if(isEmpty()) throw new IllegalStateException("Pilha vazia");
        return elementos[topo];
    }
}

