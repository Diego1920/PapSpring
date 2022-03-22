package org.diego.pap2021.repository;

import java.util.List;

import org.diego.pap2021.entities.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long>{

	/*
	public List<Pais> findAll() {
		return paisRepository.findAll();
	}

	public Pais save(String nombre) {
		return paisRepository.save(new Pais(nombre));
	}
	
	public Pais update(Pais pais) {
		Pais p = paisRepository.getById(pais.getId());
		p.setNombre(pais.getNombre());
		paisRepository.saveAndFlush(p);
		return pais;
	}

	public void delete(Long id) {
		paisRepository.deleteById(id);
	}

	*/
	
}
