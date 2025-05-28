package unoeste.fipp.mercadofipp.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Foto;
import unoeste.fipp.mercadofipp.services.FotoService;

import java.util.List;

@RestController
@RequestMapping("apis/foto")
public class FotoRestController {

    @Autowired
    private FotoService fotoService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public ResponseEntity<Object> getFotos() {
        List<Foto> fotos = fotoService.getAll();
        if (fotos != null && !fotos.isEmpty())
            return ResponseEntity.ok(fotos);
        return ResponseEntity.badRequest().body(new Erro("Nenhuma foto encontrada"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFotoById(@PathVariable Long id) {
        Foto foto = fotoService.getById(id);
        if (foto == null)
            return ResponseEntity.badRequest().body(new Erro("Foto não encontrada"));
        return ResponseEntity.ok(foto);
    }

    @GetMapping("/por-anuncio/{anuncioId}")
    public ResponseEntity<Object> getFotosByAnuncio(@PathVariable Long anuncioId) {
        List<Foto> fotos = fotoService.getByAnuncioId(anuncioId);
        if (fotos == null || fotos.isEmpty())
            return ResponseEntity.badRequest().body(new Erro("Nenhuma foto para esse anúncio"));
        return ResponseEntity.ok(fotos);
    }

    @PostMapping
    public ResponseEntity<Object> addFoto(@RequestBody Foto foto) {
        Foto novaFoto = fotoService.save(foto);
        if (novaFoto == null)
            return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar foto"));
        return ResponseEntity.ok(novaFoto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFoto(@PathVariable Long id) {
        if (fotoService.delete(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body(new Erro("Erro ao excluir a foto"));
    }
}
