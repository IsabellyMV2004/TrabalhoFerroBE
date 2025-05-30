package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Anuncio;
import unoeste.fipp.mercadofipp.entities.Categoria;
import unoeste.fipp.mercadofipp.entities.Foto;
import unoeste.fipp.mercadofipp.entities.Pergunta;
import unoeste.fipp.mercadofipp.repositories.AnuncioRepository;
import unoeste.fipp.mercadofipp.repositories.FotoRepository;

import java.util.List;

@Service
public class AnuncioService {
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private FotoRepository fotoRepository;
    public List<Anuncio> getAll(){
        return anuncioRepository.findAll();
    }

    public Anuncio getById(Long id){
        return anuncioRepository.findById(id).orElse(null);
    }

    public List<Anuncio> getName(String titulo) {
        return anuncioRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public Anuncio add(Anuncio anuncio)
    {
        //agora grave as fotos na tabela de fotos
        //for...
        //anuncioRespository.addFoto(...)
        Anuncio novoAnuncio=anuncioRepository.save(anuncio);
        List<Foto> fotos = anuncio.getFotos();
        if(fotos != null && !fotos.isEmpty()){
            for(Foto foto: fotos){
                foto.setAnuncio(novoAnuncio);
                fotoRepository.save(foto);
            }
        }
        return novoAnuncio;
    }



    public boolean addPergunta(long id_anuncio, String texto){
        try{
            anuncioRepository.addPergunta(texto, id_anuncio);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    public boolean delete(Long id){
        Anuncio anuncio = anuncioRepository.findById(id).orElse(null);
        try{
            anuncioRepository.delete(anuncio);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
