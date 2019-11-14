package edu.cs3500.spreadsheets.view;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;

/**
 * Models a vertical scroll bar listener for the spreadsheet graphical view.
 */
public class VerticalScrollListener implements AdjustmentListener {


  private DefaultTableModel table;
  private int scrollMaxCount;
  private DefaultTableModel rows;
  private JScrollBar rowScroll;

  /**
   * This constructs a new spreadsheet scroll listener.
   *
   * @param table     the data of the spreadsheet in the form of a table
   * @param rows      the row headers of the table
   * @param rowScroll the vertical scroll bar for the rows
   */
  VerticalScrollListener(DefaultTableModel table, DefaultTableModel rows, JScrollBar rowScroll) {
    super();
    this.table = table;
    this.scrollMaxCount = 0;
    this.rows = rows;
    this.rowScroll = rowScroll;
  }


  @Override
  public void adjustmentValueChanged(AdjustmentEvent e) {
    // checks if the current value of the vertical scroll bar is at greater than max it has been
    if (e.getValue() >= scrollMaxCount) {
      Integer[] addedRow = new Integer[table.getColumnCount()];
      Integer[] addedHeader = new Integer[table.getColumnCount()];
      table.addRow(addedRow);
      rows.addRow(addedHeader);
      for (int i = 1; i < rows.getRowCount() + 1; i++) {
        rows.setValueAt(i, i - 1, 0);
      }

      scrollMaxCount = e.getValue(); // set new max it has scrolled to current value
    }
    // scrolling the row headers (setting to the value of the table scroll)
    rowScroll.setValue(e.getValue());
  }

}
