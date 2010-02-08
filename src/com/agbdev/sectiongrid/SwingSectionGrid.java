package com.agbdev.sectiongrid;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SwingSectionGrid
implements SectionGrid {
	private JScrollPane scrollPane;

	public SwingSectionGrid() {
    	JTable table = new JTable();
    	this.scrollPane = new JScrollPane(table);
    	table.setFillsViewportHeight(true);
	}

	@Override
    public Object getImplementation() {
	    return this.scrollPane;
    }

}
