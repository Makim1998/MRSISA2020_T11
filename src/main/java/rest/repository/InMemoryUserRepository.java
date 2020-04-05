package rest.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import rest.domain.User;

@Repository
public  class InMemoryUserRepository implements UserRepository{
	private final ConcurrentMap<String, User> users = new ConcurrentHashMap<String, User>();

	@Override
	public Collection<User> findAll() {
		users.put("pacijent", new User("pacijent","pacijent"));
		return this.users.values();
	}

	@Override
	public User create(User user) {
		this.users.put(user.getUsername(), user);
		return user;
	}

	@Override
	public User findOne(String id) {
		return this.users.get(id);
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
