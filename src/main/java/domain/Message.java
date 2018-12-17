
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	//Attributes
	private Date				sendDate;
	private Collection<Actor>	recipient;
	private Actor				sender;
	private String				subject;
	private String				body;
	private int					priority;


	//Getters and Setters
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(final Date sendDate) {
		this.sendDate = sendDate;
	}
	@NotNull
	@Valid
	@ManyToMany
	public Collection<Actor> getRecipient() {
		return this.recipient;
	}

	public void setRecipient(final Collection<Actor> recipient) {
		this.recipient = recipient;
	}
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getSender() {
		return this.sender;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}
	@NotBlank
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}
	@NotBlank
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@Range(min = 0, max = 3)
	public int getPriority() {
		return this.priority;
	}

	public void setPriority(final int priority) {
		this.priority = priority;
	}

}
