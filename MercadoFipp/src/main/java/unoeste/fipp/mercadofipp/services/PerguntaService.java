package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Pergunta;
import unoeste.fipp.mercadofipp.repositories.PerguntaRepository;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;

    public Pergunta addResposta(Long id_pergunta, String texto){
        try {
            perguntaRepository.addResposta(texto, id_pergunta);
            return perguntaRepository.findById(id_pergunta).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
