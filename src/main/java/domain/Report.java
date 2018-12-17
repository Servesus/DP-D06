
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	//Attributes
	private Date	moment;
	private String	description;
	private String	attachment;
	private Boolean	isFinal;


	//Getters and setters
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	@URL
	public String getAttachment() {
		return this.attachment;
	}

	public Boolean getIsFinal() {
		return this.isFinal;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setAttachment(final String attachment) {
		this.attachment = attachment;
	}

	public void setIsFinal(final Boolean isFinal) {
		this.isFinal = isFinal;
	}


	//Relationships
	private Collection<Note>	notes;
	private Complaint			complaint;
	private Referee				referee;


	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Note> getNotes() {
		return this.notes;
	}

	@ManyToOne(optional = false)
	public Complaint getComplaint() {
		return this.complaint;
	}

	@NotNull
	@ManyToOne
	public Referee getReferee() {
		return this.referee;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}

	public void setComplaint(final Complaint complaint) {
		this.complaint = complaint;
	}

	public void setReferee(final Referee referee) {
		this.referee = referee;
	}

}
