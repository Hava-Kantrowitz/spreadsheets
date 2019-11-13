package edu.cs3500.spreadsheets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.SpreadsheetReadOnlyAdapter;
import edu.cs3500.spreadsheets.view.SpreadsheetGraphicsView;
import edu.cs3500.spreadsheets.view.SpreadsheetTextualView;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

/**
 * The main class for our program.
 */
public class BeyondGood {

  private static BasicSpreadsheet sheet = new BasicSpreadsheet();

  /**
   * The main entry point.
   *
   * @param args any command-line arguments
   */
  public static void main(String[] args) throws FileNotFoundException {
    // checking if it is to run the empty spreadsheet
    if (args.length == 1 && args[0].equals("-gui")) {
      // run the gui without a file
      // add in a file for the given output
      File file = new File("spreadsheet.txt");
      try {
        file.createNewFile();   // creating an empty file to pass in

      } catch (IOException e) {
        System.out.println("Cannot create an empty file");
      }

      // rendering the gui with the empty file
      renderGui(new FileReader(file.getAbsolutePath()));

    }
    // if there is an input file
    else if (args.length >= 3 && args[0].equals("-in")) {

      // get the file input
      Reader inputFile = null;
      try {
        inputFile = new FileReader(args[1]);
      } catch (FileNotFoundException e) {
        System.out.println("File not found");
        System.exit(1);
      }


      // check if it is the gui view
      if (args.length == 3 && args[2].equals("-gui")) {
        // render the view of the file with the gui
        renderGui(inputFile);
      }
      // check if it is length 4
      else if (args.length == 4 && args[2].equals("-save")) {
        // checking if the save one

        // open the input file
        renderGui(inputFile);
        // save to the output file

        PrintWriter outputFile = null;
        try {
          outputFile = new PrintWriter(args[3]);
        } catch (FileNotFoundException e) {
          System.out.println("File not found");
          System.exit(1);
        }
        SpreadsheetTextualView view = new SpreadsheetTextualView(sheet, outputFile);
        view.render();

      }
      // checking if it is the textual evaluation
      else if (args.length == 4 && args[2].equals("-eval")) {
        // evaluate the given cell
        evaluateCellTextView(inputFile, args[3]);
      } else {
        throw new IllegalArgumentException("Malformed command line");

      }

    } else {
      throw new IllegalArgumentException("Malformed command line");
    }

  }


  /**
   * Parses the coordinate values of the cell.
   *
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


  /**
   * This is the method to create the gui from the given file.
   *
   * @param fileName the name of the file
   */
  private static void renderGui(Reader fileName) {
    sheet.initializeSpreadsheet(fileName);
    SpreadsheetView view = new SpreadsheetGraphicsView(sheet);
    view.render();
  }


  /**
   * Runs the evaluation of a given cell.
   *
   * @param fileName  the name of the file to run
   * @param givenCell the given cell to be evaluated
   */
  private static void evaluateCellTextView(Reader fileName, String givenCell) {
    sheet.initializeSpreadsheet(fileName);

    try {
      sheet.evaluateSheet();
    } catch (IllegalArgumentException e) {
      for (String i : sheet.badReferences) {
        System.out.println("Error in cell " + i + " : cell contains self reference.");
      }
      System.exit(1);
    }

    Coord inputCoord = parseCellVal(givenCell);
    String result = sheet.getCellAt(inputCoord).toString();
    try {
      Double resultNum = Double.parseDouble(result);
      System.out.print(String.format("%f", resultNum));
    } catch (NumberFormatException e) {
      System.out.print("\"" + result +"\"");  // adding the quotes if it is a string
    }
  }

}
