
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
public class EducationalRecord extends DomainEntity {

	private String				diplomasTitle;
	private Date				studiesBeggining;
	private Date				studiesEnding;
	private String				institution;
	private String				attachment;
	private Collection<String>	comments;


	@NotBlank
	public String getDiplomasTitle() {
		return this.diplomasTitle;
	}
	public void setDiplomasTitle(final String diplomasTitle) {
		this.diplomasTitle = diplomasTitle;
	}
	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getStudiesBeggining() {
		return this.studiesBeggining;
	}
	public void setStudiesBeggining(final Date studiesBeggining) {
		this.studiesBeggining = studiesBeggining;
	}
	@Temporal(TemporalType.DATE)
	public Date getStudiesEnding() {
		return this.studiesEnding;
	}
	public void setStudiesEnding(final Date studiesEnding) {
		this.studiesEnding = studiesEnding;
	}
	@NotNull
	public String getInstitution() {
		return this.institution;
	}
	public void setInstitution(final String institution) {
		this.institution = institution;
	}
	@URL
	public String getAttachment() {
		return this.attachment;
	}
	public void setAttachment(final String attachment) {
		this.attachment = attachment;
	}
	@ElementCollection
	public Collection<String> getComments() {
		return this.comments;
	}
	public void setComments(final Collection<String> comment) {
		this.comments = comment;
	}
}
