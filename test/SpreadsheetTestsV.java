import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.StringValue;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for the spreadsheet as a whole (Victoria's half).
 */
public class SpreadsheetTestsV {

  private BasicSpreadsheet basicSpreadsheet = new BasicSpreadsheet();


  // THESE ARE THE TESTS FOR GET CELL AT
  private static void initializeTestSheet(Spreadsheet sheet){
    try {
      sheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/NEU 1st " +
              "year/Object Oriented/CS 3500 Projects/spreadsheets/src/edu/cs3500/" +
              "spreadsheets/testingBlankTenByTen.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }


  // the test for when the given cell has a formula
  @Test
  public void getCellFormula() {


    initializeTestSheet(basicSpreadsheet);

    Coord index = new Coord(1, 3);
    ArrayList<Formula> firstFormulas = new ArrayList<>();
    firstFormulas.add(new Reference("C1", basicSpreadsheet));
    firstFormulas.add(new Reference("A1", basicSpreadsheet));
    ArrayList<Formula> secondFormulas = new ArrayList<>();
    secondFormulas.add(new Reference("C1", basicSpreadsheet));
    secondFormulas.add(new Reference("A1", basicSpreadsheet));
    ArrayList<Formula> thirdFormulas = new ArrayList<>();
    thirdFormulas.add(new Function("SUB", firstFormulas));
    thirdFormulas.add(new Function("SUB", secondFormulas));
    Cell addedCell = new Function("PRODUCT", thirdFormulas);
    basicSpreadsheet.setCellAt(index, addedCell);

    assertEquals(new Function("PRODUCT", thirdFormulas),
            basicSpreadsheet.getCellAt(index));
  }


  // the test for when the given cell has a numeric value
  @Test
  public void getCellNumericValue() {
    initializeTestSheet(basicSpreadsheet);
    Coord index = new Coord(1, 3);
    Cell addedCell = new DoubleValue(7.0);
    basicSpreadsheet.setCellAt(index, addedCell);

    assertEquals(addedCell,
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the given cell has a boolean value
  @Test
  public void getCellBooleanValue() {
    initializeTestSheet(basicSpreadsheet);
    Coord index = new Coord(5, 3);
    Cell addedCell = new BooleanValue(true);
    basicSpreadsheet.setCellAt(index, addedCell);

    assertEquals(addedCell,
            basicSpreadsheet.getCellAt(index));
  }


  // the test for when the given cell has a string value
  @Test
  public void getCellStringValue() {
    initializeTestSheet(basicSpreadsheet);
    Coord index = new Coord(5, 3);
    Cell addedCell = new StringValue("I am not throwing away my shot!");
    basicSpreadsheet.setCellAt(index, addedCell);

    assertEquals(addedCell,
            basicSpreadsheet.getCellAt(index));
  }


  // the test for when the given cell is blank
  @Test
  public void getCellBlankValue() {
    initializeTestSheet(basicSpreadsheet);
    Coord index = new Coord(5, 3);
    Cell addedCell = new Blank();
    basicSpreadsheet.setCellAt(index, addedCell);

    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the given cell is out of bounds on the column side
  // new blank cells added so it should return a blank cell
  @Test
  public void getOutColumnSide() {
    initializeTestSheet(basicSpreadsheet);
    Coord index = new Coord(15, 3);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the given cell is out of bounds on the row side
  @Test
  public void getOutRowSide() {
    initializeTestSheet(basicSpreadsheet);
    Coord index = new Coord(5, 10);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }


  // the test for when the given cell is out of bounds on the row and column
  @Test
  public void getOutBothRowCol() {
    initializeTestSheet(basicSpreadsheet);
    Coord index = new Coord(15, 20);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the row is over double the current row size
  @Test
  public void getOutRowOverDouble() {
    initializeTestSheet(basicSpreadsheet);
    Coord index = new Coord(3, 50);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the column is over double the current column size
  @Test
  public void getOutColumnOverDouble() {
    initializeTestSheet(basicSpreadsheet);
    Coord index = new Coord(70, 8);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the column and the row are over double the current column and row size
  @Test
  public void getOutColumnRowOverDouble() {
    initializeTestSheet(basicSpreadsheet);
    Coord index = new Coord(40, 50);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the given cell is out of bounds (and doubling would exceed max possible int)
  // on the row input (the doubling will go over but the number itself will still be valid
  @Test
  public void getOutRowOverMaxIntRow() {
    initializeTestSheet(basicSpreadsheet);
    Coord index = new Coord(3, 1000000);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the given cell is out of bounds (and doubling would exceed max possible int)
  // on the column input
  @Test
  public void getOutColumnMaxIntCol() {
    initializeTestSheet(basicSpreadsheet);
    Coord index = new Coord(1000000, 5);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }


  // the test for when the given cell is out of bounds (and doubling would exceed max possible int)
  // on the row and column inputs
//  @Test
//  public void getOutColumnMaxIntColRow() {
//    basicSpreadsheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src" +
//            "\\edu\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
//    Coord index = new Coord(1000000, 1000000);
//    assertEquals(new Blank(),
//            basicSpreadsheet.getCellAt(index));
//  }

  // the test for when the given cell is out of bounds (and doubling would exceed max possible int)
  // on the row and col inputs (the doubling will go over but the number itself will still be valid
//  @Test (expected = IllegalArgumentException.class)
//  public void getOutRowColOverMaxIntRow() {
//    basicSpreadsheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src" +
//            "\\edu\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
//    Coord index = new Coord(10000000, 1000001);
//    assertEquals(new Blank(),
//            basicSpreadsheet.getCellAt(index));
//  }

  // THESE ARE THE TESTS FOR GET CELL SECTION

  // the test for when the given cells are all empty (in a rectangle formation)
  @Test
  public void getSectionBlank() {
    initializeTestSheet(basicSpreadsheet);
    Coord index1 = new Coord(1, 1);  // section of blank cells
    Coord index2 = new Coord(3, 3);

    ArrayList expected = new ArrayList<Cell>();

    expected.add(new Blank());  // adding all blanks to the expected list
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());

    ArrayList actual = basicSpreadsheet.getCellSection(index1, index2);

    assertEquals(expected, actual);
  }

  // the test for when the given cells  have some formulas
  @Test
  public void getSectionFormulas() {
    initializeTestSheet(basicSpreadsheet);
    // setting up the board to be all formulas in given section
    ArrayList<Formula> firstFormulas = new ArrayList<>();
    firstFormulas.add(new DoubleValue(3.0));
    firstFormulas.add(new DoubleValue(4.0));
    basicSpreadsheet.setCellAt(new Coord(1, 1), new Function("SUM", firstFormulas));
    ArrayList<Formula> secondFormulas = new ArrayList<>();
    secondFormulas.add(new Reference("A1", basicSpreadsheet));
    secondFormulas.add(new DoubleValue(2.0));
    basicSpreadsheet.setCellAt(new Coord(2, 1), new Function("SUM", secondFormulas));
    ArrayList<Formula> thirdFormulas = new ArrayList<>();
    thirdFormulas.add(new Reference("A1", basicSpreadsheet));
    thirdFormulas.add(new Reference("B1", basicSpreadsheet));
    basicSpreadsheet.setCellAt(new Coord(3, 3), new Function("PRODUCT", thirdFormulas));


    Coord index1 = new Coord(1, 1);  // section of blank cells
    Coord index2 = new Coord(3, 3);

    List<Cell> expected = new ArrayList<>();

    expected.add(new Function("SUM", firstFormulas));  // adding all blanks to the expected list
    expected.add(new Function("SUM", secondFormulas));
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Function("PRODUCT", thirdFormulas));

    List<Cell> actual = basicSpreadsheet.getCellSection(index1, index2);

    boolean isEqual = true;

    assertEquals(expected.size(), actual.size());

    for (int i = 0; i < actual.size() && isEqual; i++) {
      if (!actual.contains(expected.get(i))) {   // if it does not contain
        isEqual = false;
      }
    }

    assertTrue(isEqual);
  }

  // the test for when the given cells are some values and blanks
  @Test
  public void getSectionValues() {
    initializeTestSheet(basicSpreadsheet);
    // setting up the board to be all formulas in given section
    basicSpreadsheet.setCellAt(new Coord(1, 1), new StringValue("hi"));
    basicSpreadsheet.setCellAt(new Coord(2, 1), new DoubleValue(9.0));
    basicSpreadsheet.setCellAt(new Coord(3, 3), new BooleanValue(true));


    Coord index1 = new Coord(1, 1);  // section of blank cells
    Coord index2 = new Coord(3, 3);

    List<Cell> expected = new ArrayList<>();

    expected.add(new StringValue("hi"));  // adding all blanks to the expected list
    expected.add(new DoubleValue(9.0));
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new BooleanValue(true));

    List<Cell> actual = basicSpreadsheet.getCellSection(index1, index2);

    boolean isEqual = true;

    assertEquals(expected.size(), actual.size());

    for (int i = 1; i < 3 && isEqual; i++) {
      if (!actual.contains(expected.get(i))) {   // if it does not contain
        isEqual = false;
      }
    }

    assertTrue(isEqual);
  }


  // the test for when the given cells have a mix of formulas, values, and empty
  @Test
  public void getSectionAllTypes() {
    initializeTestSheet(basicSpreadsheet);
    // setting up the board to be all formulas in given section
    basicSpreadsheet.setCellAt(new Coord(1, 1), new StringValue("hi"));
    basicSpreadsheet.setCellAt(new Coord(2, 1), new DoubleValue(9.0));
    basicSpreadsheet.setCellAt(new Coord(3, 1), new Reference("B1", basicSpreadsheet));
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("B1", basicSpreadsheet));
    ourFormulas.add(new Reference("C1", basicSpreadsheet));
    basicSpreadsheet.setCellAt(new Coord(1, 2), new Function("SUM", ourFormulas));
    basicSpreadsheet.setCellAt(new Coord(3, 3), new BooleanValue(true));


    Coord index1 = new Coord(1, 1);  // section of blank cells
    Coord index2 = new Coord(3, 3);

    List<Cell> expected = new ArrayList<>();

    expected.add(new StringValue("hi"));  // adding all blanks to the expected list
    expected.add(new DoubleValue(9.0));
    expected.add(new Reference("B1", basicSpreadsheet));
    expected.add(new Function("SUM", ourFormulas));
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new BooleanValue(true));

    List<Cell> actual = basicSpreadsheet.getCellSection(index1, index2);

    boolean isEqual = true;

    assertEquals(expected.size(), actual.size());

    for (int i = 0; i < actual.size() && isEqual; i++) {
      if (!actual.contains(expected.get(i))) {   // if it does not contain
        isEqual = false;
      }
    }

    assertTrue(isEqual);
  }

  // the test for when the first given cell is out of range (column)
  // this is also a list of a single column
  // all blank because added
  // all cells out of range
  @Test
  public void getSectionAllTypesColOut() {
    initializeTestSheet(basicSpreadsheet);
    Coord index1 = new Coord(11, 1);  // section of blank cells
    Coord index2 = new Coord(11, 4);

    List<Cell> expected = new ArrayList<>();


    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());

    List<Cell> actual = basicSpreadsheet.getCellSection(index1, index2);

    boolean isEqual = true;

    assertEquals(expected.size(), actual.size());

    // repeat as long as they are equal and less than size
    for (int i = 0; i < actual.size() && isEqual; i++) {
      if (!actual.contains(expected.get(i))) {   // if it does not contain
        isEqual = false;
      }
    }

    assertTrue(isEqual);
  }


  // the test for when the cell at the end is out of range (row and column)
  // some cells are not out of range
  // two columns and 5 rows
  @Test
  public void getSectionSomeOutOfRange() {
    initializeTestSheet(basicSpreadsheet);
    // setting up the board to be all formulas in given section
    basicSpreadsheet.setCellAt(new Coord(10, 1), new StringValue("hi"));
    basicSpreadsheet.setCellAt(new Coord(10, 2), new DoubleValue(9.0));
    basicSpreadsheet.setCellAt(new Coord(10, 3), new Reference("J2", basicSpreadsheet));
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("J3", basicSpreadsheet));
    ourFormulas.add(new Reference("J2", basicSpreadsheet));
    basicSpreadsheet.setCellAt(new Coord(10, 4), new Function("SUM", ourFormulas));
    basicSpreadsheet.setCellAt(new Coord(10, 5), new BooleanValue(true));


    Coord index1 = new Coord(10, 1);  // section of blank cells
    Coord index2 = new Coord(11, 5);

    List<Cell> expected = new ArrayList<>();

    expected.add(new StringValue("hi"));  // adding all blanks to the expected list
    expected.add(new Blank());
    expected.add(new DoubleValue(9.0));
    expected.add(new Blank());
    expected.add(new Reference("J2", basicSpreadsheet));
    expected.add(new Blank());
    expected.add(new Function("SUM", ourFormulas));
    expected.add(new Blank());
    expected.add(new BooleanValue(true));
    expected.add(new Blank());
    List<Cell> actual = basicSpreadsheet.getCellSection(index1, index2);

    boolean isEqual = true;

    assertEquals(expected.size(), actual.size());

    for (int i = 0; i < actual.size() && isEqual; i++) {
      if (!actual.contains(expected.get(i))) {   // if it does not contain
        isEqual = false;
      }
    }

    assertTrue(isEqual);
  }





}
