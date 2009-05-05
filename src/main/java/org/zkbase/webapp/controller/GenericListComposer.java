package org.zkbase.webapp.controller;

import java.util.List;

import org.zkbase.model.User;
import org.zkbase.service.GenericService;
import org.zkbase.service.UserService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.event.PagingEvent;

public abstract class GenericListComposer<T> extends GenericForwardComposer
		implements ListitemRenderer {

	protected Textbox glc_searchField;
	protected Paging glc_page;
	protected Listbox glc_listbox;
	protected ListModelList listModelList;

	GenericService<T> service;
	T example;

	@SuppressWarnings("unchecked")
	public GenericListComposer(String serviceName) {
		super();
		this.service = (GenericService<T>) SpringUtil.getBean(serviceName);
		if (this.service == null)
			System.out.println("ERROR: service not found");
	}

	public void onClick$search(Event e) {
		example = getSearchExample(glc_searchField.getValue());
		Long size = service.countByExample(example);
		glc_page.setTotalSize(size.intValue());
		glc_page.setActivePage(0);		
		buildList(0);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		listModelList = new ListModelList();

		final Long count = service.count();
		glc_page.setTotalSize(count.intValue());
		glc_page.setActivePage(0);
		glc_page.addEventListener("onPaging", new GlcOnPageEventListener());

		glc_listbox.setItemRenderer(this);
		buildList(0);
	}

	private void buildList(int firstResult) {
		List<T> list = null;

		if (example == null) {
			list = service.findAll(firstResult, glc_page.getPageSize());
		} else {
			list = service.findByExample(example, firstResult, glc_page.getPageSize());
		}

		listModelList.clear();
		listModelList.addAll(list);
		glc_listbox.setModel(listModelList);
	}

	private class GlcOnPageEventListener implements EventListener {

		@Override
		public void onEvent(Event event) throws Exception {
			PagingEvent pe = (PagingEvent) event;
			int pgno = pe.getActivePage();
			int firstResult = pgno * glc_page.getPageSize();
			buildList(firstResult);
		}
	}

	abstract protected T getSearchExample(String query);

	abstract public void render(Listitem listItem, Object data)
			throws Exception;
}
