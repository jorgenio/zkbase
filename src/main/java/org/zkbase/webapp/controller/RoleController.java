package org.zkbase.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkbase.model.Role;
import org.zkbase.service.RoleService;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;

public class RoleController extends GenericForwardComposer {

	private Textbox name;
	private Textbox description;

	@Autowired
	RoleService roleService;

	public List<Role> getList() {
		return roleService.findAll();
	}

	public void onClick$add(Event e) {
		Role role = new Role();
		role.setName(name.getValue());
		role.setDescription(description.getValue());
		roleService.persist(role);
	}
}
