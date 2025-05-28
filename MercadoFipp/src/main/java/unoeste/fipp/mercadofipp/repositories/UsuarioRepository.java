package unoeste.fipp.mercadofipp.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unoeste.fipp.mercadofipp.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuário por nome e senha (login)
    @Query(value = "SELECT * FROM usuario WHERE usr_name = :nome AND usr_pass = :senha", nativeQuery = true)
    Usuario autenticar(@Param("nome") String nome, @Param("senha") String senha);

    // Atualizar senha do usuário
    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario SET usr_pass = :novaSenha WHERE usr_id = :id", nativeQuery = true)
    void atualizarSenha(@Param("id") Long id, @Param("novaSenha") String novaSenha);

    // Inserir novo usuário
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuario (usr_name, usr_pass, usr_level) VALUES (:nome, :senha, :nivel)", nativeQuery = true)
    void adicionarUsuario(@Param("nome") String nome, @Param("senha") String senha, @Param("nivel") int nivel);
}
