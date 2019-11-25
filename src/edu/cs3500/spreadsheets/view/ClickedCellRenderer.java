package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public class ClickedCellRenderer extends DefaultTableCellRenderer {
  private int row;
  private int col;

  Border border;

  public ClickedCellRenderer(int row, int col) {
    this.row = row;
    this.col = col;
    border = BorderFactory.createLineBorder(Color.RED, 10, true);
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    JComponent withBorder = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    withBorder.setBorder(border);
    return withBorder;
  }


}
