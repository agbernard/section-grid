package com.agbdev.sectiongrid;

import java.util.List;
import com.agbdev.sectiongrid.GridSection.ID;


public interface SectionGrid {
	public Object getImplementation();
	public void addSection(final GridSection section);
	public void updateSection(ID id, final List<Row> data);
}
