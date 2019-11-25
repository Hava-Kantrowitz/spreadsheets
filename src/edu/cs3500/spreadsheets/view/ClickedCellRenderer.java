package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class ClickedCellRenderer extends DefaultTableCellRenderer {
  private int row;
  private int col;


  protected Border border;

  public ClickedCellRenderer(int row, int col) {
    this.row = row;
    this.col = col;
    border = BorderFactory.createLineBorder(Color.RED, 2, true);
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
                                                 boolean isSelected, boolean hasFocus,
                                                 int row, int column) {

    JComponent cellSelected = (JComponent) super.getTableCellRendererComponent(table, value,
            isSelected, hasFocus, row, column);

    // check if it is equal to the given row and column
    if(row == this.row && col == this.col) {
      cellSelected.setBorder(border);  // add the border if the desired cell
    }
    // return without a border added if not desired cell
    return cellSelected;
  }


}
