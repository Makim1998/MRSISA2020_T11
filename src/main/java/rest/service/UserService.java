package rest.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.User;
import rest.repository.UserRepository;

@Service
public class UserService  {
	@Autowired
	private UserRepository userRepository;
	
	
	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	
	public User findOne(Integer id) {
		return  userRepository.findById(id).orElseGet(null);
	}

	
	public User create(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User findByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void remove(Integer id) {
		userRepository.deleteById(id);
	}

}
