package br.com.librareasy.tads;

import static java.lang.System.out;

import java.util.function.Predicate;

public class ListaDinamica<E> implements Lista<E> {
    private No<E> inicio, fim;

    public ListaDinamica() {
        this.inicio = null;
        this.fim = null;
    }

    @Override
    public void add(int pos, E val) {
        if(pos < 0){
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        if(pos == 0){
            addAtStart(val);
            return;
        }

        No<E> novoNo = new No<>(val);
        No<E> auxiliar = inicio;
        int i = 0;

        while(auxiliar != null && i < pos -1){
            auxiliar = auxiliar.getProximoNo();
            i++;
        }

        if(auxiliar == null){
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        novoNo.setProximoNo(auxiliar.getProximoNo());
        auxiliar.setProximoNo(novoNo);

        if(novoNo.getProximoNo() == null){
            fim = novoNo;
        }
    }

    @Override
    public E remove(int pos) {
        if(isEmpty() || pos < 0){
            throw new IndexOutOfBoundsException("Posição inválida ou lista vazia");
        }

        if(pos == 0){
            return removeAtStart();
        }

        No<E> anterior = inicio;
        int i = 0;

        while(anterior != null && i < pos - 1){
            anterior = anterior.getProximoNo();
            i++;
        }

        if(anterior == null || anterior.getProximoNo() == null){
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        No<E> atual = anterior.getProximoNo();
        E removedItem = atual.getDado();

        anterior.setProximoNo(atual.getProximoNo());

        if(atual == fim){
            fim = anterior;
        }

        return removedItem;
    }

    @Override
    public void set(int pos, E val) {
        No<E> alvo = getNo(pos);
        alvo.setDado(val);
    }

    @Override
    public E get(int pos) {
        No<E> alvo = getNo(pos);
        return alvo.getDado();
    }

    @Override
    public int size() {
        int cont = 0;
        No<E> auxiliar = inicio;
        while(auxiliar != null){
            cont++;
            auxiliar = auxiliar.getProximoNo();
        }

        return cont;
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
    public void add(E val) {
        addAtEnd(val);
    }

    private No<E> getNo(int pos){
        if(pos < 0 || isEmpty()){
            throw new IndexOutOfBoundsException("Posição inválida ou lista vazia");
        }

        No<E> auxiliar = inicio;
        int i = 0;

        while(auxiliar != null && i < pos){
            auxiliar = auxiliar.getProximoNo();
            i++;
        }

        if(auxiliar == null){
            throw new IndexOutOfBoundsException("Posição inválida");
        }

        return auxiliar;
    }

    public void addAtStart(E dado){
        No<E> novoNo = new No<>(dado);

        if(isEmpty()){
            inicio = novoNo;
            fim = novoNo;
        }
        else{
            novoNo.setProximoNo(inicio);
            inicio = novoNo;
        }
    }

    public void addAtEnd(E dado){
        No<E> novoNo = new No<>(dado);

        if(isEmpty()){
            inicio = novoNo;
            fim = novoNo;
        }
        else{
            fim.setProximoNo(novoNo);
            fim = novoNo;
        }
    }

    public E removeAtStart(){
        if(isEmpty()){
            throw new IllegalStateException("Lista vazia");
        }

        E removedItem = inicio.getDado();

        if(inicio == fim){
            inicio = null;
            fim = null;
        }
        else{
            inicio = inicio.getProximoNo();
        }

        return removedItem;
    }

    public E removeAtEnd(){
        if(isEmpty()){
            throw new IllegalStateException("Lista vazia");
        }

        E removedItem = fim.getDado();

        if(inicio == fim){
            inicio = null;
            fim = null;
        }
        else{
            No<E> auxiliar = inicio;

            while(auxiliar.getProximoNo() != fim){
                auxiliar = auxiliar.getProximoNo();
            }

            fim = auxiliar;
            auxiliar.setProximoNo(null);
        }

        return removedItem;
    }

    public E encontrar(Predicate<E> filtro) {
        No<E> atual = inicio;
        while (atual != null) {
            if (filtro.test(atual.getDado())) {
                return atual.getDado();
            }
            atual = atual.getProximoNo();
        }
        return null;
    }

    public int encontrarIndice(Predicate<E> filtro) {
        No<E> atual = inicio;
        int indice = 0;
        while (atual != null) {
            if (filtro.test(atual.getDado())) {
                return indice;
            }
            atual = atual.getProximoNo();
            indice++;
        }
        return -1;
    }

    public void display(){
        if(isEmpty()){
            out.println("Lista vazia");
        }

        out.println("Dados na lista");
        No<E> auxiliar = inicio;

        while(auxiliar != null){
            out.println(auxiliar.getDado());
            auxiliar = auxiliar.getProximoNo();
        }

        out.println("\n");
    }
}
