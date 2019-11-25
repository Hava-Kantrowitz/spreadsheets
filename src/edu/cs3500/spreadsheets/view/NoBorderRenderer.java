package edu.cs3500.spreadsheets.view;

import java.awt.Color;

import javax.swing.BorderFactory;



public class NoBorderRenderer extends ClickedCellRenderer{

  public NoBorderRenderer(int row, int col) {
    super(row, col);
    // making the border empty
    super.border = BorderFactory.createLineBorder(Color.BLACK, 0, true);
  }

}
