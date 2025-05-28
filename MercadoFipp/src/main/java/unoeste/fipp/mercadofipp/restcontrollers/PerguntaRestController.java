package unoeste.fipp.mercadofipp.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Pergunta;
import unoeste.fipp.mercadofipp.services.PerguntaService;

import java.util.List;

@RestController
@RequestMapping("apis/pergunta")
public class PerguntaRestController {

    @Autowired
    private PerguntaService perguntaService;

    @GetMapping
    public ResponseEntity<Object> getPerguntas() {
        List<Pergunta> perguntas = perguntaService.getAll();
        if (perguntas == null || perguntas.isEmpty())
            return ResponseEntity.badRequest().body(new Erro("Nenhuma pergunta encontrada"));
        return ResponseEntity.ok(perguntas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPerguntaById(@PathVariable Long id) {
        Pergunta pergunta = perguntaService.getById(id);
        if (pergunta == null)
            return ResponseEntity.badRequest().body(new Erro("Pergunta não encontrada"));
        return ResponseEntity.ok(pergunta);
    }

    @GetMapping("/anuncio/{anuncioId}")
    public ResponseEntity<Object> getPerguntasByAnuncio(@PathVariable Long anuncioId) {
        List<Pergunta> perguntas = perguntaService.getByAnuncioId(anuncioId);
        if (perguntas == null || perguntas.isEmpty())
            return ResponseEntity.badRequest().body(new Erro("Nenhuma pergunta encontrada para o anúncio"));
        return ResponseEntity.ok(perguntas);
    }

    @PostMapping
    public ResponseEntity<Object> addPergunta(@RequestBody Pergunta pergunta) {
        Pergunta nova = perguntaService.save(pergunta);
        if (nova == null)
            return ResponseEntity.badRequest().body(new Erro("Erro ao adicionar pergunta"));
        return ResponseEntity.ok(nova);
    }

    @PutMapping
    public ResponseEntity<Object> updatePergunta(@RequestBody Pergunta pergunta) {
        Pergunta atualizada = perguntaService.save(pergunta);
        if (atualizada == null)
            return ResponseEntity.badRequest().body(new Erro("Erro ao atualizar pergunta"));
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePergunta(@PathVariable Long id) {
        if (perguntaService.delete(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao deletar pergunta"));
    }
}
