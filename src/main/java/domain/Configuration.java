
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	//Attributes
	private int		maxResults	= 10;
	private int		maxTime		= 1;
	private String	pageName	= "Acme-Handy-Worker";


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
