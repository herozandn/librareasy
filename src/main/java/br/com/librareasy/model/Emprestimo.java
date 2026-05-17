package br.com.librareasy.model;

public class Emprestimo {
    private static int cont=1;
    private final int idEmprestimo;
    private Exemplar exemplarEmprestado;
    private Usuario usuario;
    private Data dataInicio;
    private Data prazoDevolucao;
    private StatusEmprestimo statusEmprestimo;
    private Data dataDevolucao;

    public Emprestimo(Exemplar exemplarEmprestado, Usuario usuario, Data hoje) {
        this.idEmprestimo = cont++;
        setExemplarEmprestado(exemplarEmprestado);
        setUsuario(usuario);
        setDataInicio(hoje);
        this.prazoDevolucao = hoje.adicionarDias();
        this.statusEmprestimo = StatusEmprestimo.Ativo;
    }

    /**
     * Registra a devolução
     * @param dataDevolucao data REAL de devolução do exemplar
     */
    public void registrarDevolucao(Data dataDevolucao){
        this.dataDevolucao = dataDevolucao;
        this.statusEmprestimo = StatusEmprestimo.Finalizado;
    }

    /**
     * Calcula a multa de atraso
     * Se houver atraso a multa é de R$4,00 por dia
     */
    public double multa(){
        if(dataDevolucao==null) return 0.0;
        long dias = prazoDevolucao.calcularDiferencaEmDias(dataDevolucao);
        return (dias>0) ? dias*4.0 : 0.0;
    }

    public Exemplar getExemplarEmprestado() {
        return exemplarEmprestado;
    }

    public void setExemplarEmprestado(Exemplar exemplarEmprestado) {
        if(exemplarEmprestado==null) throw new IllegalArgumentException("Exemplar não pode ser nulo");
        if(exemplarEmprestado.getStatusLivro()!=StatusLivro.Disponivel){
            throw new IllegalStateException("Exemplar não disponível");
        }
        this.exemplarEmprestado = exemplarEmprestado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if(usuario==null) throw new IllegalArgumentException("Usuário não pode ser nulo");
        this.usuario = usuario;
    }

    public Data getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Data dataInicio) {
        if(dataInicio==null) throw new IllegalArgumentException("Data não pode ser nula");
        if(this.prazoDevolucao!=null && !dataInicio.isAntes(this.prazoDevolucao)){
            throw new IllegalStateException("Data de início não pode ser após o prazo de devolução.");
        }
        this.dataInicio = dataInicio;
    }

    public Data getPrazoDevolucao() {
        return prazoDevolucao;
    }

    public StatusEmprestimo getStatusEmprestimo() {
        return statusEmprestimo;
    }
}
