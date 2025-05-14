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

    // Construtor vazio (usado pelo Jackson)
    public TarefaRequest() {}

    // Getters
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

    // Setters (usados pelo Jackson ao desserializar o JSON)
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

