
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class EndorserRecord extends DomainEntity {

	private String	fullName;
	private String	email;
	private String	phone;
	private String	linkedInProfile;
	private String	comments;


	@NotBlank
	public String getFullName() {
		return this.fullName;
	}
	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}
	@Email
	public String getEmail() {
		return this.email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}
	@NotBlank
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(final String phone) {
		this.phone = phone;
	}
	@URL
	public String getLinkedInProfile() {
		return this.linkedInProfile;
	}
	public void setLinkedInProfile(final String linkedInProfile) {
		this.linkedInProfile = linkedInProfile;
	}
	public String getComments() {
		return this.comments;
	}
	public void setComments(final String comments) {
		this.comments = comments;
	}
}
