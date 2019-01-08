
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	//Attributes 
	private String	nameEN;
	private String	nameES;


	//Getters and setters

	@NotBlank
	public String getNameEN() {
		return this.nameEN;
	}

	public void setNameEN(final String nameEN) {
		this.nameEN = nameEN;
	}
	@NotBlank
	public String getNameES() {
		return this.nameES;
	}

	public void setNameES(final String nameES) {
		this.nameES = nameES;
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
