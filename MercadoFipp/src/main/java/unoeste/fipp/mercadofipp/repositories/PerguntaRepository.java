package unoeste.fipp.mercadofipp.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    @Modifying
    @Transactional
    @Query(value="INSERT INTO pergunta_anuncio (per_resp,per_id) VALUES (:texto, :id_pergunta) ",nativeQuery = true)
    public void addResposta(@Param("texto") String texto, @Param("id_pergunta")Long id_pergunta);
}
