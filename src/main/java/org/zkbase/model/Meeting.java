package org.zkbase.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="zkb_meeting")
public class Meeting {
	private Long id;
	private Long number;
	private String title;
	private String content;
	private User leader;
//	private List<User> participants; 
	
	@Id
	@TableGenerator(
			name="meetingGen",
			table="ID_GEN",
			pkColumnName="GEN_KEY",
			valueColumnName="GEN_VALUE",
			pkColumnValue="MEETING_ID",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="meetingGen")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
//	@OneToOne
//	public User getLeader() {
//		return leader;
//	}
//	public void setLeader(User leader) {
//		this.leader = leader;
//	}
//	@OneToMany
//	public List<User> getParticipants() {
//		return participants;
//	}
//	public void setParticipants(List<User> participants) {
//		this.participants = participants;
//	}
	
	@OneToOne
	public User getLeader() {
		return leader;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public void setLeader(User leader) {
		this.leader = leader;
	}
	
	@Override
	public String toString() {
		return this.number + " " + this.getTitle();
	}
}
