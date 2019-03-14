package scicafe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "users")
public class User implements Serializable{

	 private static final long serialVersionUID = 1L;
	 
	 @Id
	 @GeneratedValue
	private Long id;

	 @Column(nullable = false)
	private String fname;
	 @Column(nullable = false)
	private String lname;
	 @Column(nullable = false)
	private String position;
	 @Column(nullable = false)
	private String organisation;
	@Column(nullable = false, unique = true)
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
    @Column(nullable = false)
	private String password;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	
	private String title;
	
	@ManyToMany
	@JoinTable(name = "affliations",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "program_id"))
	private Set<Program> affliations;
	
	private boolean enabled = true;
	
	
	private boolean isadministrator;
	private boolean iseventorganiser;
	private boolean isrewardprovider;
	
	public User(){
		affliations = new HashSet<Program>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public Set<Program> getAffliations() {
		return affliations;
	}
	public void setAffliations(Set<Program> affliations) {
		this.affliations = affliations;
	}
	public boolean isAdministrator() {
		return isadministrator;
	}
	public void setAdministrator(boolean isadministrator) {
		this.isadministrator = isadministrator;
	}
	public boolean isEventOrganiser() {
		return iseventorganiser;
	}
	public void setEventorganiser(boolean iseventorganiser) {
		this.iseventorganiser = iseventorganiser;
	}
	public boolean isRewardprovider() {
		return isrewardprovider;
	}
	public void setRewardProvider(boolean isrewardprovider) {
		this.isrewardprovider = isrewardprovider;
	}

}
