package br.com.librareasy.model;

import br.com.librareasy.tads.ListaDinamica;

import java.util.Objects;

public class Usuario {
    private static int contadorId = 1;
    private final int idUsuario;
    private String nome;
    private TipoUsuario tipo;
    private ListaDinamica<Emprestimo> emprestimosAtuais;

    public Usuario(String nome, TipoUsuario tipo) {
        this.idUsuario = contadorId++;
        setNome(nome);
        setTipoUsuario(tipo);
        // Inicializa a lista dinâmica
        this.emprestimosAtuais = new ListaDinamica<>();
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipo = Objects.requireNonNull(tipoUsuario, "Tipo de usuário não pode ser nulo.");
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public ListaDinamica<Emprestimo> getEmprestimosAtuais() {
        return emprestimosAtuais;
    }

    public boolean temPendencias() {
        return !emprestimosAtuais.isEmpty();
        }
}
