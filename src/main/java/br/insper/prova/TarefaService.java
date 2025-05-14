package br.insper.prova;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {
    private final TarefaRepository repo;
    public TarefaService(TarefaRepository repo) {
        this.repo = repo;
    }

    @PreAuthorize("hasAuthority('SCOPE_read:tasks')")
    public List<TarefaResponse> listAll() {
        return repo.findAll().stream()
                .map(t -> new TarefaResponse(
                        t.getId(),
                        t.getTitle(),
                        t.getDescription(),
                        t.getPriority(),
                        t.getCreatorEmail()
                ))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public TarefaResponse create(TarefaRequest req) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitle(req.getTitle());
        tarefa.setDescription(req.getDescription());
        tarefa.setPriority(req.getPriority());
        tarefa.setCreatorEmail(req.getCreatorEmail());
        Tarefa saved = repo.save(tarefa);
        return new TarefaResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getPriority(),
                saved.getCreatorEmail()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String id) {
        repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        repo.deleteById(id);
    }
}
