package scicafe.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "programs")
public class Program implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	 @GeneratedValue
	 @Column(name = "id")
	private long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String fullname;
	@Column(nullable = false)
	private String description;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
