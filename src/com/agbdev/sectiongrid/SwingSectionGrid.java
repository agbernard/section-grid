package com.agbdev.sectiongrid;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;

public class SwingSectionGrid
implements SectionGrid {
	private JPanel pnlMain;
	private int numSections = 0;

	public SwingSectionGrid() {
    	this.pnlMain = new JPanel(new SpringLayout());
	}

	public void addSection(final GridSection section) {
    	GridDataModel model = section.getDataModel();
    	JTable table = new JTable(new GridModel(model));
    	JScrollPane scrollPane = new JScrollPane(table);
    	table.setFillsViewportHeight(true);
    	
    	JPanel panel = new JPanel(new BorderLayout());
    	panel.add(new JLabel(section.getName()), BorderLayout.PAGE_START);
    	panel.add(scrollPane, BorderLayout.CENTER);
    	this.pnlMain.add(panel);
    	this.numSections++;
	}
	
	private final class GridModel extends AbstractTableModel {
		private static final long serialVersionUID = 80394553666744922L;
		private GridDataModel model;
		
		private GridModel(GridDataModel model) {
			this.model = model;
		}

		public String getColumnName(int col) {
	        return model.getColumnName(col);
	    }

		
		@Override
		public int getColumnCount() {
			return model.getNumColumns();
		}

		@Override
		public int getRowCount() {
			return model.getNumRows();
		}

		@Override
		public Object getValueAt(int rowIndex, int colIndex) {
			return model.getValueAt(rowIndex, colIndex);
		}
		
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
