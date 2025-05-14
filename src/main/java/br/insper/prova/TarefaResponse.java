package br.insper.prova;

public class TarefaResponse {
    private String id;
    private String title;
    private String description;
    private String priority;
    private String creatorEmail;

    public TarefaResponse() {}
    public TarefaResponse(String id,
                          String title,
                          String description,
                          String priority,
                          String creatorEmail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.creatorEmail = creatorEmail;
    }
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getPriority() {
        return priority;
    }
    public String getCreatorEmail() {
        return creatorEmail;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }
}


