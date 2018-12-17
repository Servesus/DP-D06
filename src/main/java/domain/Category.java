
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	//Attributes 
	private String	name;


	//Getters and setters
	@NotBlank
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}


	//Relationships 
	private Collection<Category>	childs;
	private Collection<Category>	parents;


	@ManyToMany(mappedBy = "parents")
	public Collection<Category> getChilds() {
		return this.childs;
	}
	@ManyToMany
	public Collection<Category> getParents() {
		return this.parents;
	}

	public void setChilds(final Collection<Category> childs) {
		this.childs = childs;
	}

	public void setParents(final Collection<Category> parents) {
		this.parents = parents;
	}

}
