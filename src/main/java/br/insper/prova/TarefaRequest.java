package br.insper.prova;

import jakarta.validation.constraints.NotBlank;

public class TarefaRequest {
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank
    private String prioridade;
    @NotBlank
    private String emailcriador;

    public TarefaRequest() {}

    public String getTitulo() {
        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getPrioridade() {
        return prioridade;
    }
    public String getEmailCriador() {
        return emailcriador;
    }

    // Setters (usados pelo Jackson ao desserializar o JSON)
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
    public void setEmailCriador(String emailcriador) {
        this.emailcriador = emailcriador;
    }
}

