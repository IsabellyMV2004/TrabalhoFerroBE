package unoeste.fipp.mercadofipp.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.services.AnuncioService;
import unoeste.fipp.mercadofipp.services.PerguntaService;

@RestController
@RequestMapping("apis/pergunta")
public class PerguntaRestController {

    @Autowired
    private PerguntaService perguntaService;

    @GetMapping("add-resposta/{id}/{texto}")
    public ResponseEntity<Object> addResposta(@PathVariable(name="id") Long idPergunta, @PathVariable(name="texto") String texto){
        if(perguntaService.addResposta(idPergunta,texto))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao adicionar resposta"));
    }
}
