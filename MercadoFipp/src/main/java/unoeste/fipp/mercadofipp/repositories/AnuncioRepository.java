package unoeste.fipp.mercadofipp.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import unoeste.fipp.mercadofipp.entities.Anuncio;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pergunta_anuncio (per_text, anu_id) VALUES (:texto, :id_anuncio)", nativeQuery = true)
    void addPergunta(@Param("texto") String texto, @Param("id_anuncio") Long id_anuncio);

    @Modifying
    @Transactional
    @Query(value = "UPDATE pergunta_anuncio SET per_resp = :texto WHERE per_id = :id_pergunta", nativeQuery = true)
    void addResposta(@Param("texto") String texto, @Param("id_pergunta") Long id_pergunta);

    @Query("SELECT a FROM Anuncio a WHERE a.usuario.id = :id")
    List<Anuncio> findByUsuarioId(@Param("id") Long id);

    @Query("SELECT a FROM Anuncio a WHERE LOWER(a.titulo) LIKE LOWER(CONCAT('%', :filtro, '%')) OR LOWER(a.descricao) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<Anuncio> findByFiltro(@Param("filtro") String filtro);
}
