package edu.cs3500.spreadsheets.provider.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.provider.model.IViewWorksheet;

/**
 * JPanelGrid is a JPanel that represents the whole grid of cells.
 */
public class JPanelGrid extends JPanel {
  HashMap<Coord, JPanelCell> grid = new HashMap<Coord, JPanelCell>();

  public final int CELL_WIDTH = 20;
  public final int CELL_HEIGHT = 10;

  public JPanelGrid(IViewWorksheet model) {
    if (model != null) {
      // for each cell, add a corresponding JPanelCell to grid.
      for (Coord c : model.allCells()) {
        JPanelCell cell = new JPanelCell(c, model.getStringCell((c)));
        cell.setActionCommand(c.col + "," + c.row);
        grid.put(c,cell);
      }
    }



  }

  public HashMap<Coord, JPanelCell> getGrid() {
    return this.grid;
  }

  /**
   *
   */
  @Override
  protected void paintComponent(Graphics g) {

    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        // sets the color to cyan if this is a row/column header
        if (i == 0 || j == 0) {
          g.setColor(Color.CYAN);
        } else {
          g.setColor(Color.WHITE);
        }
        // draws an empty rectangle to represent a cell
        g.drawRect(i, j, CELL_WIDTH, CELL_HEIGHT);
      }
    }
    // sets the names of the column headers
    for (int j = 1; j < 100; j += CELL_WIDTH) {
      g.drawString("" + (char) j, j, 0);
    }
    // sets the names of the row headers
    for (int i = 1; i < 100; i += CELL_HEIGHT) {
      g.drawString("" + i, 0, i);
    }
    // paints every cell in the grid
    g.setColor(Color.black);
    if (grid != null) {
      for (JPanelCell c : grid.values()) {
        c.paintComponent(g);
      }
    }
  }

  public void addActionListener(ActionListener actionListener) {
    for(JPanelCell c : grid.values()) {
      c.addActionListener(actionListener);
    }
  }

}
