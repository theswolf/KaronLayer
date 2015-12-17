package core.september.karonlayer.persistence.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class App {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String name;
	private String description;
	@Enumerated(EnumType.STRING)
	private AppType type;
	
	@Column
	@ElementCollection(targetClass=HashMap.class)
	private Map<String,String> defaultConf;
	
	
	 @ManyToOne(fetch=FetchType.EAGER)
	 @JoinColumn(name="domain_id")
	 @NotNull
	 private Domain domain;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AppType getType() {
		return type;
	}

	public void setType(AppType type) {
		this.type = type;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Map<String, String> getDefaultConf() {
		return defaultConf;
	}

	public void setDefaultConf(Map<String, String> defaultConf) {
		this.defaultConf = defaultConf;
	}
	
	
	 
	 

}
