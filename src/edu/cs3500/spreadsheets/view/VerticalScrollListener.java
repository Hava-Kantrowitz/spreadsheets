package edu.cs3500.spreadsheets.view;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 * Models a vertical scroll bar.
 */
public class VerticalScrollListener implements AdjustmentListener {


  private JScrollPane scrollPane;
  private DefaultTableModel table;
  private int scrollMaxCount;

  /**
   * This constructs a new spreadsheet scroll listener.
   */
  VerticalScrollListener(JScrollPane scrollPane, DefaultTableModel table) {
    super();
    this.scrollPane = scrollPane;
    this.table = table;
    this.scrollMaxCount = 0;
  }


  @Override
  public void adjustmentValueChanged(AdjustmentEvent e) {
    // checks if the current value of the vertical scroll bar is at greater than max it has been
    if (e.getValue() >= scrollMaxCount) {
      Integer[] addedRow = new Integer[table.getColumnCount()];
      table.addRow(addedRow);
      for (int i = 1; i < table.getRowCount() + 1; i++) {
        table.setValueAt(i, i - 1, 0);
      }

      scrollMaxCount = e.getValue(); // set new max it has scrolled to current value
    }
  }

}
