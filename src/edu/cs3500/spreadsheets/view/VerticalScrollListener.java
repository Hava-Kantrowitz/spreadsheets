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
  private int scrollDifference = 755;

  /**
   * This constructs a new spreadsheet scroll listener.
   */
  VerticalScrollListener(JScrollPane scrollPane, DefaultTableModel table) {
    super();
    this.scrollPane = scrollPane;
    this.table = table;
  }


  @Override
  public void adjustmentValueChanged(AdjustmentEvent e) {
    // checks if the current value of the vertical scroll bar is at max (subtraction accounts
    // for the difference in max and how far scroll goes

    if (e.getValue() >= scrollPane.getVerticalScrollBar().getMaximum() - scrollDifference) {
      Integer[] addedRow = new Integer[table.getColumnCount()];
      table.addRow(addedRow);
      for (int i = 1; i < table.getRowCount() + 1; i++) {
        table.setValueAt(i, i - 1, 0);
      }
    }
  }

}
