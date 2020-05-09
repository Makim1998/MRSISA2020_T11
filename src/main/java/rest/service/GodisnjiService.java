package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.GodisnjiOdmor;
import rest.domain.Sala;
import rest.repository.GodisnjiRepository;
import rest.repository.SalaRepository;

@Service 

public class GodisnjiService {

	@Autowired
	private GodisnjiRepository gRepository;
	
	public GodisnjiOdmor save(GodisnjiOdmor go) {
		// TODO Auto-generated method stub
		return gRepository.save(go);
	}

	public List<GodisnjiOdmor> findAll() {
		return gRepository.findByPrihvacenOdbijenIsNull();
	}


	public GodisnjiOdmor findOne(Integer id) {
		// TODO Auto-generated method stub
		return gRepository.findById(id).orElseGet(null);
	}

}
