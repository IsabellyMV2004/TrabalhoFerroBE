package unoeste.fipp.mercadofipp.services;

import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.repositories.PerguntaRepository;

@Service
public class PerguntaService {

    private PerguntaRepository perguntaRepository;

    public boolean addResposta(Long id_pergunta, String texto){
        try{
            perguntaRepository.addResposta(texto, id_pergunta);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
}
