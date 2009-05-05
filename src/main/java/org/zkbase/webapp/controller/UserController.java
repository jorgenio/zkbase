package org.zkbase.webapp.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkbase.model.Role;
import org.zkbase.model.User;
import org.zkbase.service.RoleService;
import org.zkbase.service.UserService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.event.PagingEvent;

public class UserController extends GenericForwardComposer implements
		ListitemRenderer {
    private final Log log = LogFactory.getLog(getClass());

	protected Textbox username;
	protected Textbox firstName;
	protected Textbox lastName;
	protected Textbox password;
	protected Textbox email;
	protected Textbox searchField;
	protected Paging pageUsers;
	protected Listbox userListAll;
	protected ListModelList listModelList;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	public void createDemoAccount(String name, Set<Role> roles) {
		log.debug("Creating demo account for user " + name);
		User u = new User();
		u.setFirstName(name);
		u.setLastName("name");
		u.setPassword(name);
		u.setUsername(name);
		u.setEmail(name + "@zkbase.org");
		u.setAccountExpired(false);
		u.setAccountLocked(false);
		u.setCredentialsExpired(false);
		u.setEnabled(true);

		u.setRoles(roles);

		userService.persist(u);
	}

	public void onClick$search(Event e) {
		log.info("onClick: search");
		User example = new User();
		example.setUsername("%" + searchField.getValue() + "%");
		example.setFirstName("%" +searchField.getValue() + "%");
		List<User> users = userService.findByExample(example, 0, 200);
		
		listModelList.clear();
		listModelList.addAll(users);
		userListAll.setItemRenderer(this);
		userListAll.setModel(listModelList);
	}

	public void onClick$init(Event e) {
		log.info("onClick: init");
		// insert roles:
		Role r1 = new Role();
		r1.setName("ROLE_USER");
		r1.setDescription("Common user privileges");
		Role r2 = new Role();
		r2.setName("ROLE_ADMIN");
		r2.setDescription("Administrator privileges");

		roleService.persist(r1);
		roleService.persist(r2);

		Set<Role> roles = new HashSet<Role>();
		roles.add(r1);
		// set up users accounts

		for (int i = 1; i <= 100; ++i)
			this.createDemoAccount("user" + i, roles);

		roles.add(r2);
		// set up admin account:
		this.createDemoAccount("admin", roles);

		buildUserList();
	}

	public List<User> getList() {
		return userService.findAll();
	}

	public void onClick$add(Event e) {
		log.info("onClick: add");

		User u = new User();
		u.setFirstName(firstName.getValue());
		u.setLastName(lastName.getValue());
		u.setPassword(password.getValue());
		u.setUsername(username.getValue());
		u.setEmail(email.getValue());

		// u.setRoles(roles);

		userService.persist(u);
		buildUserList();
	}

	private void buildUserList() {
		log.info("Building user list");
		
		final Long userCount = userService.count();
		final int pageSize = pageUsers.getPageSize();
		pageUsers.setTotalSize(userCount.intValue());
		pageUsers.setActivePage(0);

		List<User> users = userService.findAll(0, pageSize);		
		listModelList.clear();
		listModelList.addAll(users);
		userListAll.setItemRenderer(this);
		userListAll.setModel(listModelList);

	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		listModelList = new ListModelList();

		buildUserList();
		final int pageSize = pageUsers.getPageSize();
		pageUsers.addEventListener("onPaging", new EventListener() {
			public void onEvent(Event event) {

				PagingEvent pe = (PagingEvent) event;
				int pgno = pe.getActivePage();
				int firstResult = pgno * pageSize;
				listModelList.clear();
				List<User> users = userService.findAll(firstResult, pageSize);
				listModelList.addAll(users);
				userListAll.setModel(listModelList);
			}
		});
	}

	protected String getUserRolesString(User user) {
		Iterator<Role> iter = user.getRoles().iterator();
		String userRolesString = "";
		while (iter.hasNext()) {
			userRolesString += iter.next().getName();
			if (iter.hasNext())
				userRolesString += ", ";
		}
		return userRolesString;
	}

	@Override
	public void render(Listitem listItem, Object data) throws Exception {
		User user = (User) data;
		new Listcell(user.getUsername() + "").setParent(listItem);
		new Listcell(user.getFirstName() + "").setParent(listItem);
		new Listcell(user.getLastName() + "").setParent(listItem);
		new Listcell(user.getEmail() + "").setParent(listItem);
		new Listcell(this.getUserRolesString(user)).setParent(listItem);
	}
}

// class UserListModel extends ListModelList {
//
// private static final long serialVersionUID = -8699018351228988340L;
//	
// @SuppressWarnings("unchecked")
// @Override
// public void sort(Comparator cmpr, boolean ascending) {
// Collections.sort(getInnerList() , cmpr);
// fireEvent(org.zkoss.zul.event.ListDataEvent.CONTENTS_CHANGED, -1, -1);
// }
//
// }

