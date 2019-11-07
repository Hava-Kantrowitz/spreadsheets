package edu.cs3500.spreadsheets.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;

public class SpreadsheetTable extends JTable {

  private BasicSpreadsheet spreadsheet;

  public SpreadsheetTable(BasicSpreadsheet model) {
    this.spreadsheet = model;
  }

  public JTable getTable() {

    String[] colHeadings = getHeaders();
    int numRows = getMaxRows()+1;
    DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length) ;
    model.setColumnIdentifiers(colHeadings);
    for (Coord c : spreadsheet.getListCoords()) {
      model.setValueAt(spreadsheet.getCellAt(c).toString(), c.row, c.col);
    }

    for (int i = 1; i < getMaxRows()+1; i++) {
      model.setValueAt(i, i-1, 0);
    }

    return new JTable(model);
  }

  private int getMaxRows() {
    int highestRow = 0;

    for (Coord coord : spreadsheet.getListCoords()) {
      int checkRow = coord.row;
      if (checkRow > highestRow) {
        highestRow = checkRow;
      }
    }

    return highestRow;
  }

  private String[] getHeaders() {
    int highestCol = 0;

    for (Coord coord : spreadsheet.getListCoords()) {
      int checkCol = coord.col;
      if (checkCol > highestCol) {
        highestCol = checkCol;
      }
    }
    String[] headerList = new String[highestCol+1];
    for (int i = 0; i < highestCol+1; i++) {
      headerList[i] = Coord.colIndexToName(i);
    }
    return headerList;
  }

  private String getRowName(int num) {
    return Coord.colIndexToName(num-1);
  }

  private String getColName(int num) {
    int parseNum = num-1;
    return Integer.toString(parseNum);
  }


}
