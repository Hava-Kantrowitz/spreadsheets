package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;

public class JHighlightBox extends JPanel {

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setBackground(Color.CYAN);
    g2d.drawRect(2, 2, 10, 20);
  }

}
