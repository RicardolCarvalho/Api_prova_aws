package br.insper.prova;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tarefa")
public class Tarefa {
    @Id
    private String id;

    private String titulo;
    private String descricao;
    private String prioridade;
    private String emailcriador;

    // getters e setters (ou use Lombok se preferir)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return titulo; }
    public void setTitle(String titulo) { this.titulo = titulo; }

    public String getDescription() { return descricao; }
    public void setDescription(String descricao) { this.descricao = descricao; }

    public String getPriority() { return prioridade; }
    public void setPriority(String prioridade) { this.prioridade = prioridade; }

    public String getCreatorEmail() { return emailcriador; }
    public void setCreatorEmail(String emailcriador) { this.emailcriador = emailcriador; }
}

