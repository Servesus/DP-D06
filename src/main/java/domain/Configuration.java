
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	//Attributes
	private int					maxResults;
	private int					maxTime;
	private String				pageName;
	private String				bannerURL;
	private String				welcomeEN;
	private String				welcomeES;
	private int					VATPercent;
	private int					phoneCCode;
	private Collection<String>	spamWords;
	private Collection<String>	cCardsMakes;


	@URL
	@NotNull
	public String getBannerURL() {
		return this.bannerURL;
	}

	public void setBannerURL(final String bannerURL) {
		this.bannerURL = bannerURL;
	}

	public String getWelcomeEN() {
		return this.welcomeEN;
	}

	public void setWelcomeEN(final String welcomeEN) {
		this.welcomeEN = welcomeEN;
	}

	public String getWelcomeES() {
		return this.welcomeES;
	}

	public void setWelcomeES(final String welcomeES) {
		this.welcomeES = welcomeES;
	}

	@NotNull
	@Range(min = 0, max = 100)
	public int getVATPercent() {
		return this.VATPercent;
	}

	public void setVATPercent(final int vATPercent) {
		this.VATPercent = vATPercent;
	}

	@NotNull
	public int getPhoneCCode() {
		return this.phoneCCode;
	}

	public void setPhoneCCode(final int phoneCCode) {
		this.phoneCCode = phoneCCode;
	}

	@ElementCollection
	public Collection<String> getSpamWords() {
		return this.spamWords;
	}

	public void setSpamWords(final Collection<String> spamWords) {
		this.spamWords = spamWords;
	}

	@ElementCollection
	public Collection<String> getcCardsMakes() {
		return this.cCardsMakes;
	}

	public void setcCardsMakes(final Collection<String> cCardsMakes) {
		this.cCardsMakes = cCardsMakes;
	}

	@Range(min = 10, max = 100)
	public int getMaxResults() {
		return this.maxResults;
	}

	public void setMaxResults(final int maxResults) {
		this.maxResults = maxResults;
	}
	@Range(min = 1, max = 24)
	public int getMaxTime() {
		return this.maxTime;
	}

	public void setMaxTime(final int maxTime) {
		this.maxTime = maxTime;
	}
	public String getPageName() {
		return this.pageName;
	}
	public void setPageName(final String pageName) {
		this.pageName = pageName;
	}

}
