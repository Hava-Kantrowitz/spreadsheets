import org.junit.Test;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Value;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertTrue;

public class SpreadsheetTest {

  //compile test
  @Test
  public void compileTest() {
    assertEquals(1, 1);
  }

  //TEST INTIALIZE SHEET

  //check that there are 10 cells, all blank
  @Test
  public void testInitSheet() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell firstCell = testSheet.getCellAt(coord1);
    Coord coord2 = new Coord(10, 10);
    Cell secondCell = testSheet.getCellAt(coord2);
    Cell compCell = new Blank();
    assertEquals(compCell,firstCell);
    assertTrue(compCell.equals(secondCell));
  }

  //tests initializing with a given file

  //TEST SET CELL AT

  //tests that a blank cell can be set
  @Test
  public void testBlankCell() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell hamCell = new StringValue("Ham the jam");
    testSheet.setCellAt(coord1, hamCell);
    testSheet.setCellAt(coord1, new Blank());   // changed this to blank instead of null
    Cell compCell = new Blank();
    assertEquals(compCell,testSheet.getCellAt(coord1));
  }

  //tests that a formula cell can be set
  @Test
  public void testFormulaCell() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell formCell = new Formula("=(SUM 4 3)");
    testSheet.setCellAt(coord1, formCell);
    assertTrue(formCell.equals(testSheet.getCellAt(coord1)));
  }

  //tests that a string value cell can be set
  @Test
  public void testStringCell() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell hamCell = new StringValue("Ham the jam");
    testSheet.setCellAt(coord1, hamCell);
    assertTrue(hamCell.equals(testSheet.getCellAt(coord1)));
  }

  //tests that a boolean value cell can be set
  @Test
  public void testBoolCell() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell boolCell = new BooleanValue(true);
    testSheet.setCellAt(coord1, boolCell);
    assertTrue(boolCell.equals(testSheet.getCellAt(coord1)));
  }

  //tests that a double cell can be set
  @Test
  public void testDoubleCell() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell doubleCell = new DoubleValue(7.2);
    testSheet.setCellAt(coord1, doubleCell);
    assertTrue(doubleCell.equals(testSheet.getCellAt(coord1)));
  }

  //tests that a reference cell can be set
  @Test
  public void testReferenceCell() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new DoubleValue(8.0);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new DoubleValue(2.0);
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new Formula("=(SUM A1 A2)");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    assertEquals(val3,testSheet.getCellAt(coord3));
  }

  //tests that a very large cell can be set

  //TEST EVAL ALL CELLS

  //tests evaluating all cells, cells strings
  @Test
  public void testEvalStrings() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new StringValue("my name is");
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new StringValue("Alexander Hamilton");
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new StringValue("and I'm treasury secretary");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.evaluateSheet();
    StringValue comp1 = new StringValue("my name is");
    StringValue comp2 = new StringValue("Alexander Hamilton");
    StringValue comp3 = new StringValue("and I'm treasury secretary");
    assertEquals(comp1, testSheet.getCellAt(coord1).evaluateCell());
    assertEquals(comp2, testSheet.getCellAt(coord2).evaluateCell());
    assertEquals(comp3, testSheet.getCellAt(coord3).evaluateCell());

  }

  //tests evaluating all cells, cells booleans
  @Test
  public void testEvalBools() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new BooleanValue(true);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new BooleanValue(true);
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new BooleanValue(false);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.evaluateSheet();
    BooleanValue comp1 = new BooleanValue(true);
    BooleanValue comp2 = new BooleanValue(false);
    assertEquals(comp1, testSheet.getCellAt(coord1).evaluateCell());
    assertEquals(comp1, testSheet.getCellAt(coord2).evaluateCell());
    assertEquals(comp2, testSheet.getCellAt(coord3).evaluateCell());

  }

  //tests evaluating all cells, cells doubles
  @Test
  public void testEvalDouble() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new DoubleValue(3.3);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new DoubleValue(4.0);
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new DoubleValue(172.8);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.evaluateSheet();
    DoubleValue comp1 = new DoubleValue(3.3);
    DoubleValue comp2 = new DoubleValue(4.0);
    DoubleValue comp3 = new DoubleValue(172.8);
    assertEquals(comp1, testSheet.getCellAt(coord1).evaluateCell());
    assertEquals(comp2, testSheet.getCellAt(coord2).evaluateCell());
    assertEquals(comp3, testSheet.getCellAt(coord3).evaluateCell());

  }

  //tests evaluating all cells, cells formulas
  @Test
  public void testEvalFormulas() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new Formula("=(SUM 7 2)");
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new Formula("=(SUM 3 3)");
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new Formula("=(SUM 2 2)");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.evaluateSheet();
    DoubleValue comp1 = new DoubleValue(9.0);
    DoubleValue comp2 = new DoubleValue(6.0);
    DoubleValue comp3 = new DoubleValue(4.0);
    assertEquals(comp1, testSheet.getCellAt(coord1).evaluateCell());
    assertEquals(comp2, testSheet.getCellAt(coord2).evaluateCell());
    assertEquals(comp3, testSheet.getCellAt(coord3).evaluateCell());

  }

  //tests evaluating all cells, cells references
  @Test
  public void testEvalReferences() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new DoubleValue(2.0);
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new DoubleValue(1.0);
    Coord coord4 = new Coord(1, 3);
    Cell val4 = new Formula("A1");
    Coord coord5 = new Coord(2, 3);
    Cell val5 = new Formula("A2");
    Coord coord6 = new Coord(3, 3);
    Cell val6 = new Formula("B1");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.setCellAt(coord4, val4);
    testSheet.setCellAt(coord5, val5);
    testSheet.setCellAt(coord6, val6);
    testSheet.evaluateSheet();
    DoubleValue comp1 = new DoubleValue(4.0);
    DoubleValue comp2 = new DoubleValue(2.0);
    DoubleValue comp3 = new DoubleValue(1.0);
    assertEquals(comp1, testSheet.getCellAt(coord4).evaluateCell());
    assertEquals(comp2, testSheet.getCellAt(coord5).evaluateCell());
    assertEquals(comp3, testSheet.getCellAt(coord6).evaluateCell());
  }

  //tests evaluating all cells, cells mixed types
  @Test
  public void testEvalMixed() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new StringValue("hey");
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new BooleanValue(true);
    Coord coord4 = new Coord(1, 3);
    Cell val4 = new Formula("A1");
    Coord coord5 = new Coord(2, 3);
    Cell val5 = new Formula("= 2 4");
    Coord coord6 = new Coord(3, 3);
    Cell val6 = new Blank();
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.setCellAt(coord4, val4);
    testSheet.setCellAt(coord5, val5);
    testSheet.setCellAt(coord6, val6);
    testSheet.evaluateSheet();
    Value comp1 = new DoubleValue(4.0);
    Value comp2 = new StringValue("hey");
    Value comp3 = (Value) new BooleanValue(true);
    Value comp4 = new DoubleValue(4.0);
    Value comp5 = new DoubleValue(6.0);
    Value comp6 = new DoubleValue(0.0);
    assertEquals(comp1, testSheet.getCellAt(coord1).evaluateCell());
    assertEquals(comp2, testSheet.getCellAt(coord2).evaluateCell());
    assertEquals(comp3, testSheet.getCellAt(coord3).evaluateCell());
    assertEquals(comp4, testSheet.getCellAt(coord4).evaluateCell());
    assertEquals(comp5, testSheet.getCellAt(coord5).evaluateCell());
    assertEquals(comp6, testSheet.getCellAt(coord6).evaluateCell());
  }

  //TEST EVAL BLANK CELL

  //tests evaluating a blank cell
  @Test
  public void testEvalBlank() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Value comp1 = new DoubleValue(0.0);
    testSheet.setCellAt(coord1,comp1);
    assertEquals(comp1, testSheet.getCellAt(coord1).evaluateCell());
  }

  //TEST EVAL FORMULA CELL

  //tests evaluating a formula cell
  @Test
  public void testEvalFormula() {
    Cell cell1 = new Formula("=(SUM 4 3)");
    Value comp1 = new DoubleValue(7.0);
    assertEquals(comp1, cell1.evaluateCell());
  }

  //tests evaluating a formula cell that uses a reference
  @Test
  public void testEvalFormulaReference() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Value val1 = new DoubleValue(11.0);
    testSheet.setCellAt(coord1, val1);
    Cell cell1 = new Formula("A1");
    assertEquals(val1, cell1.evaluateCell());
  }

  //TEST DIFFERENCE

  //tests difference of two positive doubles
  @Test
  public void testDifferencePos() {
    Cell cell1 = new Formula("=(DIFFERENCE 4 3)");
    Value comp1 = new DoubleValue(1.0);
    assertEquals(comp1, cell1.evaluateCell());
  }

  //tests difference of two negative doubles
  @Test
  public void testDifferenceNeg() {
    Cell cell1 = new Formula("=(DIFFERENCE -4 3)");
    Value comp1 = new DoubleValue(-7.0);
    assertEquals(comp1, cell1.evaluateCell());
  }

  //tests difference of two positive references
  @Test
  public void testDifferenceReference() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Value val1 = new DoubleValue(10.0);
    Coord coord2 = new Coord(2, 1);
    Value val2 = new DoubleValue(2.0);
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new Formula("=(DIFFERENCE A1 A2)");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    Value comp1 = new DoubleValue(8.0);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //tests difference of two formula references
  @Test
  public void testDifferenceFormula() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new Formula("=(SUM 5 5)");
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new Formula("=(SUM 1 1)");
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new Formula("=(DIFFERENCE A1 A2)");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    Value comp1 = new DoubleValue(8.0);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //tests difference of two string references
  @Test
  public void testDifferenceStrings() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new StringValue("hamilton");
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new StringValue("burr");
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new Formula("=(DIFFERENCE A1 A2)");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.getCellAt(coord3).evaluateCell();
    Value comp1 = new DoubleValue(0.0);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //tests difference of two boolean references
  @Test
  public void testDifferenceBool() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new BooleanValue(true);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new BooleanValue(false);
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new Formula("=(DIFFERENCE A1 A2)");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    Value comp1 = new DoubleValue(0.0);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //tests difference of two blanks
  @Test
  public void testDifferenceBlank() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new Formula("=(DIFFERENCE A1 A2)");
    testSheet.setCellAt(coord3, val3);
    Value comp1 = new DoubleValue(0.0);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //TEST COMPARISON

  //tests comparison of two positive doubles
  @Test
  public void testCompPos() {
    Cell cell1 = new Formula("=(< 4 3)");
    Value comp1 = (Value) new BooleanValue(false);
    assertEquals(comp1, cell1.evaluateCell());
  }

  //tests comparison of two negative doubles
  @Test
  public void testCompNeg() {
    Cell cell1 = new Formula("=(< -4 3)");
    Value comp1 = (Value) new BooleanValue(true);
    assertEquals(comp1, cell1.evaluateCell());
  }

  //tests comparison of two positive references
  @Test
  public void testCompReference() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new DoubleValue(16.0);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new DoubleValue(2.0);
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new Formula("=(< A1 A2)");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    Value comp1 = (Value) new BooleanValue(false);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //tests comparison of two formula references
  @Test
  public void testCompFormula() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new Formula("=(SUM 5 5)");
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new Formula("=(SUM 1 1)");
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new Formula("=(< A1 A2)");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    Value comp1 = (Value) new BooleanValue(false);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //tests comparison of two string references
  @Test (expected = IllegalArgumentException.class)
  public void testCompStrings() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new StringValue("a. ham");
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new StringValue("a. burr");
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new Formula("=(< A1 A2)");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.getCellAt(coord3).evaluateCell();
  }

  //tests comparison of two boolean references
  @Test (expected = IllegalArgumentException.class)
  public void testCompBools() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new BooleanValue(false);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new BooleanValue(true);
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new Formula("=(< A1 A2)");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.getCellAt(coord3).evaluateCell();
  }

  //tests comparison of blanks
  @Test (expected = IllegalArgumentException.class)
  public void testCompBlanks() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new Formula("=(< A1 A2)");
    testSheet.setCellAt(coord3, val3);
    testSheet.getCellAt(coord3).evaluateCell();
  }




}
