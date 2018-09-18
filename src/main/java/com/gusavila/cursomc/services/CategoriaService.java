package com.gusavila.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gusavila.cursomc.domain.Categoria;
import com.gusavila.cursomc.repositories.CategoriaRepository;
import com.gusavila.cursomc.services.exceptions.DataIntegrityException;
import com.gusavila.cursomc.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> optional = repo.findById(id);
		return optional.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void deleteById(Integer id) {
		find(id);
		try {
			repo.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível remover uma categoria que possui produtos");
		}
	}
}
