package com.agbdev.sectiongrid;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import com.agbdev.sectiongrid.GridSection.ID;

public class SwingSectionGrid
implements SectionGrid {
	private JPanel pnlMain;
	private Map<ID, GridSection> sections;

	public SwingSectionGrid() {
    	this.pnlMain = new JPanel(new SpringLayout());
    	this.sections = new HashMap<ID, GridSection>();
	}

	public void addSection(final GridSection section) {
		Component gridImpl = (Component) section.getImplementation();

    	JPanel panel = new JPanel(new BorderLayout());
    	Component sectionHeader = getSectionHeading(section.getHeader());
    	panel.add(sectionHeader, BorderLayout.PAGE_START);
    	panel.add(gridImpl, BorderLayout.CENTER);
    	this.pnlMain.add(panel);
    	this.sections.put(section.getId(), section);
	}

	private Component getSectionHeading(final GridSection.Header sectionHeader) {
		String text = sectionHeader.getName();
		if(sectionHeader.isBold()) {
			text = "<b>" + text + "</b>";
		}

		String desc = sectionHeader.getDesc();
		if (desc != null) {
			text += " (" + sectionHeader.getDesc() + ")";
		}
		return new JLabel("<html>" + text + "</html>");
	}

	public void setBorderTitle(final String title) {
		Border outsideBorder = BorderFactory.createTitledBorder(title);
		this.pnlMain.setBorder(outsideBorder);
	}

	public Object getImplementation() {
		SpringUtilities.makeCompactGrid(this.pnlMain,
		                                this.sections.size(), 1, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
	    return this.pnlMain;
    }

	public void updateSection(final ID id, final List<Row> data) {
		GridSection section = this.sections.get(id);
	    GridDataModel model = section.getDataModel();
	    model.update(data);
	    section.update();
    }

	public void writeToCsv(final String filePath) throws IOException {
		File csvFile = new File(filePath);
		Writer writer = null;
		try {
			writer = new FileWriter(csvFile);
			boolean wroteHeader = false;
			for (ID id : this.sections.keySet()) {
				GridSection section = this.sections.get(id);
				GridDataModel model = section.getDataModel();
				GridDataWriter gridWriter = model.getWriter(writer);

				if(!wroteHeader) {
					gridWriter.writeHeaderToStream();
					wroteHeader = true;
				}

				gridWriter.writeDataToStream();
			}
		}
		finally {
			if(writer != null) {
				writer.close();
			}
		}
	}

}
