package unoeste.fipp.mercadofipp.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Pergunta;
import unoeste.fipp.mercadofipp.services.AnuncioService;
import unoeste.fipp.mercadofipp.services.PerguntaService;

@RestController
@RequestMapping("apis/pergunta")
public class PerguntaRestController {

    @Autowired
    private PerguntaService perguntaService;

    @GetMapping("/add-resposta")
    public ResponseEntity<Object> addResposta(@RequestParam("id") Long idPergunta,
                                              @RequestParam("texto") String texto){
        System.out.println("ID: " + idPergunta + " | Texto: " + texto);
        Pergunta perguntaAtualizada = perguntaService.addResposta(idPergunta, texto);
        if (perguntaAtualizada != null)
            return ResponseEntity.ok(perguntaAtualizada); // retorna objeto
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao adicionar resposta"));
    }
}
