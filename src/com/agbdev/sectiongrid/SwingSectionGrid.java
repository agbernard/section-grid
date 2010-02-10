package com.agbdev.sectiongrid;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

public class SwingSectionGrid
implements SectionGrid {
	private JPanel pnlMain;
	private int numSections = 0;

	public SwingSectionGrid() {
    	this.pnlMain = new JPanel(new SpringLayout());
	}

	public void addSection(final GridSection section) {
    	JTable table = new JTable();
    	JScrollPane scrollPane = new JScrollPane(table);
    	table.setFillsViewportHeight(true);

    	GridDataModel model = section.getDataSource();
    	//populate table from data source

    	JPanel panel = new JPanel(new BorderLayout());
    	panel.add(new JLabel(section.getName()), BorderLayout.PAGE_START);
    	panel.add(scrollPane, BorderLayout.CENTER);
    	this.pnlMain.add(panel);
    	this.numSections++;
	}

	@Override
    public Object getImplementation() {
		SpringUtilities.makeCompactGrid(this.pnlMain,
		                                this.numSections, 1, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
	    return this.pnlMain;
    }

}
