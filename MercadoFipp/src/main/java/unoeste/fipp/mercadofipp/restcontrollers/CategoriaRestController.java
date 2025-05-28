package unoeste.fipp.mercadofipp.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Categoria;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.services.CategoriaService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("apis/categoria")
public class CategoriaRestController {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private boolean isAdmin() {
        Object nivel = httpServletRequest.getAttribute("nivel");
        return nivel != null && nivel.equals("ADM");
    }

    private boolean isUsuarioOuAdmin() {
        Object nivel = httpServletRequest.getAttribute("nivel");
        return nivel != null && (nivel.equals("ADM") || nivel.equals("USUARIO"));
    }

    @GetMapping
    public ResponseEntity<Object> getCategorias() {
        if (!isUsuarioOuAdmin()) {
            return ResponseEntity.status(403).body(new Erro("Acesso negado"));
        }
        List<Categoria> categorias = categoriaService.getAll();
        if (categorias == null || categorias.isEmpty()) {
            return ResponseEntity.badRequest().body(new Erro("Categorias não encontradas"));
        }
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoriaId(@PathVariable("id") Long id) {
        if (!isUsuarioOuAdmin()) {
            return ResponseEntity.status(403).body(new Erro("Acesso negado"));
        }
        Categoria categoria = categoriaService.getById(id);
        if (categoria == null)
            return ResponseEntity.badRequest().body(new Erro("Categoria não encontrada"));
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Object> addCategoria(@RequestBody Categoria categoria) {
        if (!isAdmin()) {
            return ResponseEntity.status(403).body(new Erro("Acesso negado"));
        }
        Categoria novaCategoria = categoriaService.save(categoria);
        if (novaCategoria == null)
            return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar a categoria"));
        return ResponseEntity.ok(novaCategoria);
    }

    @PutMapping
    public ResponseEntity<Object> updateCategoria(@RequestBody Categoria categoria) {
        if (!isAdmin()) {
            return ResponseEntity.status(403).body(new Erro("Acesso negado"));
        }
        Categoria atualizada = categoriaService.update(categoria);
        if (atualizada == null)
            return ResponseEntity.badRequest().body(new Erro("Erro ao atualizar a categoria"));
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategoria(@PathVariable Long id) {
        if (!isAdmin()) {
            return ResponseEntity.status(403).body(new Erro("Acesso negado"));
        }
        if (categoriaService.delete(id))
            return ResponseEntity.ok(Map.of("mensagem", "Categoria deletada com sucesso"));
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao deletar categoria"));
    }
}
