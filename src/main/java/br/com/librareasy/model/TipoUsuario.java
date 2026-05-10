package br.com.librareasy.model;

public enum TipoUsuario {
    ALUNO(3),
    PROFESSOR(10),
    ADMINISTRADOR(10);

    private final int limiteLivros;

    TipoUsuario(int limiteLivros){
        this.limiteLivros = limiteLivros;
    }

    public int getLimiteLivros() {
        return limiteLivros;
    }
}
