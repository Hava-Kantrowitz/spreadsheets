package edu.cs3500.spreadsheets.view;

import java.awt.Color;

import javax.swing.BorderFactory;


/**
 * Class to create a renderer to remove border when a cell isn't clicked.
 */
public class NoBorderRenderer extends ClickedCellRenderer {

  /**
   * Constructs an instance of the removed border renderer class.
   * @param row the row of the cell
   * @param col the col of the cell
   */
  public NoBorderRenderer(int row, int col) {
    super(row, col);
    // making the border empty
    super.border = BorderFactory.createLineBorder(Color.BLACK, 0, true);
  }

}
