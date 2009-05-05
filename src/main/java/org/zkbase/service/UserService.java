package org.zkbase.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zkbase.dao.EntityNotFoundException;
import org.zkbase.model.User;

@Transactional(readOnly=true,propagation = Propagation.REQUIRED)
public class UserService extends GenericService<User> {

	public UserService() {
		super(User.class);
	}

	public User findByUserName(String username) {
		return (User)super.findByNamedQuerySingle("User.findByName", username);
	}

	public List<User> findByUserNameLike(String username, int firstResult,
			int maxResults) throws EntityNotFoundException {
		return super.findByNamedQuery("User.findByNameLike", firstResult,
				maxResults, username + "%");
	}

	@Override
	public List<User> findByExample(User example, int firstResult, int maxResults) {

		return super.findByNamedQuery("User.findByExample", firstResult,
				maxResults, example.getUsername(), example.getFirstName());
	}
	
	public Long countByExample(User example) {
		return (Long)super.findByNamedQuerySingle("User.countByExample", example.getUsername(), example.getFirstName());
	}

}
