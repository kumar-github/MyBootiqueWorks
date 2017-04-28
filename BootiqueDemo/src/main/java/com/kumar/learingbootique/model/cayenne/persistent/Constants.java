package com.kumar.learingbootique.model.cayenne.persistent;

import com.kumar.learingbootique.model.cayenne.persistent.auto._Constants;

public class Constants extends _Constants
{
	private static final long serialVersionUID = 1L;

	public String getAttributeName()
	{
		return (this.getObjectId() != null) && !this.getObjectId().isTemporary() ? (String) this.getObjectId().getIdSnapshot().get(ATTRIBUTE_NAME_PK_COLUMN) : null;
	}
}