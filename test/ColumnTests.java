import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.StringValue;

import static junit.framework.TestCase.assertEquals;

/**
 * This is a testing class for the full column reference.
 */
public class ColumnTests {

  /**
   * Initializes the spreadsheet to perform tests with.
   *
   * @param sheet the desired sheet
   */
  private static void initializeTestSheet(Spreadsheet sheet) {
    try {
      sheet.initializeSpreadsheet(new FileReader("/Users/victoriabowen/Desktop/" +
              "NEU_1st_year/ObjectOriented/CS_3500_Projects/spreadsheets/" +
              "src/edu/cs3500/spreadsheets/testingBlankTenByTen.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  //sum with single col ref
  @Test
  public void testSumOneCol() {
    Spreadsheet testSheet = new BasicSpreadsheet();

    initializeTestSheet(testSheet);


    Coord coord1 = new Coord(1, 1);
    Cell cell1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(1, 3);
    Cell cell2 = new StringValue("Hey");//string multiplication tested
    Coord coord3 = new Coord(1, 4);
    Cell cell3 = new DoubleValue(2.0);
    Coord coord4 = new Coord(2, 6);
    Cell cell4 = new DoubleValue(3.0);
    Coord coord5 = new Coord(2, 2);
    Cell cell5 = new DoubleValue(11.0);
    testSheet.setCellAt(coord1, cell1);
    testSheet.setCellAt(coord2, cell2);
    testSheet.setCellAt(coord3, cell3);
    testSheet.setCellAt(coord4, cell4);
    testSheet.setCellAt(coord5, cell5);
    Coord refCoord = new Coord(4, 2);
    testSheet.setCellAt(refCoord, "=(SUM A:B)");

    assertEquals(new DoubleValue(20.0), testSheet.getCellAt(refCoord).evaluateCell());
  }

  //sum with multiple col ref
  @Test
  public void testSumMultCol() {
    Spreadsheet testSheet = new BasicSpreadsheet();

    initializeTestSheet(testSheet);


    Coord coord1 = new Coord(1, 1);
    Cell cell1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(1, 3);
    Cell cell2 = new StringValue("Hey");//string multiplication tested
    Coord coord3 = new Coord(1, 4);
    Cell cell3 = new DoubleValue(2.0);
    Coord coord4 = new Coord(2, 6);
    Cell cell4 = new DoubleValue(3.0);
    Coord coord5 = new Coord(2, 2);
    Cell cell5 = new DoubleValue(11.0);
    Coord coord6 = new Coord(3, 4);
    Cell cell6 = new StringValue("Nathan");
    Coord coord7 = new Coord(3, 1);
    Cell cell7 = new DoubleValue(7.0);
    testSheet.setCellAt(coord1, cell1);
    testSheet.setCellAt(coord2, cell2);
    testSheet.setCellAt(coord3, cell3);
    testSheet.setCellAt(coord4, cell4);
    testSheet.setCellAt(coord5, cell5);
    testSheet.setCellAt(coord6, cell6);
    testSheet.setCellAt(coord7, cell7);
    Coord refCoord = new Coord(7, 2);
    testSheet.setCellAt(refCoord, "=(SUM A:C)");

    assertEquals(new DoubleValue(27.0), testSheet.getCellAt(refCoord).evaluateCell());
  }

  //product with sc
  @Test
  public void testMultOneCol() {
    Spreadsheet testSheet = new BasicSpreadsheet();

    initializeTestSheet(testSheet);


    Coord coord1 = new Coord(1, 1);
    Cell cell1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(1, 3);
    Cell cell2 = new StringValue("Hey");//string multiplication tested
    Coord coord3 = new Coord(1, 4);
    Cell cell3 = new DoubleValue(2.0);
    Coord coord4 = new Coord(2, 6);
    Cell cell4 = new DoubleValue(3.0);
    Coord coord5 = new Coord(2, 2);
    Cell cell5 = new DoubleValue(11.0);
    testSheet.setCellAt(coord1, cell1);
    testSheet.setCellAt(coord2, cell2);
    testSheet.setCellAt(coord3, cell3);
    testSheet.setCellAt(coord4, cell4);
    testSheet.setCellAt(coord5, cell5);
    Coord refCoord = new Coord(4, 2);
    testSheet.setCellAt(refCoord, "=(PRODUCT A:B)");

    assertEquals(new DoubleValue(264.0), testSheet.getCellAt(refCoord).evaluateCell());
  }

  //product with mc
  @Test
  public void testMultMultCol() {
    Spreadsheet testSheet = new BasicSpreadsheet();

    initializeTestSheet(testSheet);


    Coord coord1 = new Coord(1, 1);
    Cell cell1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(1, 3);
    Cell cell2 = new StringValue("Hey");//string multiplication tested
    Coord coord3 = new Coord(1, 4);
    Cell cell3 = new DoubleValue(2.0);
    Coord coord4 = new Coord(2, 6);
    Cell cell4 = new DoubleValue(3.0);
    Coord coord5 = new Coord(2, 2);
    Cell cell5 = new DoubleValue(11.0);
    Coord coord6 = new Coord(3, 4);
    Cell cell6 = new StringValue("Nathan");
    Coord coord7 = new Coord(3, 1);
    Cell cell7 = new DoubleValue(7.0);
    testSheet.setCellAt(coord1, cell1);
    testSheet.setCellAt(coord2, cell2);
    testSheet.setCellAt(coord3, cell3);
    testSheet.setCellAt(coord4, cell4);
    testSheet.setCellAt(coord5, cell5);
    testSheet.setCellAt(coord6, cell6);
    testSheet.setCellAt(coord7, cell7);
    Coord refCoord = new Coord(7, 2);
    testSheet.setCellAt(refCoord, "=(PRODUCT A:C)");

    assertEquals(new DoubleValue(1848.0), testSheet.getCellAt(refCoord).evaluateCell());
  }

  //difference with mc
  @Test(expected = IllegalArgumentException.class)
  public void testDiffMultCol() {
    Spreadsheet testSheet = new BasicSpreadsheet();

    initializeTestSheet(testSheet);


    Coord coord1 = new Coord(1, 1);
    Cell cell1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(1, 3);
    Cell cell2 = new StringValue("Hey");//string multiplication tested
    Coord coord3 = new Coord(1, 4);
    Cell cell3 = new DoubleValue(2.0);
    Coord coord4 = new Coord(2, 6);
    Cell cell4 = new DoubleValue(3.0);
    Coord coord5 = new Coord(2, 2);
    Cell cell5 = new DoubleValue(11.0);
    testSheet.setCellAt(coord1, cell1);
    testSheet.setCellAt(coord2, cell2);
    testSheet.setCellAt(coord3, cell3);
    testSheet.setCellAt(coord4, cell4);
    testSheet.setCellAt(coord5, cell5);
    Coord refCoord = new Coord(4, 2);
    testSheet.setCellAt(refCoord, "=(SUB A:C)");
    testSheet.getCellAt(refCoord).evaluateCell();
  }

  //difference with sc
  @Test(expected = IllegalArgumentException.class)
  public void testDiffOneCol() {
    Spreadsheet testSheet = new BasicSpreadsheet();

    initializeTestSheet(testSheet);


    Coord coord1 = new Coord(1, 1);
    Cell cell1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(1, 3);
    Cell cell2 = new StringValue("Hey");//string multiplication tested
    Coord coord3 = new Coord(1, 4);
    Cell cell3 = new DoubleValue(2.0);
    Coord coord4 = new Coord(2, 6);
    Cell cell4 = new DoubleValue(3.0);
    Coord coord5 = new Coord(2, 2);
    Cell cell5 = new DoubleValue(11.0);
    testSheet.setCellAt(coord1, cell1);
    testSheet.setCellAt(coord2, cell2);
    testSheet.setCellAt(coord3, cell3);
    testSheet.setCellAt(coord4, cell4);
    testSheet.setCellAt(coord5, cell5);
    Coord refCoord = new Coord(4, 2);
    testSheet.setCellAt(refCoord, "=(SUB A:B)");
    testSheet.getCellAt(refCoord).evaluateCell();
  }

  //comparison with mc
  @Test(expected = IllegalArgumentException.class)
  public void testCompMultCol() {
    Spreadsheet testSheet = new BasicSpreadsheet();

    initializeTestSheet(testSheet);


    Coord coord1 = new Coord(1, 1);
    Cell cell1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(1, 3);
    Cell cell2 = new StringValue("Hey");//string multiplication tested
    Coord coord3 = new Coord(1, 4);
    Cell cell3 = new DoubleValue(2.0);
    Coord coord4 = new Coord(2, 6);
    Cell cell4 = new DoubleValue(3.0);
    Coord coord5 = new Coord(2, 2);
    Cell cell5 = new DoubleValue(11.0);
    testSheet.setCellAt(coord1, cell1);
    testSheet.setCellAt(coord2, cell2);
    testSheet.setCellAt(coord3, cell3);
    testSheet.setCellAt(coord4, cell4);
    testSheet.setCellAt(coord5, cell5);
    Coord refCoord = new Coord(4, 2);
    testSheet.setCellAt(refCoord, "=(< A:C)");
    testSheet.getCellAt(refCoord).evaluateCell();
  }

  //comparison with sc
  @Test(expected = IllegalArgumentException.class)
  public void testCompOneCol() {
    Spreadsheet testSheet = new BasicSpreadsheet();

    initializeTestSheet(testSheet);


    Coord coord1 = new Coord(1, 1);
    Cell cell1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(1, 3);
    Cell cell2 = new StringValue("Hey");//string multiplication tested
    Coord coord3 = new Coord(1, 4);
    Cell cell3 = new DoubleValue(2.0);
    Coord coord4 = new Coord(2, 6);
    Cell cell4 = new DoubleValue(3.0);
    Coord coord5 = new Coord(2, 2);
    Cell cell5 = new DoubleValue(11.0);
    testSheet.setCellAt(coord1, cell1);
    testSheet.setCellAt(coord2, cell2);
    testSheet.setCellAt(coord3, cell3);
    testSheet.setCellAt(coord4, cell4);
    testSheet.setCellAt(coord5, cell5);
    Coord refCoord = new Coord(4, 2);
    testSheet.setCellAt(refCoord, "=(< A:B)");
    testSheet.getCellAt(refCoord).evaluateCell();
  }

  //sqrt with mc
  @Test(expected = IllegalArgumentException.class)
  public void testSqrtMultCol() {
    Spreadsheet testSheet = new BasicSpreadsheet();

    initializeTestSheet(testSheet);


    Coord coord1 = new Coord(1, 1);
    Cell cell1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(1, 3);
    Cell cell2 = new StringValue("Hey");//string multiplication tested
    Coord coord3 = new Coord(1, 4);
    Cell cell3 = new DoubleValue(2.0);
    Coord coord4 = new Coord(2, 6);
    Cell cell4 = new DoubleValue(3.0);
    Coord coord5 = new Coord(2, 2);
    Cell cell5 = new DoubleValue(11.0);
    testSheet.setCellAt(coord1, cell1);
    testSheet.setCellAt(coord2, cell2);
    testSheet.setCellAt(coord3, cell3);
    testSheet.setCellAt(coord4, cell4);
    testSheet.setCellAt(coord5, cell5);
    Coord refCoord = new Coord(4, 2);
    testSheet.setCellAt(refCoord, "=(SQRT A:C)");
    testSheet.getCellAt(refCoord).evaluateCell();
  }

  //sqrt with sc
  @Test(expected = IllegalArgumentException.class)
  public void testSqrtOneCol() {
    Spreadsheet testSheet = new BasicSpreadsheet();

    initializeTestSheet(testSheet);


    Coord coord1 = new Coord(1, 1);
    Cell cell1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(1, 3);
    Cell cell2 = new StringValue("Hey");//string multiplication tested
    Coord coord3 = new Coord(1, 4);
    Cell cell3 = new DoubleValue(2.0);
    Coord coord4 = new Coord(2, 6);
    Cell cell4 = new DoubleValue(3.0);
    Coord coord5 = new Coord(2, 2);
    Cell cell5 = new DoubleValue(11.0);
    testSheet.setCellAt(coord1, cell1);
    testSheet.setCellAt(coord2, cell2);
    testSheet.setCellAt(coord3, cell3);
    testSheet.setCellAt(coord4, cell4);
    testSheet.setCellAt(coord5, cell5);
    Coord refCoord = new Coord(4, 2);
    testSheet.setCellAt(refCoord, "=(SQRT A:B)");
    testSheet.getCellAt(refCoord).evaluateCell();
  }

  //hamilton with mc
  @Test
  public void testHamMultCol() {
    Spreadsheet testSheet = new BasicSpreadsheet();

    initializeTestSheet(testSheet);


    Coord coord1 = new Coord(1, 1);
    Cell cell1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(1, 3);
    Cell cell2 = new StringValue("Hey");//string multiplication tested
    Coord coord3 = new Coord(1, 4);
    Cell cell3 = new DoubleValue(2.0);
    Coord coord4 = new Coord(2, 6);
    Cell cell4 = new DoubleValue(3.0);
    Coord coord5 = new Coord(2, 2);
    Cell cell5 = new DoubleValue(11.0);
    testSheet.setCellAt(coord1, cell1);
    testSheet.setCellAt(coord2, cell2);
    testSheet.setCellAt(coord3, cell3);
    testSheet.setCellAt(coord4, cell4);
    testSheet.setCellAt(coord5, cell5);
    Coord refCoord = new Coord(4, 2);
    testSheet.setCellAt(refCoord, "=(HAM A:C)");
    testSheet.getCellAt(refCoord).evaluateCell();
    assertEquals(new StringValue("There's a million things we haven't done, just you wait!"),
            testSheet.getCellAt(refCoord).evaluateCell());
  }

  //hamilton with sc
  @Test
  public void testHamOneCol() {
    Spreadsheet testSheet = new BasicSpreadsheet();

    initializeTestSheet(testSheet);


    Coord coord1 = new Coord(1, 1);
    Cell cell1 = new DoubleValue(4.0);
    Coord coord2 = new Coord(1, 3);
    Cell cell2 = new StringValue("Hey");//string multiplication tested
    Coord coord3 = new Coord(1, 4);
    Cell cell3 = new DoubleValue(2.0);
    Coord coord4 = new Coord(2, 6);
    Cell cell4 = new DoubleValue(3.0);
    Coord coord5 = new Coord(2, 2);
    Cell cell5 = new DoubleValue(11.0);
    testSheet.setCellAt(coord1, cell1);
    testSheet.setCellAt(coord2, cell2);
    testSheet.setCellAt(coord3, cell3);
    testSheet.setCellAt(coord4, cell4);
    testSheet.setCellAt(coord5, cell5);
    Coord refCoord = new Coord(4, 2);
    testSheet.setCellAt(refCoord, "=(HAM A:B)");
    assertEquals(new StringValue("There's a million things we haven't done, just you wait!"),
            testSheet.getCellAt(refCoord).evaluateCell());
  }


}
