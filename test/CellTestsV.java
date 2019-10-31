import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.ArrayList;

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
      sheet.initializeSpreadsheet(new FileReader("C:\\Users\\havak\\IdeaProjects\\nextTry" +
              "\\src\\edu\\cs3500\\spreadsheets\\testingBlankTenByTen.txt"));
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
  @Test
  public void evalBool(){
    BooleanValue b = new BooleanValue(false);
    assertEquals(b, b.evaluateCell());

    BooleanValue b2 = new BooleanValue(true);
    assertEquals(b2, b2.evaluateCell());
  }

  // this is to check that the returned value is correct (when double)
  @Test
  public void evalDouble(){
     DoubleValue d = new DoubleValue(10.0);
     assertEquals(d, d.evaluateCell());
  }

  // this is to check that the returned value is correct (when string)
  @Test
  public void evalString(){
    StringValue s = new StringValue("hello");
    assertEquals(s, s.evaluateCell());
  }



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
    spreadsheet.setCellAt(b2, "=(PRODUCT A1 C3)");
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
    assertEquals(new DoubleValue(10.0), spreadsheet.getCellAt(b2).evaluateCell());
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
    spreadsheet.setCellAt(b2, "=(SUM A1 C3 C2 B1)");
    assertEquals(new DoubleValue(11.0), spreadsheet.getCellAt(b2).evaluateCell());
  }

  // this is the test for sum (when has boolean inputs)
  @Test
  public void sumWithBoolean() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, "true");
    spreadsheet.setCellAt(c3, "5");
    spreadsheet.setCellAt(c2, "=4");
    spreadsheet.setCellAt(b1, "false");
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
    spreadsheet.setCellAt(a1, "10");
    spreadsheet.setCellAt(c3, "5");
    spreadsheet.setCellAt(c2, "4");
    spreadsheet.setCellAt(b1, numberVal2);
    spreadsheet.setCellAt(b2, "(SUM A1 C3 C2 B1)");
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
    spreadsheet.setCellAt(b2, "=(SQRT A1)");
    spreadsheet.getCellAt(b2).evaluateCell();
  }
  // this is the check for an illegal number of arguments for sqrt
  @Test(expected = IllegalArgumentException.class)
  public void sqrtWithTooMany() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, stringVal);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    spreadsheet.setCellAt(b2, "=(SQRT A1 7)");
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



  // THESE ARE THE TESTS FOR CELL REFERENCE WHEN THE REFERENCE HAS A COLON

  // the sum case
  @Test
  public void sectionCellsSum(){
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, "10");
    spreadsheet.setCellAt(a2,"11");
    spreadsheet.setCellAt(b1,"10");
    // then the last corner is blank

    spreadsheet.setCellAt(c3,"=(SUM A1:B2)");

    assertEquals(new DoubleValue(31.0),spreadsheet.getCellAt(c3).evaluateCell());
  }
  // the product case
  @Test
  public void sectionCellsProduct(){
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, "5");
    spreadsheet.setCellAt(a2,"2");
    spreadsheet.setCellAt(b1,"1");
    // then the last corner is blank

    spreadsheet.setCellAt(c3,"=(PRODUCT A1:B2)");

    assertEquals(new DoubleValue(10.0),spreadsheet.getCellAt(c3).evaluateCell());
  }
  // the difference case
  @Test(expected = IllegalArgumentException.class)
  public void sectionCellsDifference(){
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, "10");
    spreadsheet.setCellAt(a2,"11");
    // middle is blank
    spreadsheet.setCellAt(b2,"10");


    spreadsheet.setCellAt(c3,"=(SUB A1:B2)");

    assertEquals(new DoubleValue(31.0),spreadsheet.getCellAt(c3).evaluateCell());

  }
  // the square root case
  @Test(expected = IllegalArgumentException.class)
  public void sectionCellsSqrt(){
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, "10");
    spreadsheet.setCellAt(a2,"11");
    spreadsheet.setCellAt(b1,"10");
    // then the last corner is blank

    spreadsheet.setCellAt(c3,"=(SQRT A1:B2)");

    assertEquals(new DoubleValue(31.0),spreadsheet.getCellAt(c3).evaluateCell());
  }
  // the comparison case
  @Test(expected = IllegalArgumentException.class)
  public void sectionCellComparison(){
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(a1, "10");
    spreadsheet.setCellAt(a2,"11");
    spreadsheet.setCellAt(b1,"10");


    spreadsheet.setCellAt(c3,"=(< A1:B2)");

    assertEquals(new DoubleValue(31.0),spreadsheet.getCellAt(c3).evaluateCell());
  }

  // the case where evaluate cell for a single reference


  // the case where evaluate cell for multiple references



  // TESTS FOR CHECKING THE VALIDITY OF THE FORMULA
  // referring to own cell directly
  @Test(expected = IllegalArgumentException.class)
  public void directSelfReference() {
    initializeTestSheet(spreadsheet);
    spreadsheet.setCellAt(b2, new Reference("B2", spreadsheet));
    spreadsheet.evaluateSheet();
  }

  //referring to own cell directly within formula
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
    spreadsheet.evaluateSheet();
  }


  // referring to own cell indirectly
  @Test(expected = IllegalArgumentException.class)
  public void invalidReferenceIndirect() {
    initializeTestSheet(spreadsheet);

    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", spreadsheet));
    ourFormulas.add(new Reference("C3", spreadsheet));
    ourFormulas.add(new Reference("C2", spreadsheet));
    ourFormulas.add(new Reference("B1", spreadsheet));
    ourFormulas.add(new Reference("B2", spreadsheet));
    spreadsheet.setCellAt(b2, new Function("PRODUCT", ourFormulas));
    spreadsheet.evaluateSheet();
  }

  //tests referring to cell very indirectly
//  @Test (expected = IllegalArgumentException.class)
//  public void testVeryIndirectReference() {
//    initializeTestSheet(spreadsheet);
//
//    ArrayList<Formula> secondFormulas = new ArrayList<>();
//    secondFormulas.add(new Reference("A1", spreadsheet));
//    secondFormulas.add(new Reference("C3", spreadsheet));
//    secondFormulas.add(new Reference("C2", spreadsheet));
//    secondFormulas.add(new Reference("B1", spreadsheet));
//
//    spreadsheet.setCellAt(c2, new Function("SUM", secondFormulas));
//    spreadsheet.setCellAt(b2, new Reference("C2", spreadsheet));
//    spreadsheet.evaluateSheet();
//
//  }


}
