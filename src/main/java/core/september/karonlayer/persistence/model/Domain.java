package core.september.karonlayer.persistence.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Domain {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String alias;
	private String description;
	
	 @ManyToMany(fetch=FetchType.EAGER)
	  @JoinTable
	  (
	      name="user_domains",
	      joinColumns={ @JoinColumn(name="domain_id", referencedColumnName="id") },
	      inverseJoinColumns={ @JoinColumn(name="user_id", referencedColumnName="id") }
	  )
	private Set<User> user;
	 
	 @ManyToMany(fetch=FetchType.EAGER)
	  @JoinTable
	  (
	      name="owner_domains",
	      joinColumns={ @JoinColumn(name="domain_id", referencedColumnName="id") },
	      inverseJoinColumns={ @JoinColumn(name="user_id", referencedColumnName="id") }
	  )
	private Set<User> owner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getOwner() {
		return owner;
	}

	public void setOwner(Set<User> owner) {
		this.owner = owner;
	}
	
	
	
	
	 
	

}
