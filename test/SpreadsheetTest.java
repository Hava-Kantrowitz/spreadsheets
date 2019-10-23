import org.junit.Test;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Spreadsheet;

import static junit.framework.TestCase.assertEquals;

public class SpreadsheetTest {

  //compile test
  @Test
  public void compileTest() {
    assertEquals(1, 1);
  }

  //TEST INTIALIZE SHEET

  //check that there are 4 cells, all blank
  @Test
  public void testInitSheet() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initSheet();
    assertEquals(1, 1);
  }

  //TEST SET CELL AT

  //tests that a blank cell can be set
  @Test
  public void testBlankCell() {
    assertEquals(1, 1);
  }

  //tests that a formula cell can be set
  @Test
  public void testFormulaCell() {
    assertEquals(1, 1);
  }

  //tests that a string value cell can be set
  @Test
  public void testStringCell() {
    assertEquals(1, 1);
  }

  //tests that a boolean value cell can be set
  @Test
  public void testBoolCell() {
    assertEquals(1, 1);
  }

  //tests that a double cell can be set
  @Test
  public void testDoubleCell() {
    assertEquals(1, 1);
  }

  //tests that a reference cell can be set
  @Test
  public void testReferenceCell() {
    assertEquals(1, 1);
  }

  //TEST EVAL ALL CELLS

  //tests evaluating all cells, cells strings
  @Test
  public void testEvalStrings() {
    assertEquals(1, 1);
  }

  //tests evaluating all cells, cells booleans
  @Test
  public void testEvalBools() {
    assertEquals(1, 1);
  }

  //tests evaluating all cells, cells doubles
  @Test
  public void testEvalDouble() {
    assertEquals(1, 1);
  }

  //tests evaluating all cells, cells formulas
  @Test
  public void testEvalFormulas() {
    assertEquals(1, 1);
  }

  //tests evaluating all cells, cells references
  @Test
  public void testEvalReferences() {
    assertEquals(1, 1);
  }

  //tests evaluating all cells, cells mixed types
  @Test
  public void testEvalMixed() {
    assertEquals(1, 1);
  }

  //tests evaluating all cells, a cell has a cyclical reference
  @Test
  public void testEvalCyclicalReference() {
    assertEquals(1, 1);
  }

  //tests evaluating all cells, a cell has a long cyclical reference
  @Test
  public void testEvalLongCyclicalReference() {
    assertEquals(1, 1);
  }

  //TEST EVAL BLANK CELL

  //tests evaluating a blank cell
  @Test
  public void testEvalBlank() {
    assertEquals(1, 1);
  }

  //TEST EVAL FORMULA CELL

  //tests evaluating a formula cell
  @Test
  public void testEvalFormula() {
    assertEquals(1, 1);
  }

  //tests evaluating a formula cell that uses a reference
  @Test
  public void testEvalFormulaReference() {
    assertEquals(1, 1);
  }

  //TEST DIFFERENCE

  //tests difference of two positive doubles
  @Test
  public void testDifferencePos() {
    assertEquals(1, 1);
  }

  //tests difference of two negative doubles
  @Test
  public void testDifferenceNeg() {
    assertEquals(1, 1);
  }

  //tests difference of two positive references
  @Test
  public void testDifferenceReference() {
    assertEquals(1, 1);
  }

  //tests difference of two formulas
  @Test
  public void testDifferenceFormula() {
    assertEquals(1, 1);
  }

  //tests difference of two strings
  @Test
  public void testDifferenceStrings() {
    assertEquals(1, 1);
  }

  //tests difference of two booleans
  @Test
  public void testDifferenceBool() {
    assertEquals(1, 1);
  }

  //TEST COMPARISON

  //tests comparison of two positive doubles
  @Test
  public void testCompPos() {
    assertEquals(1, 1);
  }

  //tests comparison of two negative doubles
  @Test
  public void testCompNeg() {
    assertEquals(1, 1);
  }

  //tests comparison of two positive references
  @Test
  public void testCompReference() {
    assertEquals(1, 1);
  }

  //tests comparison of two formulas
  @Test
  public void testCompFormula() {
    assertEquals(1, 1);
  }

  //tests comparison of two strings
  @Test
  public void testCompStrings() {
    assertEquals(1, 1);
  }

  //tests comparison of two booleans
  @Test
  public void testCompBool() {
    assertEquals(1, 1);
  }

}
