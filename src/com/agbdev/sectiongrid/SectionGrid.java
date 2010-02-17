package com.agbdev.sectiongrid;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.agbdev.sectiongrid.GridSection.ID;


public interface SectionGrid {
	public Object getImplementation();
	public void addSection(final GridSection section);
	public void updateSection(ID id, final List<Row> data);
	public void setBorderTitle(String title);
	public void writeToCsv(File csvFile) throws IOException;
}
