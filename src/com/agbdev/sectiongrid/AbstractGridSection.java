package com.agbdev.sectiongrid;

public abstract class AbstractGridSection implements GridSection {
	private Header header;

	protected AbstractGridSection(final String sectionName) {
		this(sectionName, null);
	}

	protected AbstractGridSection(final String sectionName, final String desc) {
		this.header = getHeader(sectionName, desc);
	}

	@Override
	public Header getHeader() {
	    return this.header;
	}

	private Header getHeader(final String sectionName, final String desc) {
		return new Header() {
			private boolean isBold;

			@Override
			public void setIsBold(final boolean isBold) {
				this.isBold = isBold;
			}

			@Override
			public boolean isBold() {
				return this.isBold;
			}

			@Override
			public String getName() {
				return sectionName;
			}

			@Override
            public String getDesc() {
	            return desc;
            }
		};
	}
}
