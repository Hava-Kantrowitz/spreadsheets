import org.junit.Test;

import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;

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
import edu.cs3500.spreadsheets.model.Value;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests various spreadsheet applications.
 */
public class SpreadsheetTest {

  //TEST INTIALIZE SHEET



  //tests initializing with a given file

  // this is the test for initializing with a file (testing basic double setting)
  @Test
  public void testInitSheetWithFile() throws FileNotFoundException {
    Spreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingText.txt");
    assertEquals(new DoubleValue(3.0),testSheet.getCellAt(new Coord(1,1)));//simple double
    assertEquals(new DoubleValue(7.0),testSheet.getCellAt(new Coord(28,1)));//two double
    assertEquals(new BooleanValue(true),testSheet.getCellAt(new Coord(1,3)));
    assertEquals(new BooleanValue(false),testSheet.getCellAt(new Coord(81,4)));
    assertEquals(new StringValue("hello"),testSheet.getCellAt(new Coord(1,200)));

  }


  //TEST SET CELL AT

  //tests that a blank cell can be set
  @Test
  public void testBlankCell() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell hamCell = new StringValue("Ham the jam");
    testSheet.setCellAt(coord1, hamCell);
    testSheet.setCellAt(coord1, new Blank());   // changed this to blank instead of null
    Cell compCell = new Blank();
    assertEquals(compCell, testSheet.getCellAt(coord1));
  }

  //tests that a formula cell can be set
  @Test
  public void testFormulaCell() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new DoubleValue(4.0));
    ourFormulas.add(new DoubleValue(3.0));
    Cell formCell = new Function("SUM", ourFormulas);
    testSheet.setCellAt(coord1, formCell);
    assertTrue(formCell.equals(testSheet.getCellAt(coord1)));
  }

  //tests that a string value cell can be set
  @Test
  public void testStringCell() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell hamCell = new StringValue("Ham the jam");
    testSheet.setCellAt(coord1, hamCell);
    assertTrue(hamCell.equals(testSheet.getCellAt(coord1)));
  }

  //tests that a boolean value cell can be set
  @Test
  public void testBoolCell() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell boolCell = new BooleanValue(true);
    testSheet.setCellAt(coord1, boolCell);
    assertTrue(boolCell.equals(testSheet.getCellAt(coord1)));
  }

  //tests that a double cell can be set
  @Test
  public void testDoubleCell() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell doubleCell = new DoubleValue(7.2);
    testSheet.setCellAt(coord1, doubleCell);
    assertTrue(doubleCell.equals(testSheet.getCellAt(coord1)));
  }

  //tests that a reference cell can be set
  @Test
  public void testReferenceCell() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new DoubleValue(8.0);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new DoubleValue(2.0);
    Coord coord3 = new Coord(1, 2);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", testSheet));
    ourFormulas.add(new Reference("A2", testSheet));
    Cell val3 = new Function("SUM", ourFormulas);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    assertEquals(val3, testSheet.getCellAt(coord3));
  }

  //tests that a very large cell can be set

  //TEST EVAL ALL CELLS

  //tests evaluating two in a column
  @Test
  public void inColumn() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new StringValue("my name is");
    Coord coord2 = new Coord(1, 2);
    Cell val2 = new StringValue("Alexander Hamilton");
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.evaluateSheet();
    StringValue comp1 = new StringValue("my name is");
    StringValue comp2 = new StringValue("Alexander Hamilton");
    //System.out.println(comp2.toString());
    //System.out.println(testSheet.getCellAt(coord2).evaluateCell().toString());
    assertEquals(comp1, testSheet.getCellAt(coord1).evaluateCell());
    assertEquals(comp2, testSheet.getCellAt(coord2).evaluateCell());
  }

  //tests evaluating all cells, cells strings
  @Test
  public void testEvalStrings() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
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
    System.out.println(comp1.toString());
    System.out.println(testSheet.getCellAt(coord1).evaluateCell().toString());
    assertEquals(comp1, testSheet.getCellAt(coord1).evaluateCell());
    assertEquals(comp2, testSheet.getCellAt(coord2).evaluateCell());
    assertEquals(comp3, testSheet.getCellAt(coord3).evaluateCell());

  }

  //tests evaluating all cells, cells booleans
  @Test
  public void testEvalBools() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
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
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
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
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src" +
            "\\edu\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    ArrayList<Formula> firstFormulas = new ArrayList<>();
    firstFormulas.add(new DoubleValue(7.0));
    firstFormulas.add(new DoubleValue(3.0));
    Cell val1 = new Function("SUM", firstFormulas);
    Coord coord2 = new Coord(2, 1);
    ArrayList<Formula> secondFormulas = new ArrayList<>();
    secondFormulas.add(new DoubleValue(3.0));
    secondFormulas.add(new DoubleValue(3.0));
    Cell val2 = new Function("SUM", secondFormulas);
    Coord coord3 = new Coord(1, 2);
    ArrayList<Formula> thirdFormulas = new ArrayList<>();
    thirdFormulas.add(new DoubleValue(2.0));
    thirdFormulas.add(new DoubleValue(2.0));
    Cell val3 = new Function("SUM", thirdFormulas);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.evaluateSheet();
    DoubleValue comp1 = new DoubleValue(10.0);
    DoubleValue comp2 = new DoubleValue(6.0);
    DoubleValue comp3 = new DoubleValue(4.0);
    assertEquals(comp1, testSheet.getCellAt(coord1).evaluateCell());
    assertEquals(comp2, testSheet.getCellAt(coord2).evaluateCell());
    assertEquals(comp3, testSheet.getCellAt(coord3).evaluateCell());

  }

  //tests evaluating all cells, cells references
  @Test
  public void testEvalReferences() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new DoubleValue(2.0);
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new DoubleValue(1.0);
    Coord coord4 = new Coord(1, 3);
    Cell val4 = new Reference("A1", testSheet);
    Coord coord5 = new Coord(2, 3);
    Cell val5 = new Reference("A2", testSheet);
    Coord coord6 = new Coord(3, 3);
    Cell val6 = new Reference("B1", testSheet);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.setCellAt(coord4, val4);
    testSheet.setCellAt(coord5, val5);
    testSheet.setCellAt(coord6, val6);
    testSheet.evaluateSheet();
    assertEquals(val1, testSheet.getCellAt(coord4).evaluateCell());
    assertEquals(val3, testSheet.getCellAt(coord5).evaluateCell());
    assertEquals(val2, testSheet.getCellAt(coord6).evaluateCell());
  }

  //tests evaluating all cells, cells mixed types
  @Test
  public void testEvalMixed() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new StringValue("hey");
    Coord coord3 = new Coord(1, 2);
    Cell val3 = new BooleanValue(true);
    Coord coord4 = new Coord(1, 3);
    Cell val4 = new Reference("A1", testSheet);
    Coord coord5 = new Coord(2, 3);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new DoubleValue(2.0));
    ourFormulas.add(new DoubleValue(4.0));
    Cell val5 = new Function("SUM", ourFormulas);
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
    Value comp3 = new BooleanValue(true);
    Value comp4 = new DoubleValue(4.0);
    Value comp5 = new DoubleValue(6.0);
    Value comp6 = new DoubleValue(0.0);
    assertEquals(comp1, testSheet.getCellAt(coord1).evaluateCell());
    assertEquals(comp2, testSheet.getCellAt(coord2).evaluateCell());
    assertEquals(comp3, testSheet.getCellAt(coord3).evaluateCell());
    assertEquals(comp4, testSheet.getCellAt(coord4).evaluateCell());
    assertEquals(comp5, testSheet.getCellAt(coord5).evaluateCell());
    assertEquals(null, testSheet.getCellAt(coord6).evaluateCell());
  }

  //TEST EVAL BLANK CELL

  //tests evaluating a blank cell
  @Test
  public void testEvalBlank() {
    Spreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Value comp1 = new DoubleValue(0.0);
    testSheet.setCellAt(coord1, comp1);
    assertEquals(comp1, testSheet.getCellAt(coord1).evaluateCell());
  }

  //TEST EVAL FORMULA CELL

  //tests evaluating a formula cell
  @Test
  public void testEvalFormula() {
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new DoubleValue(4.0));
    ourFormulas.add(new DoubleValue(3.0));
    Cell cell1 = new Function("SUM", ourFormulas);
    Value comp1 = new DoubleValue(7.0);
    assertEquals(comp1, cell1.evaluateCell());
  }

  //tests evaluating a formula cell that uses a reference
  @Test
  public void testEvalFormulaReference() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Value val1 = new DoubleValue(11.0);
    testSheet.setCellAt(coord1, val1);
    Cell cell1 = new Reference("A1", testSheet);
    assertEquals(val1, cell1.evaluateCell());
  }

  //TEST DIFFERENCE

  //tests difference of two positive doubles
  @Test
  public void testDifferencePos() {
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new DoubleValue(4.0));
    ourFormulas.add(new DoubleValue(3.0));
    Cell cell1 = new Function("SUB", ourFormulas);
    Value comp1 = new DoubleValue(1.0);
    assertEquals(comp1, cell1.evaluateCell());
  }

  //tests difference of two negative doubles
  @Test
  public void testDifferenceNeg() {
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new DoubleValue(-4.0));
    ourFormulas.add(new DoubleValue(3.0));
    Cell cell1 = new Function("SUM", ourFormulas);
    Value comp1 = new DoubleValue(-1.0);
    assertEquals(comp1, cell1.evaluateCell());
  }

  //tests difference of two positive references
  @Test
  public void testDifferenceReference() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu\\" +
            "cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Value val1 = new DoubleValue(10.0);
    Coord coord2 = new Coord(2, 1);
    Value val2 = new DoubleValue(2.0);
    Coord coord3 = new Coord(1, 2);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", testSheet));
    ourFormulas.add(new Reference("A2", testSheet));
    Cell val3 = new Function("SUM", ourFormulas);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    Value comp1 = new DoubleValue(8.0);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //tests difference of two formula references
  @Test
  public void testDifferenceFormula() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    ArrayList<Formula> firstFormulas = new ArrayList<>();
    firstFormulas.add(new DoubleValue(5.0));
    firstFormulas.add(new DoubleValue(5.0));
    Cell val1 = new Function("SUM", firstFormulas);
    Coord coord2 = new Coord(2, 1);
    ArrayList<Formula> secondFormulas = new ArrayList<>();
    secondFormulas.add(new DoubleValue(1.0));
    secondFormulas.add(new DoubleValue(1.0));
    Cell val2 = new Function("SUM", secondFormulas);
    Coord coord3 = new Coord(1, 2);
    ArrayList<Formula> thirdFormulas = new ArrayList<>();
    thirdFormulas.add(new Reference("A1", testSheet));
    thirdFormulas.add(new Reference("A2", testSheet));
    Cell val3 = new Function("SUB", thirdFormulas);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    Value comp1 = new DoubleValue(8.0);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //tests difference of two string references
  @Test(expected = IllegalArgumentException.class)
  public void testDifferenceStrings() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new StringValue("hamilton");
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new StringValue("burr");
    Coord coord3 = new Coord(1, 2);
    ArrayList<Formula> thirdFormulas = new ArrayList<>();
    thirdFormulas.add(new Reference("A1", testSheet));
    thirdFormulas.add(new Reference("A2", testSheet));
    Cell val3 = new Function("SUB", thirdFormulas);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.getCellAt(coord3).evaluateCell();
    Value comp1 = new DoubleValue(0.0);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //tests difference of two boolean references
  @Test(expected = IllegalArgumentException.class)
  public void testDifferenceBool() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new BooleanValue(true);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new BooleanValue(false);
    Coord coord3 = new Coord(1, 2);
    ArrayList<Formula> thirdFormulas = new ArrayList<>();
    thirdFormulas.add(new Reference("A1", testSheet));
    thirdFormulas.add(new Reference("A2", testSheet));
    Cell val3 = new Function("SUB", thirdFormulas);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    Value comp1 = new DoubleValue(0.0);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //tests difference of two blanks
  @Test (expected = IllegalArgumentException.class)
  public void testDifferenceBlank() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord3 = new Coord(1, 2);
    ArrayList<Formula> thirdFormulas = new ArrayList<>();
    thirdFormulas.add(new Reference("A1", testSheet));
    thirdFormulas.add(new Reference("A2", testSheet));
    Cell val3 = new Function("SUB", thirdFormulas);
    testSheet.setCellAt(coord3, val3);
    Value comp1 = new DoubleValue(0.0);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //TEST COMPARISON

  //tests comparison of two positive doubles
  @Test
  public void testCompPos() {
    ArrayList<Formula> firstFormulas = new ArrayList<>();
    firstFormulas.add(new DoubleValue(4.0));
    firstFormulas.add(new DoubleValue(3.0));
    Cell cell1 = new Function("<", firstFormulas);
    Value comp1 = (Value) new BooleanValue(false);
    assertEquals(comp1, cell1.evaluateCell());
  }

  //tests comparison of two negative doubles
  @Test
  public void testCompNeg() {
    ArrayList<Formula> firstFormulas = new ArrayList<>();
    firstFormulas.add(new DoubleValue(-4.0));
    firstFormulas.add(new DoubleValue(3.0));
    Cell cell1 = new Function("<", firstFormulas);
    Value comp1 = (Value) new BooleanValue(true);
    assertEquals(comp1, cell1.evaluateCell());
  }

  //tests comparison of two positive references
  @Test (expected = IllegalArgumentException.class)
  public void testCompReference() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new DoubleValue(16.0);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new DoubleValue(2.0);
    Coord coord3 = new Coord(1, 2);
    ArrayList<Formula> ourFormulas = new ArrayList<>();
    ourFormulas.add(new Reference("A1", testSheet));
    ourFormulas.add(new Reference("A2", testSheet));
    Cell val3 = new Function("<", ourFormulas);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    Value comp1 = (Value) new BooleanValue(false);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //tests comparison of two formula references
  @Test
  public void testCompFormula() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    ArrayList<Formula> firstFormulas = new ArrayList<>();
    firstFormulas.add(new DoubleValue(5.0));
    firstFormulas.add(new DoubleValue(5.0));
    Cell val1 = new Function("SUM", firstFormulas);
    Coord coord2 = new Coord(2, 1);
    ArrayList<Formula> secondFormulas = new ArrayList<>();
    firstFormulas.add(new DoubleValue(1.0));
    firstFormulas.add(new DoubleValue(1.0));
    Cell val2 = new Function("SUM", secondFormulas);
    Coord coord3 = new Coord(1, 2);
    ArrayList<Formula> thirdFormulas = new ArrayList<>();
    thirdFormulas.add(new Reference("A1", testSheet));
    thirdFormulas.add(new Reference("A2", testSheet));
    Cell val3 = new Function("<", thirdFormulas);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    Value comp1 = new BooleanValue(false);
    assertEquals(comp1, testSheet.getCellAt(coord3).evaluateCell());
  }

  //tests comparison of two string references
  @Test(expected = IllegalArgumentException.class)
  public void testCompStrings() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new StringValue("a. ham");
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new StringValue("a. burr");
    Coord coord3 = new Coord(1, 2);
    ArrayList<Formula> thirdFormulas = new ArrayList<>();
    thirdFormulas.add(new Reference("A1", testSheet));
    thirdFormulas.add(new Reference("A2", testSheet));
    Cell val3 = new Function("<", thirdFormulas);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.getCellAt(coord3).evaluateCell();
  }

  //tests comparison of two boolean references
  @Test(expected = IllegalArgumentException.class)
  public void testCompBools() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu\\" +
            "cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord1 = new Coord(1, 1);
    Cell val1 = new BooleanValue(false);
    Coord coord2 = new Coord(2, 1);
    Cell val2 = new BooleanValue(true);
    Coord coord3 = new Coord(1, 2);
    ArrayList<Formula> thirdFormulas = new ArrayList<>();
    thirdFormulas.add(new Reference("A1", testSheet));
    thirdFormulas.add(new Reference("A2", testSheet));
    Cell val3 = new Function("<", thirdFormulas);
    testSheet.setCellAt(coord1, val1);
    testSheet.setCellAt(coord2, val2);
    testSheet.setCellAt(coord3, val3);
    testSheet.getCellAt(coord3).evaluateCell();
  }

  //tests comparison of blanks
  @Test(expected = IllegalArgumentException.class)
  public void testCompBlanks() {
    BasicSpreadsheet testSheet = new BasicSpreadsheet();
    testSheet.initializeSpreadsheet("C:\\Users\\havak\\IdeaProjects\\nextTry\\src\\edu" +
            "\\cs3500\\spreadsheets\\testingBlankTenByTen.txt");
    Coord coord3 = new Coord(1, 2);
    ArrayList<Formula> thirdFormulas = new ArrayList<>();
    thirdFormulas.add(new Reference("A1", testSheet));
    thirdFormulas.add(new Reference("A2", testSheet));
    Cell val3 = new Function("<", thirdFormulas);
    testSheet.setCellAt(coord3, val3);
    testSheet.getCellAt(coord3).evaluateCell();
  }


}
