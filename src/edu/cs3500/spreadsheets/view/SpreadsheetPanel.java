package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;

public class SpreadsheetPanel extends javax.swing.JPanel {

  private BasicSpreadsheet model;

  public SpreadsheetPanel(BasicSpreadsheet model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;

    g2d.setColor(Color.PINK);
    g2d.fillRect(0, 0, this.getWidth(), 20);
    g2d.fillRect(0, 0, 50, this.getHeight());
    g2d.setColor(Color.BLACK);

    for (int i = 0; i < model.getNumRows(); i++) {
      g2d.drawLine(i*50, 0, i*50, this.getHeight());
      if (i > 1) {
        g2d.drawString(getRowName(i), (i*50)-30, 15);
      }

      for (int j = 0; j < model.getNumCols(); j++) {
        g2d.drawLine(0, j*20, this.getWidth(), j*20);
        if (j > 1) {
          g2d.drawString(getColName(j), 15, (j*20)-5);
        }
        Coord newCoord = new Coord(j+1, i+1);
        g2d.drawString(model.getCellAt(newCoord).toString(), (i*50) + 60, (j*80) - 5);
      }

    }

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
