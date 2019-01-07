
package domain;

import java.util.Collection;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	//Attributes 
	private Map<String, String>	name;


	//Getters and setters
	@NotNull
	@ElementCollection
	public Map<String, String> getName() {
		return this.name;
	}
	public void setName(final Map<String, String> name) {
		this.name = name;
	}


	//Relationships 
	private Collection<Category>	childs;
	private Category				parents;


	@Valid
	@OneToMany(mappedBy = "parents")
	public Collection<Category> getChilds() {
		return this.childs;
	}
	@Valid
	@ManyToOne(optional = true)
	public Category getParents() {
		return this.parents;
	}

	public void setChilds(final Collection<Category> childs) {
		this.childs = childs;
	}

	public void setParents(final Category parents) {
		this.parents = parents;
	}

}
