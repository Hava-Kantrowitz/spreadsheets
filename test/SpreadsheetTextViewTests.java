import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.view.SpreadsheetTextualView;
import edu.cs3500.spreadsheets.view.SpreadsheetView;

import static org.junit.Assert.assertEquals;


/**
 *  This is the class for the test cases for the textual view.
 */
public class SpreadsheetTextViewTests {


  // this is the test to check that references and functions are rendered as their raw contents
  @Test
  public void fileRawContents() throws FileNotFoundException {
    BasicSpreadsheet sheet1 = new BasicSpreadsheet();

    // setting up the first spreadsheet with the file
    FileReader inputFile1 = new FileReader("/Users/victoriabowen/Desktop" +
            "/NEU_1st_year/ObjectOriented/CS_3500_Projects/" +
            "spreadsheets/src/edu/cs3500/spreadsheets/testingText.txt");

    sheet1.initializeSpreadsheet(inputFile1);

    PrintWriter outputFile = new PrintWriter("/Users/victoriabowen/Desktop/NEU_1st_year" +
            "/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/outputFileSave1.txt");


    // render the text file from the actual spreadsheet
    SpreadsheetView view = new SpreadsheetTextualView(sheet1,outputFile);
    view.render();

    Scanner scan = new Scanner(new FileReader("/Users/victoriabowen/Desktop/NEU_1st_year/" +
            "ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/outputFileSave1.txt"));

    scan.nextLine();
    scan.nextLine();
    scan.nextLine();

    // functions raw contents
    assertEquals("B2 =(HAM 7)", scan.nextLine());

    scan.nextLine();
    scan.nextLine();
    // functions are raw contents
    assertEquals("A5 =(SUM (PRODUCT 2 3) (PRODUCT 2 3))",scan.nextLine());


    scan.nextLine();
    scan.nextLine();
    // cell reference is raw contents not evaluated
    assertEquals("A3 =AB1", scan.nextLine());


  }

  // this is the test for when the file has not changed
  @Test
  public void fileNoChange() throws FileNotFoundException {
    BasicSpreadsheet sheet1 = new BasicSpreadsheet();

    // setting up the first spreadsheet with the file
    FileReader inputFile1 = new FileReader("/Users/victoriabowen/Desktop" +
            "/NEU_1st_year/ObjectOriented/CS_3500_Projects/" +
            "spreadsheets/src/edu/cs3500/spreadsheets/testingText.txt");

    sheet1.initializeSpreadsheet(inputFile1);

    PrintWriter outputFile = new PrintWriter("/Users/victoriabowen/Desktop/NEU_1st_year" +
            "/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/outputFileSave.txt");


    // render the text file from the actual spreadsheet
    SpreadsheetView view = new SpreadsheetTextualView(sheet1,outputFile);
    view.render();


    // sets up a new speadsheet with the output of render
    FileReader inputFile2 = new FileReader("/Users/victoriabowen/Desktop/NEU_1st_year" +
            "/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/outputFileSave.txt");
    Spreadsheet sheet2 = new BasicSpreadsheet();
    sheet2.initializeSpreadsheet(inputFile2);


    // now sheet one should be equal to sheet two
    assertEquals(sheet1, sheet2);


  }


  // this tests the case in which some existing cells are changed
  // and new cells are added
  // before the textual view is rendered
  // when it goes to the same value as a previously saved spreadsheet
  @Test
  public void fileChange() throws FileNotFoundException {
    BasicSpreadsheet sheet1 = new BasicSpreadsheet();

    // setting up the first spreadsheet with the file
    FileReader inputFile1 = new FileReader("/Users/victoriabowen/Desktop" +
            "/NEU_1st_year/ObjectOriented/CS_3500_Projects/" +
            "spreadsheets/src/edu/cs3500/spreadsheets/testingText.txt");

    sheet1.initializeSpreadsheet(inputFile1);
    sheet1.setCellAt(new Coord(1,1), "false");  // changing existing cell
    sheet1.setCellAt(new Coord(25,7), "=(SUM 3 5)"); // setting a new formula
    sheet1.setCellAt(new Coord(1,4),"7"); // setting a new double
    sheet1.setCellAt(new Coord(200,300),"\"Hello\""); // setting out of range
    sheet1.setCellAt(new Coord(1,1), "");  // setting a cell to blank
    sheet1.setCellAt(new Coord(200, 300), ""); // setting the max to blank


    PrintWriter outputFile = new PrintWriter("/Users/victoriabowen/Desktop/NEU_1st_year" +
            "/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/outputFileSaveChange.txt");


    // render the text file from the actual spreadsheet
    SpreadsheetView view = new SpreadsheetTextualView(sheet1,outputFile);
    view.render();


    // sets up a new speadsheet with the output of render
    FileReader inputFile2 = new FileReader("/Users/victoriabowen/Desktop/NEU_1st_year" +
            "/ObjectOriented/CS_3500_Projects/spreadsheets/src/edu/cs3500/" +
            "spreadsheets/outputFileSaveChange.txt");
    Spreadsheet sheet2 = new BasicSpreadsheet();
    sheet2.initializeSpreadsheet(inputFile2);


    // now sheet one should be equal to sheet two
    assertEquals(sheet1, sheet2);


  }





}
