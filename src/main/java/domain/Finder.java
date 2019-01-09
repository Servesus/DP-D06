
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	private String	singleKeyWord;
	private Integer	rangeStart;
	private Integer	rangeFinish;
	private Date	dateStartRange;
	private Date	dateFinishRange;
	private Date	lastUpdate;
	private String	categoryName;
	private String	warrantyTitle;


	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	public String getWarrantyTitle() {
		return this.warrantyTitle;
	}

	public void setWarrantyTitle(final String warrantyTitle) {
		this.warrantyTitle = warrantyTitle;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yy HH:mm")
	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(final Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getSingleKeyWord() {
		return this.singleKeyWord;
	}

	public void setSingleKeyWord(final String singleKeyWord) {
		this.singleKeyWord = singleKeyWord;
	}

	public Integer getRangeStart() {
		return this.rangeStart;
	}

	public void setRangeStart(final Integer rangeStart) {
		this.rangeStart = rangeStart;
	}

	public Integer getRangeFinish() {
		return this.rangeFinish;
	}

	public void setRangeFinish(final Integer rangeFinish) {
		this.rangeFinish = rangeFinish;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDateStartRange() {
		return this.dateStartRange;
	}

	public void setDateStartRange(final Date dateStartRange) {
		this.dateStartRange = dateStartRange;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDateFinishRange() {
		return this.dateFinishRange;
	}

	public void setDateFinishRange(final Date dateFinishRange) {
		this.dateFinishRange = dateFinishRange;
	}


	//Relationships
	private Collection<FixUpTask>	fixUpTask;


	@ManyToMany
	public Collection<FixUpTask> getFixUpTask() {
		return this.fixUpTask;
	}

	public void setFixUpTask(final Collection<FixUpTask> fixUpTask) {
		this.fixUpTask = fixUpTask;
	}

}
