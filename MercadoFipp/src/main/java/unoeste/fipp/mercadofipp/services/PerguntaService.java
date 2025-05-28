package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Pergunta;
import unoeste.fipp.mercadofipp.repositories.PerguntaRepository;

import java.util.List;

@Service
public class PerguntaService {
    @Autowired
    private PerguntaRepository perguntaRepository;

    public List<Pergunta> getAll() {
        return perguntaRepository.findAll();
    }

    public Pergunta getById(Long id) {
        return perguntaRepository.findById(id).orElse(null);
    }

    public List<Pergunta> getByAnuncioId(Long anuncioId) {
        return perguntaRepository.findByAnuncioId(anuncioId);
    }

    public Pergunta save(Pergunta pergunta) {
        try {
            return perguntaRepository.save(pergunta);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean delete(Long id) {
        Pergunta pergunta = perguntaRepository.findById(id).orElse(null);
        if (pergunta != null) {
            try {
                perguntaRepository.delete(pergunta);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
