
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Note extends DomainEntity {

	//Attributes
	private Date	moment;
	private String	authorComment;
	private Collection<String>	hwComments;
	private Collection<String>	customerComments;
	private Collection<String> refereeComments;


	//Getters and setters

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	@NotBlank
	public String getAuthorComment() {
		return this.authorComment;
	}
	
	@ElementCollection
	public Collection<String> getCustomerComments() {
		return this.customerComments;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setAuthorComment(final String authorComment) {
		this.authorComment = authorComment;
	}

	public void setCustomerComments(final Collection<String> customerComments) {
		this.customerComments = customerComments;
	}
	
	@ElementCollection
	public Collection<String> getHwComments() {
		return this.hwComments;
	}

	public void setHwComments(final Collection<String> hwComments) {
		this.hwComments = hwComments;
	}
	
	@ElementCollection
	public Collection<String> getRefereeComments(){
		return this.refereeComments;
	}
	
	public void setRefereeComments(final Collection<String> refereeComments){
		this.refereeComments= refereeComments;
	}
}
