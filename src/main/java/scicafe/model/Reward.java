package scicafe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rewards")
public class Reward implements Serializable{

	private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue
	private Long id;
	 
	 @Column(nullable = false)
	 private String title;
	 
	 @Column(nullable = false)
	private String description;
	 
	 @Column(nullable = false)
	private String organisation;
	
	 @ManyToOne
	private User submitter;
	 
	 //Qualified events //doubt
	
	 @ManyToMany
	 @Column(nullable = false)
		@JoinTable(name = "eventrewards",
	    joinColumns = @JoinColumn(name = "event_id"),
	    inverseJoinColumns = @JoinColumn(name = "reward_id"))
		Set<Event> eventrewards;
		
	// List<Event> events;
	 
	 
	 @Column(nullable = false)
	private Date rewardstart;
	 @Column(nullable = false)
	private Date rewardend;
	 //reward criteria
	private int mineventsattended = 1;

	
	public Reward(){
		
		eventrewards = new HashSet<Event>();
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getRewardstart() {
		return rewardstart;
	}
	public void setRewardstart(Date rewardstart) {
		this.rewardstart = rewardstart;
	}
	public Date getRewardend() {
		return rewardend;
	}
	public void setRewardend(Date rewardend) {
		this.rewardend = rewardend;
	}
	public Set<Event> getEvents() {
		return eventrewards;
	}
	public void setEvents(List<Event> events) {
		this.eventrewards = eventrewards;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getOrganisation() {
		return organisation;
	}


	public void setOrganisation(String provider) {
		this.organisation = organisation;
	}


	public User getSubmitter() {
		return submitter;
	}


	public void setSubmitter(User submitter) {
		this.submitter = submitter;
	}


	public int getMineventsattended() {
		return mineventsattended;
	}


	public void setMineventsattended(int mineventsattended) {
		this.mineventsattended = mineventsattended;
	}

	
}
