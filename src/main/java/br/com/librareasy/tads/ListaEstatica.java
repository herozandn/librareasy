package br.com.librareasy.tads;

public class ListaEstatica<E> implements Lista<E>{
    private E[] elementos;
    private int tamanho;

    @SuppressWarnings("não verificado")
    public ListaEstatica(int n){
        this.tamanho = 0;
        this.elementos = (E[]) new Object[n];
    }

    @Override
    public void add(int pos, E val){
        if(isFull()) throw new IllegalStateException("Erro: Acervo lotado! Não é possível adicionar mais itens.");
        if(pos<0 || pos>tamanho) throw new IndexOutOfBoundsException("Posição inválida");
        for(int i=tamanho; i>=pos+1; i--) {
            elementos[i] = elementos[i-1];
        }
        elementos[pos] = val;
        tamanho++;
    }

    @Override
    public E remove(int pos){
        if(isEmpty()) throw new IllegalStateException("Erro: Acervo vazio! Não é possível remover mais itens.");
        if(pos<0 || pos>=tamanho) throw new IndexOutOfBoundsException("Posição inválida");
        E dadoExcluido = elementos[pos];
        for(int i=pos; i<=tamanho-2; i++){
            elementos[i] = elementos[i+1];
        }
        tamanho--;
        return dadoExcluido;
    }

    @Override
    public void set(int pos, E val){
        if(isEmpty()) throw new IllegalStateException("Erro: Acervo vazio! Não é possível atribuir este item.");
        if(pos<0 || pos>= tamanho) throw new IndexOutOfBoundsException("Posição inválida");
        elementos[pos] = val;
    }

    @Override
    public E get(int pos){
        if(isEmpty()) throw new IllegalStateException("Erro: Acervo vazio! Não é possível trazer este item.");
        if(pos<0 || pos>=tamanho) throw new IndexOutOfBoundsException("Posição inválida");
        return elementos[pos];
    }

    @Override
    public int size(){
        return tamanho;
    }

    @Override
    public boolean isEmpty(){
        return elementos.length==0;
    }

    @Override
    public boolean isFull(){
        return elementos.length==tamanho;
    }

    @Override
    public String toString(){
        if(isEmpty()) return "[]";
        StringBuilder construtorDeTexto = new StringBuilder();
        construtorDeTexto.append("[");
        for (int i = 0; i < tamanho; i++) {
            construtorDeTexto.append(elementos[i].toString());
            if (i < tamanho - 1) {
                construtorDeTexto.append(", ");
            }
        }
        construtorDeTexto.append("]");
        return construtorDeTexto.toString();
    }
}