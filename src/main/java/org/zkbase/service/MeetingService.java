package org.zkbase.service;

import java.util.List;

import org.zkbase.model.Meeting;

public class MeetingService extends GenericService<Meeting> {

	public MeetingService() {
		super(Meeting.class);
	}

	@Override
	public Long countByExample(Meeting example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Meeting> findByExample(Meeting example, int firstResult,
			int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

}
