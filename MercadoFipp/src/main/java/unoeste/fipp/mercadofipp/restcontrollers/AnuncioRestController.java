package unoeste.fipp.mercadofipp.restcontrollers;

import com.fasterxml.jackson.core.ObjectCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.repositories.AnuncioRepository;
import unoeste.fipp.mercadofipp.services.AnuncioService;

import java.util.List;

@RestController
@RequestMapping("apis/anuncio")
public class AnuncioRestController {
    @Autowired
    private AnuncioService anuncioService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Anuncio> anuncios = anuncioService.getAll();
        if(anuncios!=null && !anuncios.isEmpty())
            return ResponseEntity.ok(anuncios);
        else
            return ResponseEntity.badRequest().body(new Erro("Anuncios não encontrados"));
    }

    @GetMapping("/buscarTitulo")
    public ResponseEntity<Object> getTitulo(@RequestParam String titulo){
        List<Anuncio> anuncio = anuncioService.getName(titulo);
        if(!anuncio.isEmpty())
            return ResponseEntity.ok(anuncio);
        return ResponseEntity.badRequest().body(new Erro("Anuncio não encontrado"));
    }

    @GetMapping("add-pergunta/{id}/{texto}")
    public ResponseEntity<Object> addPergunta(@PathVariable(name="id") Long idAnuncio, @PathVariable(name="texto") String texto){
        if(anuncioService.addPergunta(idAnuncio,texto))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao adicionar pergunta"));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Anuncio anuncio){
        Anuncio novoAnuncio=anuncioService.add(anuncio);
        if(novoAnuncio!=null)
            return ResponseEntity.ok(novoAnuncio);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao gravar o Anuncio"));
    }
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Anuncio anuncio){
        Anuncio novoAnuncio=anuncioService.add(anuncio);
        if(novoAnuncio!=null)
            return ResponseEntity.ok(novoAnuncio);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao gravar o Anuncio"));
    }
}
