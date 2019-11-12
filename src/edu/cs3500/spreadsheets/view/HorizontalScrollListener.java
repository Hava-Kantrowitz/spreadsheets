package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * This is the class for a scroll specifically for a spreadsheet.
 */
public class HorizontalScrollListener implements AdjustmentListener {

  private DefaultTableModel table;
  private int scrollMaxCount;
  private JTable sheet;

  /**
   * This constructs a new spreadsheet scroll listener.
   */
  HorizontalScrollListener(JTable sheet, DefaultTableModel table) {
    super();
    this.sheet = sheet;
    this.table = table;
    this.scrollMaxCount = 0;
  }


  @Override
  public void adjustmentValueChanged(AdjustmentEvent e) {
    // checks if the current value of the horizontal scroll bar is at max (subtraction accounts
    // for the difference in max and how far scroll goes
    if (e.getValue() >= scrollMaxCount) {

      // getting the name of the new column
      String colName = Coord.colIndexToName(table.getColumnCount());

      table.addColumn(colName);  // adds the column to default table

      scrollMaxCount = e.getValue(); // set new max
    }

    DefaultTableCellRenderer rowRenderer = new DefaultTableCellRenderer();
    sheet.getColumnModel().getColumn(0).setCellRenderer(rowRenderer);

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);

    for (int i = 0; i < sheet.getColumnCount(); i++) {
      sheet.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    rowRenderer.setBackground(Color.LIGHT_GRAY);
    rowRenderer.setHorizontalAlignment(JLabel.CENTER);
    sheet.getColumnModel().getColumn(0).setCellRenderer(rowRenderer);
  }
}
