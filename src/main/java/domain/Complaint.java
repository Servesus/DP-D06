
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Complaint extends DomainEntity {

	private String				ticker;
	private Date				moment;
	private String				description;
	private Collection<String>	attachment;

	//Relationships:
	private Customer			customer;
	private Collection<Report>	reports;
	private FixUpTask			fixUpTasks;


	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^(\\d{6}(-)\\w{6})$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	@ElementCollection
	public Collection<String> getAttachment() {
		return this.attachment;
	}

	public void setAttachment(final Collection<String> attachment) {
		this.attachment = attachment;
	}

	//Relationships:

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@OneToMany(mappedBy = "complaint")
	public Collection<Report> getReports() {
		return this.reports;
	}

	public void setReports(final Collection<Report> reports) {
		this.reports = reports;
	}

	@ManyToOne(optional = false)
	public FixUpTask getFixUpTasks() {
		return this.fixUpTasks;
	}

	public void setFixUpTasks(final FixUpTask fixUpTasks) {
		this.fixUpTasks = fixUpTasks;
	}

}
