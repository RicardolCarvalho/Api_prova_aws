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

    public String getTitle() {
        return titulo;
    }
    public String getDescription() {
        return descricao;
    }
    public String getPriority() {
        return prioridade;
    }
    public String getCreatorEmail() {
        return emailcriador;
    }

    public void setTitle(String titulo) {
        this.titulo = titulo;
    }
    public void setDescription(String descricao) {
        this.descricao = descricao;
    }
    public void setPriority(String prioridade) {
        this.prioridade = prioridade;
    }
    public void setCreatorEmail(String emailcriador) {
        this.emailcriador = emailcriador;
    }
}

