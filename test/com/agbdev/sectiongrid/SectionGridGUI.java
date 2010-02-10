package com.agbdev.sectiongrid;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class SectionGridGUI {
	private JFrame frame;
	private SectionGrid grid;

	public SectionGridGUI() {
		initFrame();
		initComponents();
	}

    private void initFrame() {
        this.frame = new JFrame("Section Grid (implemented in Swing)");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(new Dimension(1100, 850));
        this.frame.setLayout(new BorderLayout());
    }

    private void initComponents() {
    	this.grid = new SwingSectionGrid();
    	this.grid.addSection(new TestSection());
    	this.frame.add((Component) this.grid.getImplementation());
    }

    public void show() {
    	this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
	        e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SectionGridGUI().show();
            }
        });
    }
}
