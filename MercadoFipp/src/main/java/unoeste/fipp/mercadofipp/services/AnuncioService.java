package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Foto;
import unoeste.fipp.mercadofipp.repositories.AnuncioRepository;
import unoeste.fipp.mercadofipp.repositories.FotoRepository;

import java.util.List;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private FotoRepository fotoRepository;

    public List<Anuncio> getAll() {
        return anuncioRepository.findAll();
    }

    public Anuncio add(Anuncio anuncio) {
        Anuncio novoAnuncio = anuncioRepository.save(anuncio);
        List<Foto> fotos = anuncio.getFotos();
        if (fotos != null && !fotos.isEmpty()) {
            for (Foto foto : fotos) {
                foto.setAnuncio(novoAnuncio);
                fotoRepository.save(foto);
            }
        }
        return novoAnuncio;
    }

    public boolean addPergunta(long id_anuncio, String texto) {
        try {
            anuncioRepository.addPergunta(texto, id_anuncio);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addResposta(Long id_pergunta, String texto) {
        try {
            anuncioRepository.addResposta(texto, id_pergunta);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Anuncio> getByUsuario(Long id) {
        return anuncioRepository.findByUsuarioId(id);
    }

    public List<Anuncio> getByFiltro(String filtro) {
        return anuncioRepository.findByFiltro(filtro);
    }

    public Anuncio getById(Long id) {
        return anuncioRepository.findById(id).orElse(null);
    }

    public boolean delete(Long id) {
        try {
            anuncioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
