package br.insper.prova;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    private final TarefaService service;
    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponse>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @PostMapping
    public ResponseEntity<TarefaResponse> create(
            @Valid @RequestBody TarefaRequest req
    ) {
        return ResponseEntity.ok(service.create(req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

