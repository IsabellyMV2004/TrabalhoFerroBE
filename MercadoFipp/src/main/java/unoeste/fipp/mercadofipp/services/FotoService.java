package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Foto;
import unoeste.fipp.mercadofipp.repositories.FotoRepository;

import java.util.List;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

    public List<Foto> getAll() {
        return fotoRepository.findAll();
    }

    public Foto getById(Long id) {
        return fotoRepository.findById(id).orElse(null);
    }

    public List<Foto> getByAnuncioId(Long anuncioId) {
        return fotoRepository.findByAnuncioId(anuncioId);
    }

    public Foto save(Foto foto) {
        try {
            return fotoRepository.save(foto);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean delete(Long id) {
        try {
            Foto foto = fotoRepository.findById(id).orElse(null);
            if (foto != null) {
                fotoRepository.delete(foto);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
