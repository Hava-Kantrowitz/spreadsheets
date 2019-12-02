package edu.cs3500.spreadsheets.provider.view;

import java.awt.*;

import javax.swing.*;

public class JPanelScroll extends JPanel {
  JPanelGrid grid;
  JScrollBar vertical;
  JScrollBar horizontal;


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
