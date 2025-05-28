package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Usuario;
import unoeste.fipp.mercadofipp.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            try {
                usuarioRepository.delete(usuario.get());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public Usuario logar(String nome, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByNomeAndSenha(nome, senha);
        return usuario.orElse(null);
    }
}
