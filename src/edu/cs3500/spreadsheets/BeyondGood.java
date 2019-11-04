package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   *
   * @param args any command-line arguments
   */
  public static void main(String[] args) {

    if (args.length != 4 || !args[0].equals("-in") || !args[2].equals("-eval")) {
      System.out.println("Command line is malformed.");
      System.exit(1);
    }

    BasicSpreadsheet sheet = new BasicSpreadsheet();

    Reader fileName = null;
    try {
      fileName = new FileReader(args[1]);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      System.exit(1);
    }

    sheet.initializeSpreadsheet(fileName);

    try {
      sheet.evaluateSheet();
    } catch (IllegalArgumentException e) {
      for (String i : sheet.badReferences) {
        System.out.println("Error in cell " + i + " : cell contains self reference.");
      }
      System.exit(1);
    }

    Coord inputCoord = parseCellVal(args[3]);
    String result = sheet.getCellAt(inputCoord).toString();
    try {
      Double resultNum = Double.parseDouble(result);
      System.out.print(String.format("%f", resultNum));
    } catch (NumberFormatException e) {
      System.out.print(result);
    }

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
