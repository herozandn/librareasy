package br.com.librareasy.tads;

public class PilhaDinamica<E> implements Pilha<E> {

    private No<E> topo;

    public PilhaDinamica() {
        this.topo = null;
    }

    @Override
    public boolean isEmpty() {
        return (topo == null);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void push(E n) {
        No<E> novoNo = new No<>(n);
        novoNo.setProximoNo(topo);
        topo = novoNo;
    }

    @Override
    public E pop() {
        if(isEmpty()){
            throw new IllegalStateException("Pilha Vazia");
        }

        No<E> temp = topo;
        topo = topo.getProximoNo();

        return temp.getDado();
    }

    @Override
    public int size() {
        No<E> auxiliar = topo;
        int cont = 0;
        while(auxiliar != null){
            cont++;
            auxiliar = auxiliar.getProximoNo();
        }

        return cont;
    }

    @Override
    public E show() {
        if(isEmpty()){
            throw new IllegalStateException("Pilha vazia");
        }

        return topo.getDado();
    }

    public String display(){
        if(isEmpty()){
            return "Pilha vazia";
        }

        String mensagem = "";

        No<E> auxiliar = topo;

        while(auxiliar != null){
            mensagem = mensagem + auxiliar.getDado() + " ";
            auxiliar = auxiliar.getProximoNo();
        }

        return mensagem;
    }
}
