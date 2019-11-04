package edu.cs3500.spreadsheets.view;

import java.awt.*;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;

public class SpreadsheetPanel extends javax.swing.JPanel {
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;

    BasicSpreadsheet sheet = new BasicSpreadsheet();

    g2d.setColor(Color.CYAN);
    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

    g2d.setColor(Color.BLACK);
    g2d.drawLine(this.getWidth(), 0, 0, this.getHeight());

  }
}
