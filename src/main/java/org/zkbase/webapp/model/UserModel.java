package org.zkbase.webapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkbase.dao.EntityNotFoundException;
import org.zkbase.model.User;
import org.zkbase.service.UserService;

public class UserModel {
	
	@Autowired
	private UserService userService;
	
	private User selected;
	
	public List<User> list() {
		return userService.findAll();
	}
	public void persist() {
		userService.persist(selected);
	}
	public void merge() throws EntityNotFoundException {
		userService.merge(selected);
	}
	public void delete() throws EntityNotFoundException {
		userService.delete(selected.getId());
	}

	public User getSelected() {
		return selected;
	}

	public void setSelected(User selected) {
		this.selected = selected;
	}

}
