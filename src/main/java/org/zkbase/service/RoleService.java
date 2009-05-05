package org.zkbase.service;

import java.util.List;

import org.zkbase.model.Role;

public class RoleService extends GenericService<Role> {

	public RoleService() {
		super(Role.class);
	}

	@Override
	public List<Role> findByExample(Role example, int firstResult,
			int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countByExample(Role example) {
		// TODO Auto-generated method stub
		return null;
	}
}
