package org.zkbase.webapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkbase.dao.EntityNotFoundException;
import org.zkbase.model.Meeting;
import org.zkbase.service.MeetingService;

public class MeetingModel {
	
	@Autowired
	private MeetingService meetingService;
	
	private Meeting selected;
	
	public List<Meeting> list() {
		return meetingService.findAll();
	}
	public void persist() {
		meetingService.persist(selected);
	}
	public void merge() throws EntityNotFoundException {
		meetingService.merge(selected);
	}
	public void delete() throws EntityNotFoundException {
			meetingService.delete(selected.getId());
	}
	public Meeting getSelected() {
		return selected;
	}
	public void setSelected(Meeting selected) {
		this.selected = selected;
	}
}
