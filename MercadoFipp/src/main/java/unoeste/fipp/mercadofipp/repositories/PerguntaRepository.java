package unoeste.fipp.mercadofipp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unoeste.fipp.mercadofipp.entities.Pergunta;

import java.util.List;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    // Busca perguntas de um anúncio específico
    @Query("SELECT p FROM Pergunta p WHERE p.anuncio.id = :anuncioId")
    List<Pergunta> findByAnuncioId(Long anuncioId);
}
