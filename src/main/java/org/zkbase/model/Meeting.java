package org.zkbase.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="zkb_meeting")
public class Meeting {
	private Long id;
	private Long number;
	private String title;
	private String content;
	private User leader;
	private Set<User> participants; 
	
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
	@OneToMany(fetch=FetchType.EAGER)
	public Set<User> getParticipants() {
		return participants;
	}
	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((leader == null) ? 0 : leader.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meeting other = (Meeting) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (leader == null) {
			if (other.leader != null)
				return false;
		} else if (!leader.equals(other.leader))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return this.number + " " + this.getTitle();
	}
}
