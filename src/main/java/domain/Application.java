
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity {

	private Date		moment;
	private double		price;
	private int			status;
	private String		customerComments;
	private String		hwComments;
	private HandyWorker	handyWorker;
	private FixUpTask	fixUpTask;
	private CreditCard	creditCard;


	@Valid
	@ManyToOne
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public double getPrice() {
		return this.price;
	}

	@Range(min = -1, max = 1)
	public int getStatus() {
		return this.status;
	}
	public String getCustomerComments() {
		return this.customerComments;
	}
	public String getHwComments() {
		return this.hwComments;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public void setStatus(final int status) {
		this.status = status;
	}

	public void setCustomerComments(final String customerComments) {
		this.customerComments = customerComments;
	}

	public void setHwComments(final String hwComments) {
		this.hwComments = hwComments;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public HandyWorker getHandyWorker() {
		return this.handyWorker;
	}

	public void setHandyWorker(final HandyWorker handyWorker) {
		this.handyWorker = handyWorker;
	}

	@ManyToOne(optional = false)
	public FixUpTask getFixUpTask() {
		return this.fixUpTask;
	}

	public void setFixUpTask(final FixUpTask fixUpTask) {
		this.fixUpTask = fixUpTask;
	}

}
