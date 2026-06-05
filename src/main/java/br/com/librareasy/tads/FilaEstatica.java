package br.com.librareasy.tads;

import br.com.librareasy.model.Usuario;

public class FilaEstatica<E> implements Fila<E>{
    private E[] elementos;
    private int fim;

    @SuppressWarnings("unchecked")
    public FilaEstatica(int capacity){
        this.elementos = (E[]) new Object[capacity];
        fim = -1;
    }

    @Override
    public boolean isFull(){return this.fim==elementos.length-1;}

    @Override
    public boolean isEmpty(){return fim==-1;}

    @Override
    public E peek(){
        if(isEmpty()) throw new IllegalStateException("Fila vazia");
        return elementos[0];
    }

    @Override
    public String toString(){
        if(isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= fim; i++) {
            sb.append(elementos[i]);
            if (i < fim) sb.append(", ");
        }
        sb.append("] <- fim");
        return sb.toString();
    }

    public void enqueue(E obj){
        if(isFull()) throw new IllegalStateException("Fila cheia");
        fim++;
        elementos[fim] = obj;
    }

    public E dequeue(){
        if(isEmpty()) throw new IllegalStateException("Fila vazia");
        E val = elementos[0];
        for(int i=0; i<fim; i++) {
            elementos[i] = elementos[i+1];
        }
        elementos[fim] = null;
        fim--;
        return val;
    }

    public boolean contem(E obj){
       for(int i=0; i<=fim; i++){
           if(elementos[i].equals(obj))
               return true;
       }
       return false;
    }
}

