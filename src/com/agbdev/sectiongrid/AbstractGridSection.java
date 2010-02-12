package com.agbdev.sectiongrid;


public abstract class AbstractGridSection implements GridSection {
	private Header header;

	protected AbstractGridSection(final String sectionName) {
		this(sectionName, null);
	}

	protected AbstractGridSection(final String sectionName, final String desc) {
		this.header = getHeader(sectionName, desc);
	}

	public Header getHeader() {
	    return this.header;
	}

	private Header getHeader(final String sectionName, final String desc) {
		return new Header() {
			private boolean isBold;

			public void setIsBold(final boolean isBold) {
				this.isBold = isBold;
			}

			public boolean isBold() {
				return this.isBold;
			}

			public String getName() {
				return sectionName;
			}

			public String getDesc() {
	            return desc;
            }
		};
	}
}
