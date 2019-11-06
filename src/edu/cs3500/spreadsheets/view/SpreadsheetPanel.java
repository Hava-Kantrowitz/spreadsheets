package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;

public class SpreadsheetPanel extends javax.swing.JPanel {

  private BasicSpreadsheet model;
  private JTable table;

  public SpreadsheetPanel(BasicSpreadsheet model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;

    int highestCol = 0;
    int highestRow = 0;

    for (Coord coord : model.sheet.keySet()) {
      int checkCol = coord.col;
      int checkRow = coord.row;
      if (checkCol > highestCol) {
        highestCol = checkCol;
      }
      if (checkRow > highestRow) {
        highestRow = checkRow;
      }
    }

    String[] columnNames = {"Hava", "Vicky"};

    String[][] data = {{"hey", "what"}, {"idk", "what"}};
    table = new JTable(data, columnNames);


  }

  private String getRowName(int num) {
    return Coord.colIndexToName(num-1);
  }

  private String getColName(int num) {
    int parseNum = num-1;
    return Integer.toString(parseNum);
  }

  /**
   * Parses the coordinate values of the cell.
   * @param arg the string representation of the desired coordinate, as input by user
   * @return the x-y coordinate of the given cell
   */
  private static Coord parseCellVal(String arg) {
    Scanner scan = new Scanner(arg);
    final Pattern cellRef = Pattern.compile("([A-Za-z]+)([1-9][0-9]*)");
    scan.useDelimiter("\\s+");
    int col;
    int row;
    Coord coord1 = null;
    while (scan.hasNext()) {
      String cell = scan.next();
      Matcher m = cellRef.matcher(cell);
      if (m.matches()) {
        col = Coord.colNameToIndex(m.group(1));
        row = Integer.parseInt(m.group(2));
        coord1 = new Coord(col, row);
      } else {
        throw new IllegalStateException("Expected cell ref");
      }

    }
    return coord1;
  }
}
