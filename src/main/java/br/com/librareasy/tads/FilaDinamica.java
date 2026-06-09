package br.com.librareasy.tads;

public class FilaDinamica<E> implements Fila<E> {

    private No<E> inicio, fim;

    public FilaDinamica() {
        this.inicio = null;
        this.fim = null;
    }

    @Override
    public boolean isEmpty() {
        return (inicio == null);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            throw new IllegalStateException("Fila vazia");
        }
        return inicio.getDado();
    }

    @Override
    public void enqueue(E obj) {
        No<E> novoNo = new No<>(obj);
        if(isEmpty()){
            inicio = novoNo;
            fim = novoNo;
        }
        else{
            fim.setProximoNo(novoNo);
            fim = novoNo;
        }
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalStateException("Fila vazia");
        }

        No<E> temp = inicio;

        inicio = inicio.getProximoNo();

        if(inicio == null){
            fim = null;
        }

        return temp.getDado();
    }

    public int size(){
        int cont = 0;
        No<E> atual = inicio;
        while(atual != null){
            cont++;
            atual = atual.getProximoNo();
        }

        return cont;
    }

    public String show(){
        String saida = "";
        No<E> atual = inicio;

        while(atual != null){
            saida += atual.getDado() + " ";
            atual = atual.getProximoNo();
        }

        return saida;
    }
}
