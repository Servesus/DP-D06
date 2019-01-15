
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class MiscRecord extends DomainEntity {

	private String	title;
	private String	attachment;
	private String	comments;


	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	@URL
	public String getAttachment() {
		return this.attachment;
	}
	public void setAttachment(final String attachment) {
		this.attachment = attachment;
	}

	public String getComments() {
		return this.comments;
	}
	public void setComments(final String comments) {
		this.comments = comments;
	}

}
