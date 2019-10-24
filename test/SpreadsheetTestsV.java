import org.junit.Test;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.Value;

import static org.junit.Assert.assertEquals;

/**
 * This is the testing class for the spreadsheet as a whole (Victoria's half).
 */
public class SpreadsheetTestsV {

  private Spreadsheet basicSpreadsheet = new BasicSpreadsheet();


  // THESE ARE THE TESTS FOR GET CELL AT

  // the test for when the given cell has a formula
  @Test
  public void getCellFormula(){
    Coord index = new Coord(1,3);
    Cell addedCell = new Formula("=(PRODUCT (SUB C1 A1) (SUB C1 A1))");
    basicSpreadsheet.setCellAt(index,addedCell);

    assertEquals(new Formula("=(PRODUCT (SUB C1 A1) (SUB C1 A1))"),
            basicSpreadsheet.getCellAt(index));
  }


  // the test for when the given cell has a numeric value
  @Test
  public void getCellNumericValue(){
    Coord index = new Coord(1,3);
    Cell addedCell = new Value(7);
    basicSpreadsheet.setCellAt(index,addedCell);

    assertEquals(new Value(7),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the given cell has a boolean value
  @Test
  public void getCellBooleanValue(){
    Coord index = new Coord(5,3);
    Cell addedCell = new Value(true);
    basicSpreadsheet.setCellAt(index,addedCell);

    assertEquals(new Value(true),
            basicSpreadsheet.getCellAt(index));
  }


  // the test for when the given cell has a string value
  @Test
  public void getCellStringValue(){
    Coord index = new Coord(5,3);
    Cell addedCell = new Value("I am not throwing away my shot!");
    basicSpreadsheet.setCellAt(index,addedCell);

    assertEquals(new Value("I am not throwing away my shot!"),
            basicSpreadsheet.getCellAt(index));
  }


  // the test for when the given cell is blank
  @Test
  public void getCellBlankValue(){
    Coord index = new Coord(5,3);
    Cell addedCell = new Blank();
    basicSpreadsheet.setCellAt(index,addedCell);

    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the given cell is out of bounds on the column side
  // new blank cells added so it should return a blank cell
  @Test
  public void getOutColumnSide(){
    Coord index = new Coord(15,3);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the given cell is out of bounds on the row side
  @Test
  public void getOutRowSide(){
    Coord index = new Coord(5,10);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }


  // the test for when the given cell is out of bounds on the row and column
  @Test
  public void getOutBothRowCol(){
    Coord index = new Coord(15,20);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the row is over double the current row size
  @Test
  public void getOutRowOverDouble(){
    Coord index = new Coord(3,50);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the column is over double the current column size
  @Test
  public void getOutColumnOverDouble(){
    Coord index = new Coord(70,8);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the column and the row are over double the current column and row size
  @Test
  public void getOutColumnRowOverDouble(){
    Coord index = new Coord(40,50);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the given cell is out of bounds (and doubling would exceed max possible int)
  // on the row input (the doubling will go over but the number itself will still be valid
  @Test
  public void getOutRowOverMaxIntRow(){
    Coord index = new Coord(3,2147483647);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the given cell is out of bounds (and doubling would exceed max possible int)
  // on the column input
  @Test
  public void getOutColumnMaxIntCol(){
    Coord index = new Coord(2147483647,5);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }


  // the test for when the given cell is out of bounds (and doubling would exceed max possible int)
  // on the row and column inputs
  @Test
  public void getOutColumnMaxIntColRow(){
    Coord index = new Coord(2147483647,2147483647);
    assertEquals(new Blank(),
            basicSpreadsheet.getCellAt(index));
  }

  // THESE ARE THE TESTS FOR GET CELL SECTION

  // the test for when the given cells are all empty (in a rectangle formation)
  @Test
  public void getSectionBlank(){
    Coord index1 = new Coord(1,1);
    Coord index2 = new Coord(3, 5);

    //assertEquals(new Blank(),
    //        basicSpreadsheet.getCellSection(index1, index2));
  }

  // the test for when the given cells are all formulas


  // the test for when the given cells are all values


  // the test for when the given cells have a mix of formulas, values, and empty


  // the test for when the first given cell is out of range (column)


  // the test for when a cell in the middle is out of range  (row)


  // the test for when the cell at the end is out of range (row and column)


}
