package org.zkbase.webapp.composer;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkbase.dao.EntityNotFoundException;
import org.zkbase.model.User;
import org.zkbase.webapp.model.UserModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Window;

public class UserComposer extends GenericForwardComposer {
	
	private Window userWin;

	@Autowired
	private UserModel userModel;
	AnnotateDataBinder binder;
	
	public void setNewUser() {
		User user = new User();
		user.setId(null);
		this.userModel.setSelected(user);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);		
		binder = new AnnotateDataBinder(userWin);
		this.setNewUser();
		binder.loadAll();
	}
	
	public void onClick$add(Event e) throws EntityNotFoundException {
		this.userModel.persist();
		Events.sendEvent(new Event("onClose", userWin));
	}

	public void onClick$update(Event e) throws EntityNotFoundException {
		this.userModel.merge();
		binder.loadAll(); 
	}
	
	public void onClick$del(Event e) throws EntityNotFoundException {
		this.userModel.delete();
		this.setNewUser();
		binder.loadAll();
	}
	
	public void onClick$cancel(Event e) throws EntityNotFoundException {
		this.setNewUser();
		binder.loadAll();
	}
}
