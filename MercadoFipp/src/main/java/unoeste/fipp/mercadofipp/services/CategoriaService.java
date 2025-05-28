package unoeste.fipp.mercadofipp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.mercadofipp.entities.Categoria;
import unoeste.fipp.mercadofipp.repositories.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    public Categoria getById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria save(Categoria categoria) {
        try {
            return categoriaRepository.save(categoria);
        } catch (Exception e) {
            return null;
        }
    }

    public Categoria update(Categoria categoria) {
        if (categoria == null || categoria.getId() == null)
            return null;

        Optional<Categoria> existente = categoriaRepository.findById(categoria.getId());
        if (existente.isPresent()) {
            try {
                return categoriaRepository.save(categoria);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public boolean delete(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            try {
                categoriaRepository.delete(categoria.get());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
