
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Note extends DomainEntity {

	//Attributes
	private Date	moment;
	private String	hwComments;
	private String	customerComments;
	private String	refereeComments;


	//Getters and setters

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	public String getCustomerComments() {
		return this.customerComments;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setCustomerComments(final String customerComments) {
		this.customerComments = customerComments;
	}

	public String getHwComments() {
		return this.hwComments;
	}

	public void setHwComments(final String hwComments) {
		this.hwComments = hwComments;
	}

	public String getRefereeComments() {
		return this.refereeComments;
	}

	public void setRefereeComments(final String refereeComments) {
		this.refereeComments = refereeComments;
	}
}
