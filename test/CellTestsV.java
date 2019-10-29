//import org.junit.Test;
//
//import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
//import edu.cs3500.spreadsheets.model.BooleanValue;
//import edu.cs3500.spreadsheets.model.Cell;
//import edu.cs3500.spreadsheets.model.Coord;
//import edu.cs3500.spreadsheets.model.DoubleValue;
//import edu.cs3500.spreadsheets.model.Formula;
//import edu.cs3500.spreadsheets.model.Spreadsheet;
//import edu.cs3500.spreadsheets.model.StringValue;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * This is the testing class for methods related to the specific cells (Victoria's Half).
// */
//public class CellTestsV {
//
//  private Spreadsheet spreadsheet = new BasicSpreadsheet();
//  private Coord a1 = new Coord(1, 1);
//  private Coord b1 = new Coord(2, 1);
//  private Coord c1 = new Coord(3, 1);
//  private Coord a2 = new Coord(1, 2);
//  private Coord b2 = new Coord(2, 2);
//  private Coord c2 = new Coord(3, 2);
//  private Coord a3 = new Coord(1, 3);
//  private Coord b3 = new Coord(2, 3);
//  private Coord c3 = new Coord(3, 3);
//
//  private Cell trueVal = new BooleanValue(true);
//  private Cell falseVal = new BooleanValue(false);
//  private Cell stringVal = new StringValue("hello");
//  private Cell numberVal10 = new DoubleValue(10.0);
//  private Cell numberVal5 = new DoubleValue(5.0);
//  private Cell numberVal2 = new DoubleValue(2.0);
//  private Cell numberVal4 = new DoubleValue(4.0);
//
//  // THESE ARE THE TESTS FOR EVALUATE CELL (WHEN IT IS A VALUE)
//
//  // this is to check that the returned value is correct (when boolean)
//
//
//  // this is to check that the returned value is correct (when double)
//
//  // this is to check that the returned value is correct (when string)
//
//  // this is the test when the value is an integer
//
//  // THESE ARE THE TESTS FOR EVALUATE CELL (WHEN IT IS A FORMULA)
//
//  //PRODUCT TESTS (true and false and strings are zero)
//  // this is the test for product (when has strings)
//  @Test(expected = IllegalArgumentException.class)
//  public void productWithStrings() {
//    Cell stringCell = new Formula("= SUM(hello 1)");
//    stringCell.evaluateCell();
//  }
//
//  // this is the test for product (when it has true) - true is one here
//  @Test
//  public void productWithTrue() {
//    spreadsheet.setCellAt(a1, trueVal);
//    spreadsheet.setCellAt(c3, numberVal10);
//    spreadsheet.setCellAt(b2, new Formula("=(PRODUCT A1 C3)"));
//    assertEquals(new DoubleValue(10.0), spreadsheet.getCellAt(b2).evaluateCell());
//  }
//
//  // this is the test for product (when it has false) - false is zero here
//  @Test
//  public void productWithFalse() {
//    spreadsheet.setCellAt(a1, falseVal);
//    spreadsheet.setCellAt(c3, numberVal10);
//    spreadsheet.setCellAt(b2, new Formula("=(PRODUCT A1 C3)"));
//    assertEquals(new BooleanValue(false), spreadsheet.getCellAt(b2).evaluateCell());
//  }
//
//  // this is the test for product (when has all numbers)
//  @Test
//  public void productWithNumbers() {
//    spreadsheet.setCellAt(a1, numberVal4);
//    spreadsheet.setCellAt(c3, numberVal5);
//    spreadsheet.setCellAt(c2, numberVal4);
//    spreadsheet.setCellAt(b1, numberVal2);
//    spreadsheet.setCellAt(b2, new Formula("=(PRODUCT A1 C3 C2 B1)"));
//    assertEquals(new DoubleValue(160.0), spreadsheet.getCellAt(b2).evaluateCell());
//  }
//
//
//  //SUM TESTS (true and false and strings are zero)
//  // this is the test for sum (when has strings as inputs)
//  @Test
//  public void sumWithStrings() {
//    spreadsheet.setCellAt(a1, stringVal);
//    spreadsheet.setCellAt(c3, numberVal5);
//    spreadsheet.setCellAt(c2, numberVal4);
//    spreadsheet.setCellAt(b1, numberVal2);
//    spreadsheet.setCellAt(b2, new Formula("=(SUM A1 C3 C2 B1)"));
//    assertEquals(new DoubleValue(11.0), spreadsheet.getCellAt(b2).evaluateCell());
//  }
//
//  // this is the test for sum (when has boolean inputs)
//  @Test
//  public void sumWithBoolean() {
//    spreadsheet.setCellAt(a1, trueVal);
//    spreadsheet.setCellAt(c3, numberVal5);
//    spreadsheet.setCellAt(c2, numberVal4);
//    spreadsheet.setCellAt(b1, falseVal);
//    spreadsheet.setCellAt(b2, new Formula("=(SUM A1 C3 C2 B1)"));
//    assertEquals(new DoubleValue(9.0), spreadsheet.getCellAt(b2).evaluateCell());
//  }
//
//  // this is the test for sum (when it has many valid inputs)
//  @Test
//  public void sumWithValid() {
//    spreadsheet.setCellAt(a1, numberVal10);
//    spreadsheet.setCellAt(c3, numberVal5);
//    spreadsheet.setCellAt(c2, numberVal4);
//    spreadsheet.setCellAt(b1, numberVal2);
//    spreadsheet.setCellAt(b2, new Formula("=(SUM A1 C3 C2 B1)"));
//    assertEquals(new DoubleValue(21.0), spreadsheet.getCellAt(b2).evaluateCell());
//  }
//
//
//  // SQRT TESTS
//
//  // this is the test for the string input (exception)
//  @Test(expected = IllegalArgumentException.class)
//  public void sqrtWithStrings() {
//    spreadsheet.setCellAt(a1, stringVal);
//    spreadsheet.setCellAt(b2, new Formula("=(SQRT A1)"));
//    spreadsheet.getCellAt(b2).evaluateCell();
//  }
//
//  // this is the test for when the input is true (one)
//  @Test
//  public void sqrtWithTrue() {
//    spreadsheet.setCellAt(a1, trueVal);
//
//    spreadsheet.setCellAt(b1, new Formula("=(SQRT A1)"));
//    assertEquals(new DoubleValue(1.0), spreadsheet.getCellAt(b1).evaluateCell());
//  }
//
//  // this is the test for when the input is false (zero)
//  @Test
//  public void sqrtWithFalse() {
//    spreadsheet.setCellAt(a1, falseVal);
//
//    spreadsheet.setCellAt(b1, new Formula("=(SQRT A1)"));
//    assertEquals(new DoubleValue(0.0), spreadsheet.getCellAt(b1).evaluateCell());
//  }
//
//  // this is the test for when the input is valid double
//  @Test
//  public void sqrtWithValid() {
//    spreadsheet.setCellAt(a1, numberVal4);
//
//    spreadsheet.setCellAt(b1, new Formula("=(SQRT A1)"));
//    assertEquals(new DoubleValue(2.0), spreadsheet.getCellAt(b1).evaluateCell());
//  }
//
//  // this is the test for when the number is not perfect square
//
//  @Test
//  public void sqrtWithValidNotPerfect() {
//    spreadsheet.setCellAt(a1, numberVal10);
//
//    spreadsheet.setCellAt(b1, new Formula("=(SQRT A1)"));
//    assertEquals(new DoubleValue(Math.sqrt(10)), spreadsheet.getCellAt(b1).evaluateCell());
//  }
//
//  // this is the test for when the input does something within the SQRT
//  @Test
//  public void sqrtWithMoreInside() {
//    spreadsheet.setCellAt(a1, new Formula("=(SUM 5 5)"));
//
//    spreadsheet.setCellAt(b1, new Formula("=(SQRT A1)"));
//    assertEquals(new DoubleValue(Math.sqrt(10)), spreadsheet.getCellAt(b1).evaluateCell());
//  }
//
//
//  // TESTS FOR CHECKING THE VALIDITY OF THE FORMULA
//  // referring to own cell directly
//  @Test(expected = IllegalArgumentException.class)
//  public void productWithInvalidReference() {
//    spreadsheet.setCellAt(a1, numberVal4);
//    spreadsheet.setCellAt(c3, numberVal5);
//    spreadsheet.setCellAt(c2, numberVal4);
//    spreadsheet.setCellAt(b1, numberVal2);
//    spreadsheet.setCellAt(b2, new Formula("=(PRODUCT A1 C3 C2 B1 B2)"));
//  }
//
//
//  // referring to own cell indirectly
//  @Test(expected = IllegalArgumentException.class)
//  public void invalidReferenceIndirect() {
//    spreadsheet.setCellAt(a1, new Formula("=SUM(B2 5)"));
//    spreadsheet.setCellAt(c3, numberVal5);
//    spreadsheet.setCellAt(c2, numberVal4);
//    spreadsheet.setCellAt(b1, numberVal2);
//    spreadsheet.setCellAt(b2, new Formula("=(PRODUCT A1 C3 C2 B1 B2)"));
//  }
//
//
//}
