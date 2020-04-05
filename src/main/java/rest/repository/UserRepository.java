package rest.repository;

import java.util.Collection;

import rest.domain.User;

public interface UserRepository {
	Collection<User> findAll();

	User create(User user);

	User findOne(String id);
	
	User update(User user);

	void delete(String id);
}
