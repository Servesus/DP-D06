
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class MiscRecord extends DomainEntity {

	private String				title;
	private String				attachment;
	private Collection<String>	comments;


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
	@ElementCollection
	public Collection<String> getComments() {
		return this.comments;
	}
	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}

}
