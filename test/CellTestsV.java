import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.StringValue;

import static org.junit.Assert.assertEquals;



/**
 * This is the testing class for methods related to the specific cells (Victoria's Half).
 */
public class CellTestsV {

  private static void initializeTestSheet(Spreadsheet sheet){
    try {
      sheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/NEU 1st " +
              "year/Object Oriented/CS 3500 Projects/spreadsheets/src/edu/cs3500/" +
              "spreadsheets/testingBlankTenByTen.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private BasicSpreadsheet spreadsheet = new BasicSpreadsheet();

  private Coord a1 = new Coord(1, 1);
  private Coord b1 = new Coord(2, 1);
  private Coord c1 = new Coord(3, 1);
  private Coord a2 = new Coord(1, 2);
  private Coord b2 = new Coord(2, 2);
  private Coord c2 = new Coord(3, 2);
  private Coord a3 = new Coord(1, 3);
  private Coord b3 = new Coord(2, 3);
  private Coord c3 = new Coord(3, 3);

  private Cell trueVal = new BooleanValue(true);
  private Cell falseVal = new BooleanValue(false);
  private Cell stringVal = new StringValue("hello");
  private Cell numberVal10 = new DoubleValue(10.0);
  private Cell numberVal5 = new DoubleValue(5.0);
  private Cell numberVal2 = new DoubleValue(2.0);
  private Cell numberVal4 = new DoubleValue(4.0);


  // THESE ARE THE TESTS FOR EVALUATE CELL (WHEN IT IS A VALUE)

  // this is to check that the returned value is correct (when boolean)


  // this is to check that the returned value is correct (when double)

  // this is to check that the returned value is correct (when string)

  // this is the test when the value is an integer

  // THESE ARE THE TESTS FOR EVALUATE CELL (WHEN IT IS A FORMULA)

  //PRODUCT TESTS (true and false are zero string is one)
  // this is the test for product (when has strings)
  @Test
  public void productWithStrings() {
    initializeTestSheet(spreadsheet);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new StringValue("hello"));
    ourFormulas.add(new DoubleValue(1.0));
    Cell stringCell = new Function("PRODUCT",  ourFormulas);
    assertEquals(new DoubleValue(1.0),stringCell.evaluateCell());
  }

  // this is the test for product (when it has true) - true is one here
  @Test
  public void productWithTrue() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, trueVal);
    spreadsheet.setCellAt(c3, numberVal10);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    ourFormulas.add(new Reference("C3", spreadsheet));
    spreadsheet.setCellAt(b2, new Function("PRODUCT", ourFormulas));
    assertEquals(new DoubleValue(10.0), spreadsheet.getCellAt(b2).evaluateCell());
  }

  // this is the test for product (when it has false) - false is zero here
  @Test
  public void productWithFalse() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, falseVal);
    spreadsheet.setCellAt(c3, numberVal10);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    ourFormulas.add(new Reference("C3", spreadsheet));
    spreadsheet.setCellAt(b2, new Function("PRODUCT", ourFormulas));
    assertEquals(new BooleanValue(false), spreadsheet.getCellAt(b2).evaluateCell());
  }

  // this is the test for product (when has all numbers)
  @Test
  public void productWithNumbers() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, numberVal4);
    spreadsheet.setCellAt(c3, numberVal5);
    spreadsheet.setCellAt(c2, numberVal4);
    spreadsheet.setCellAt(b1, numberVal2);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    ourFormulas.add(new Reference("C3", spreadsheet));
    ourFormulas.add(new Reference("C2", spreadsheet));
    ourFormulas.add(new Reference("B1", spreadsheet));
    spreadsheet.setCellAt(b2, new Function("PRODUCT", ourFormulas));
    assertEquals(new DoubleValue(160.0), spreadsheet.getCellAt(b2).evaluateCell());
  }


  //SUM TESTS (true and false and strings are zero)
  // this is the test for sum (when has strings as inputs)
  @Test
  public void sumWithStrings() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, stringVal);
    spreadsheet.setCellAt(c3, numberVal5);
    spreadsheet.setCellAt(c2, numberVal4);
    spreadsheet.setCellAt(b1, numberVal2);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    ourFormulas.add(new Reference("C3", spreadsheet));
    ourFormulas.add(new Reference("C2", spreadsheet));
    ourFormulas.add(new Reference("B1", spreadsheet));
    spreadsheet.setCellAt(b2, new Function("SUM", ourFormulas));
    assertEquals(new DoubleValue(11.0), spreadsheet.getCellAt(b2).evaluateCell());
  }

  // this is the test for sum (when has boolean inputs)
  @Test
  public void sumWithBoolean() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, trueVal);
    spreadsheet.setCellAt(c3, numberVal5);
    spreadsheet.setCellAt(c2, numberVal4);
    spreadsheet.setCellAt(b1, falseVal);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    ourFormulas.add(new Reference("C3", spreadsheet));
    ourFormulas.add(new Reference("C2", spreadsheet));
    ourFormulas.add(new Reference("B1", spreadsheet));
    spreadsheet.setCellAt(b2, new Function("SUM", ourFormulas));
    assertEquals(new DoubleValue(9.0), spreadsheet.getCellAt(b2).evaluateCell());
  }

  // this is the test for sum (when it has many valid inputs)
  @Test
  public void sumWithValid() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, numberVal10);
    spreadsheet.setCellAt(c3, numberVal5);
    spreadsheet.setCellAt(c2, numberVal4);
    spreadsheet.setCellAt(b1, numberVal2);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    ourFormulas.add(new Reference("C3", spreadsheet));
    ourFormulas.add(new Reference("C2", spreadsheet));
    ourFormulas.add(new Reference("B1", spreadsheet));
    spreadsheet.setCellAt(b2, new Function("SUM", ourFormulas));
    assertEquals(new DoubleValue(21.0), spreadsheet.getCellAt(b2).evaluateCell());
  }


  // SQRT TESTS

  // this is the test for the string input (exception)
  @Test(expected = IllegalArgumentException.class)
  public void sqrtWithStrings() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, stringVal);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    spreadsheet.setCellAt(b2, new Function("SQRT", ourFormulas));
    spreadsheet.getCellAt(b2).evaluateCell();
  }

  // this is the test for when the input is true (one)
  @Test
  public void sqrtWithTrue() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, trueVal);

    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    spreadsheet.setCellAt(b1, new Function("SQRT", ourFormulas));
    assertEquals(new DoubleValue(1.0), spreadsheet.getCellAt(b1).evaluateCell());
  }

  // this is the test for when the input is false (zero)
  @Test
  public void sqrtWithFalse() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, falseVal);

    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    spreadsheet.setCellAt(b1, new Function("SQRT", ourFormulas));
    assertEquals(new DoubleValue(0.0), spreadsheet.getCellAt(b1).evaluateCell());
  }

  // this is the test for when the input is valid double
  @Test
  public void sqrtWithValid() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, numberVal4);

    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    spreadsheet.setCellAt(b1, new Function("SQRT", ourFormulas));
    assertEquals(new DoubleValue(2.0), spreadsheet.getCellAt(b1).evaluateCell());
  }

  // this is the test for when the number is not perfect square

  @Test
  public void sqrtWithValidNotPerfect() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, numberVal10);

    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    spreadsheet.setCellAt(b1, new Function("SQRT", ourFormulas));
    assertEquals(new DoubleValue(Math.sqrt(10)), spreadsheet.getCellAt(b1).evaluateCell());
  }

  // this is the test for when the input does something within the SQRT
  @Test
  public void sqrtWithMoreInside() {
    initializeTestSheet(spreadsheet);
    ArrayList<Formula> firstFormulas = new ArrayList<>();
    firstFormulas.add(new DoubleValue(5.0));
    firstFormulas.add(new DoubleValue(5.0));
    spreadsheet.setCellAt(a1, new Function("SUM", firstFormulas));

    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    spreadsheet.setCellAt(b1, new Function("SQRT", ourFormulas));
    assertEquals(new DoubleValue(Math.sqrt(10)), spreadsheet.getCellAt(b1).evaluateCell());
  }


  // TESTS FOR CHECKING THE VALIDITY OF THE FORMULA
  // referring to own cell directly
  @Test(expected = IllegalArgumentException.class)
  public void productWithInvalidReference() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, numberVal4);
    spreadsheet.setCellAt(c3, numberVal5);
    spreadsheet.setCellAt(c2, numberVal4);
    spreadsheet.setCellAt(b1, numberVal2);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    ourFormulas.add(new Reference("C3", spreadsheet));
    ourFormulas.add(new Reference("C2", spreadsheet));
    ourFormulas.add(new Reference("B1", spreadsheet));
    ourFormulas.add(new Reference("B2", spreadsheet));
    spreadsheet.setCellAt(b2, new Function("PRODUCT", ourFormulas));
  }


  // referring to own cell indirectly
  @Test(expected = IllegalArgumentException.class)
  public void invalidReferenceIndirect() {
    initializeTestSheet(spreadsheet);
    ArrayList<Formula> firstFormulas = new ArrayList<>();
    firstFormulas.add(new Reference("B2", spreadsheet));
    firstFormulas.add(new DoubleValue(5.0));
    spreadsheet.setCellAt(a1, new Function("SUM", firstFormulas));
    spreadsheet.setCellAt(c3, numberVal5);
    spreadsheet.setCellAt(c2, numberVal4);
    spreadsheet.setCellAt(b1, numberVal2);

    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    ourFormulas.add(new Reference("C3", spreadsheet));
    ourFormulas.add(new Reference("C2", spreadsheet));
    ourFormulas.add(new Reference("B1", spreadsheet));
    ourFormulas.add(new Reference("B2", spreadsheet));
    spreadsheet.setCellAt(b2, new Function("PRODUCT", ourFormulas));
  }


}
