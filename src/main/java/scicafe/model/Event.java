package scicafe.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "events")
public class Event implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	 @GeneratedValue
	 @Column(name = "id")
	private Long event_id;
	
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String location;
	@Column(nullable = false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-DD-yyyy")
	private Date starttime;
	@Column(nullable = false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-DD-yyyy")
	private Date endtime;
	
	//tags
	@ManyToMany
	 @JoinTable(name = "eventtags",
		    joinColumns = @JoinColumn(name = "event_id"),
		    inverseJoinColumns = @JoinColumn(name = "tag_id"))
			private Set<Tag> tags;

	
	@ManyToOne
	@JoinColumn(name = "approvedby", referencedColumnName="id")
	private User approvedby;
	
	@ManyToOne
	@JoinColumn(name = "submitter", referencedColumnName="id")
	private User submitter;
	
	@ManyToMany
	 @JoinTable(name = "attendees",
		    joinColumns = @JoinColumn(name = "event_id"),
		    inverseJoinColumns = @JoinColumn(name = "user_id"))
			private Set<User> attendees;
	
	
	private boolean isApproved;
	
	public Event(){
		tags = new HashSet<Tag>();
		attendees = new HashSet<User>();		
	}
	public Long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String edescription) {
		this.description = edescription;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStarttime() {
		return starttime.toString();
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime.toString();
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getAttendees() {
		return attendees;
	}
	public void setAttendees(Set<User> attendees) {
		this.attendees = attendees;
	}
	public User getApprovedby() {
		return approvedby;
	}
	public void setApprovedby(User approvedby) {
		this.approvedby = approvedby;
	}
	
	public User getSubmitter() {
		return submitter;
	}
	public void setSubmitter(User submitter) {
		this.submitter = submitter;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
}
