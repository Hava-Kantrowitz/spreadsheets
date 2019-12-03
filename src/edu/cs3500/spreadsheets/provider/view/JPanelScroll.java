package edu.cs3500.spreadsheets.provider.view;

import java.awt.Adjustable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 * NOTE: This Javadoc was not written by the providers, placed by us for the style.
 */
public class JPanelScroll extends JPanel {
  JPanelGrid grid;
  JScrollBar vertical;
  JScrollBar horizontal;


  /**
   * NOTE: This Javadoc was not written by the providers, placed by us for the style.
   */
  public JPanelScroll(JPanelGrid grid) {
    this.vertical = new JScrollBar(Adjustable.VERTICAL);
    this.horizontal = new JScrollBar(Adjustable.HORIZONTAL);
    this.grid = grid;

    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 1;
    this.add(this.vertical, c);
    c.gridx = 1;
    c.gridy = 0;
    this.add(this.horizontal, c);
    c.gridx = 1;
    c.gridy = 1;
    this.add(this.grid, c);
  }
}
