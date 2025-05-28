package unoeste.fipp.mercadofipp.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import unoeste.fipp.mercadofipp.entities.Foto;

import java.util.List;

public interface FotoRepository extends JpaRepository<Foto, Long> {

    @Query(value = "SELECT * FROM foto_anuncio WHERE anu_id = :anuncioId", nativeQuery = true)
    List<Foto> findByAnuncioId(@Param("anuncioId") Long anuncioId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO foto_anuncio (fot_file, anu_id) VALUES (:arquivo, :anuncioId)", nativeQuery = true)
    void addFoto(@Param("arquivo") String arquivo, @Param("anuncioId") Long anuncioId);
}
