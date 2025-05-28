package unoeste.fipp.mercadofipp.restcontrollers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.mercadofipp.entities.Erro;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.services.UsuarioService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("apis/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public ResponseEntity<Object> getUsuarios() {
        List<Usuario> usuarioList = usuarioService.getAll();
        if (usuarioList.isEmpty())
            return ResponseEntity.badRequest().body(new Erro("Não possui dados"));
        return ResponseEntity.ok(usuarioList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioId(@PathVariable(name = "id") Long id) {
        Usuario usuario = usuarioService.getById(id);
        if (usuario == null)
            return ResponseEntity.badRequest().body(new Erro("Usuário não encontrado"));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Object> addUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.save(usuario);
        if (novoUsuario == null)
            return ResponseEntity.badRequest().body(new Erro("Erro ao cadastrar o novo usuário"));
        return ResponseEntity.ok(novoUsuario);
    }

    @PutMapping
    public ResponseEntity<Object> updateUsuario(@RequestBody Usuario usuario) {
        Usuario atualizado = usuarioService.save(usuario);
        if (atualizado == null)
            return ResponseEntity.badRequest().body(new Erro("Erro ao alterar o usuário"));
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Long id) {
        boolean sucesso = usuarioService.delete(id);
        if (sucesso)
            return ResponseEntity.ok(Map.of("mensagem", "Usuário deletado com sucesso"));
        return ResponseEntity.badRequest().body(new Erro("Usuário não encontrado ou erro ao deletar"));
    }

    @PostMapping("/logar")
    public ResponseEntity<Object> logar(@RequestBody Usuario usuario) {
        Usuario logado = usuarioService.logar(usuario.getNome(), usuario.getSenha());
        if (logado == null)
            return ResponseEntity.badRequest().body(new Erro("Usuário ou senha inválidos"));
        return ResponseEntity.ok(Map.of(
                "id", logado.getId(),
                "nome", logado.getNome(),
                "nivel", logado.getNivel(),
                "token", "token-ficticio-exemplo" // token fictício, substitua se for usar JWT
        ));
    }
}
