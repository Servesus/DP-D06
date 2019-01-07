
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Curricula extends DomainEntity {

	private String							ticker;
	private PersonalRecord					personalRecord;
	private Collection<EducationalRecord>	educationalRecord;
	private Collection<ProfessionalRecord>	professionalRecord;
	private Collection<EndorserRecord>		endorserRecord;
	private Collection<MiscRecord>			miscRecord;


	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^(\\d{6}(-)\\w{6})$")
	public String getTicker() {
		return this.ticker;
	}
	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	public PersonalRecord getPersonalRecord() {
		return this.personalRecord;
	}
	public void setPersonalRecord(final PersonalRecord personalRecord) {
		this.personalRecord = personalRecord;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<EducationalRecord> getEducationalRecord() {
		return this.educationalRecord;
	}
	public void setEducationalRecord(final Collection<EducationalRecord> educationalRecord) {
		this.educationalRecord = educationalRecord;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<ProfessionalRecord> getProfessionalRecord() {
		return this.professionalRecord;
	}
	public void setProfessionalRecord(final Collection<ProfessionalRecord> professionalRecord) {
		this.professionalRecord = professionalRecord;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<EndorserRecord> getEndorserRecord() {
		return this.endorserRecord;
	}
	public void setEndorserRecord(final Collection<EndorserRecord> endorserRecord) {
		this.endorserRecord = endorserRecord;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<MiscRecord> getMiscRecord() {
		return this.miscRecord;
	}
	public void setMiscRecord(final Collection<MiscRecord> miscRecord) {
		this.miscRecord = miscRecord;
	}

}
