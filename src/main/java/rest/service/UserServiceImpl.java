package rest.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.domain.User;
import rest.repository.InMemoryUserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private InMemoryUserRepository userRepository;
	@Override
	public Collection<User> findAll() {
		Collection<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User findOne(String id) {
		User user = userRepository.findOne(id);
		return user;
	}

	@Override
	public User create(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
