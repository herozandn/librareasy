package br.com.librareasy.tads;

public class No<E> {
    private E dado;
    private No<E> proximoNo;

    public No(E dado) {
        this.dado = dado;
        this.proximoNo = null;
    }

    public E getDado() {
        return dado;
    }

    public void setDado(E dado) {
        this.dado = dado;
    }

    public No<E> getProximoNo() {
        return proximoNo;
    }

    public void setProximoNo(No<E> proximoNo) {
        this.proximoNo = proximoNo;
    }
}
