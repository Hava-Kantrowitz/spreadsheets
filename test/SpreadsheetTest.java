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

  }

  //TEST SET CELL AT

  //tests that a blank cell can be set
  @Test
  public void testBlankCell() {

  }

  //tests that a formula cell can be set
  @Test
  public void testFormulaCell() {

  }

  //tests that a string value cell can be set
  @Test
  public void testStringCell() {

  }

  //tests that a boolean value cell can be set
  @Test
  public void testBoolCell() {

  }

  //tests that a double cell can be set
  @Test
  public void testDoubleCell() {

  }

  //tests that a reference cell can be set
  @Test
  public void testReferenceCell() {

  }

  //TEST EVAL ALL CELLS

  //tests evaluating all cells, cells strings
  @Test
  public void testEvalStrings() {

  }

  //tests evaluating all cells, cells booleans
  @Test
  public void testEvalBools() {

  }

  //tests evaluating all cells, cells doubles
  @Test
  public void testEvalDouble() {

  }

  //tests evaluating all cells, cells formulas
  @Test
  public void testEvalFormulas() {

  }

  //tests evaluating all cells, cells references
  @Test
  public void testEvalReferences() {

  }

  //tests evaluating all cells, cells mixed types
  @Test
  public void testEvalMixed() {

  }
  //TEST EVAL BLANK CELL
  //TEST EVAL FORMULA CELL
  //TEST DIFFERENCE
  //TEST COMPARISON

}
