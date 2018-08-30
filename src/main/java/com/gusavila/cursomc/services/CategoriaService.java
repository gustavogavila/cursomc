package com.gusavila.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gusavila.cursomc.domain.Categoria;
import com.gusavila.cursomc.repositories.CategoriaRepository;
import com.gusavila.cursomc.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> optional = repo.findById(id);
		return optional.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}
}
