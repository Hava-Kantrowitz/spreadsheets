package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.Coord;

/**
 * This is the class for a horizontal scroll listener for the spreadsheet.
 */
public class HorizontalScrollListener implements AdjustmentListener {

  private DefaultTableModel table;
  private int scrollMaxCount;
  private JTable sheet;
  private JTable rows;

  /**
   * This constructs a new spreadsheet scroll listener.
   * @param sheet the JTable component of the panel
   * @param table the data represented as a table
   */
  HorizontalScrollListener(JTable sheet, DefaultTableModel table, JTable rows) {
    super();
    this.sheet = sheet;
    this.table = table;
    this.scrollMaxCount = 50;
    this.rows = rows;
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

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);

    for (int i = 0; i < sheet.getColumnCount(); i++) {
      sheet.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    rowRenderer.setBackground(Color.LIGHT_GRAY);
    rowRenderer.setHorizontalAlignment(JLabel.CENTER);
    rows.getColumnModel().getColumn(0).setCellRenderer(rowRenderer);
    rows.getColumnModel().getColumn(0).setPreferredWidth(10);
    rows.getColumnModel().getColumn(0).setWidth(10);
  }
}
