
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
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class ProfessionalRecord extends DomainEntity {

	private String				companyName;
	private Date				jobBeggining;
	private Date				jobEnding;
	private String				rol;
	private String				attachment;
	private Collection<String>	comment;


	@NotBlank
	public String getCompanyName() {
		return this.companyName;
	}
	public void setCompanyName(final String companyName) {
		this.companyName = companyName;
	}
	@NotNull
	@Past
	@Temporal(TemporalType.DATE)
	public Date getJobBeggining() {
		return this.jobBeggining;
	}
	public void setJobBeggining(final Date jobBeggining) {
		this.jobBeggining = jobBeggining;
	}
	@NotNull
	@Past
	@Temporal(TemporalType.DATE)
	public Date getJobEnding() {
		return this.jobEnding;
	}
	public void setJobEnding(final Date jobEnding) {
		this.jobEnding = jobEnding;
	}
	@NotBlank
	public String getRol() {
		return this.rol;
	}
	public void setRol(final String rol) {
		this.rol = rol;
	}
	@URL
	public String getAttachment() {
		return this.attachment;
	}
	public void setAttachment(final String attachment) {
		this.attachment = attachment;
	}
	@ElementCollection
	public Collection<String> getComment() {
		return this.comment;
	}
	public void setComment(final Collection<String> comment) {
		this.comment = comment;
	}
}
