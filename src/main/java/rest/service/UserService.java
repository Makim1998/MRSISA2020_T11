package rest.service;

import java.util.Collection;

import rest.domain.User;

public interface UserService {
	Collection<User> findAll();

	User create(User user) throws Exception;; 

	User findOne(String id);
	
	User update(User user) throws Exception;;

	void delete(String id);
}
