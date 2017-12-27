package de.sambalmueslie.samanunga.mgr;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import de.sambalmueslie.samanunga.mgr.api.BusinessObject;
import de.sambalmueslie.samanunga.mgr.api.BusinessObjectListener;

public class BaseBusinessObject<T> extends Observable<BusinessObjectListener> implements BusinessObject {

	protected BaseBusinessObject(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	/** the id. */
	private final String id;

}
