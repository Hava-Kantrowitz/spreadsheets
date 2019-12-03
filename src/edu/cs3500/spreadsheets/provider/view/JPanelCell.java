package edu.cs3500.spreadsheets.provider.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * NOTE: This Javadoc was not written by the providers, placed by us for the style.
 */
public class JPanelCell extends JButton {
  Coord c;
  String value;
  Color color = Color.WHITE;

  public final int CELL_WIDTH = 20;
  public final int CELL_HEIGHT = 10;

  /**
   * NOTE: This Javadoc was not written by the providers, placed by us for the style.
   */
  public JPanelCell(Coord c, String value) {
    this.value = value;
    this.c = c;

    // Clips strings if they are too long
    if (value.length() > 50) {
      this.value = value.substring(0, 50);
    }
  }

  /**
   * NOTE: This Javadoc was not written by the providers, placed by us for the style.
   */
  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  protected void paintComponent(Graphics g) {
    g.drawString(value, c.row * CELL_HEIGHT, c.col * CELL_WIDTH);
  }
}
