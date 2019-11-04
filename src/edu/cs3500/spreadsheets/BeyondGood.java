package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.view.SpreadsheetGraphicsView;
import edu.cs3500.spreadsheets.view.SpreadsheetTextualView;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   *
   * @param args any command-line arguments
   */
  public static void main(String[] args) throws FileNotFoundException {

    // checking that the first argument is -in and the third argument is one of the valid options
    // if not lets the user know
    if (args.length != 4 || !args[0].equals("-in") ||
            !(args[2].equals("-eval") || args[2].equals("-gui") ||args[2].equals("-save"))){
      System.out.println("Command line is malformed.");
      System.exit(1);
    }
    // if all the command arguments are valid
    else {
      BasicSpreadsheet sheet = new BasicSpreadsheet();


      // if it is the evaluation for a specific cell
      if (args[2].equals("-eval")) {
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
      // the code for running the gui with a given file
      else if (args[2].equals("-gui")) {
        Reader fileName = null;
        try {
          fileName = new FileReader(args[1]);
        } catch (FileNotFoundException e) {
          System.out.println("File not found");
          System.exit(1);
        }
          sheet.initializeSpreadsheet(fileName);
        SpreadsheetGraphicsView view = new SpreadsheetGraphicsView(sheet);
        view.render();
      }
      // this is the code for the saving of the file
      else if(args[2].equals("-save")){
        Reader fileName = null;
        try {
          fileName = new FileReader(args[1]);
        } catch (FileNotFoundException e) {
          System.out.println("File not found");
          System.exit(1);
        }

        sheet.initializeSpreadsheet(fileName);
        // NEED TO OPEN THE SPREADSHEET IN THIS AS WELL
        // ADD THAT IN ONCE GUI IS WORKING

       PrintWriter outputFile = null;
        try {
          outputFile = new PrintWriter(args[3]);
        } catch (FileNotFoundException e) {
          System.out.println("File not found");
          System.exit(1);
        } catch (IOException e) {
          e.printStackTrace();
        }

        SpreadsheetTextualView view = new SpreadsheetTextualView(sheet,outputFile);

        view.render();

      }
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
