package org.zkbase.webapp.controller;

import org.zkbase.model.User;
import org.zkbase.service.GenericService;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

public class GenUserListComposer extends GenericListComposer<User> {
		

	public GenUserListComposer() {
		super("userService");
	}

	@Override	
	protected User getSearchExample(String query) {
		User example = new User();
		example.setFirstName("%" + query + "%");
		example.setUsername("%" + query + "%");
		return example;
	}

	@Override
	public void render(Listitem listItem, Object data) throws Exception {
		User user = (User)data;
		new Listcell(user.getFirstName()).setParent(listItem);
		new Listcell(user.getLastName()).setParent(listItem);
	}

}
