import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.model.Blank;
import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.Coord;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.StringValue;

import static org.junit.Assert.assertTrue;
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
    Cell addedCell = new DoubleValue(7.0);
    basicSpreadsheet.setCellAt(index,addedCell);

    assertEquals(new DoubleValue(7.0),
            basicSpreadsheet.getCellAt(index));
  }

  // the test for when the given cell has a boolean value
  @Test
  public void getCellBooleanValue(){
    Coord index = new Coord(5,3);
    Cell addedCell = new BooleanValue(true);
    basicSpreadsheet.setCellAt(index,addedCell);

    assertEquals(new BooleanValue(true),
            basicSpreadsheet.getCellAt(index));
  }


  // the test for when the given cell has a string value
  @Test
  public void getCellStringValue(){
    Coord index = new Coord(5,3);
    Cell addedCell = new StringValue("I am not throwing away my shot!");
    basicSpreadsheet.setCellAt(index,addedCell);

    assertEquals(new StringValue("I am not throwing away my shot!"),
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
    Coord index1 = new Coord(1,1);  // section of blank cells
    Coord index2 = new Coord(3, 3);

    List<Cell> expected = new ArrayList<Cell>();

    expected.add(new Blank());  // adding all blanks to the expected list
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());

    List<Cell> actual = basicSpreadsheet.getCellSection(index1,index2);

    boolean isEqual = true;

    for(int i = 0; i < actual.size() && isEqual; i++){
      if(actual.get(i) != expected.get(i)){
        isEqual = false;
      }
    }

    assertTrue(isEqual);
  }

  // the test for when the given cells  have some formulas
  @Test
  public void getSectionFormulas(){
    // setting up the board to be all formulas in given section
    basicSpreadsheet.setCellAt(new Coord(1,1),new Formula("=(SUM 3 4)"));
    basicSpreadsheet.setCellAt(new Coord(2,1), new Formula("=(SUM A1 2)"));
    basicSpreadsheet.setCellAt(new Coord(3,3), new Formula("=(PRODUCT A1 B1)"));


    Coord index1 = new Coord(1,1);  // section of blank cells
    Coord index2 = new Coord(3, 3);

    List<Cell> expected = new ArrayList<>();

    expected.add(new Formula("=(SUM 3 4)"));  // adding all blanks to the expected list
    expected.add(new Formula("=(SUM A1 2)"));
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Formula("=(PRODUCT A1 B1)"));

    List<Cell> actual = basicSpreadsheet.getCellSection(index1,index2);

    boolean isEqual = true;

    assertEquals(expected.size(),actual.size());

    for(int i = 0; i < actual.size() && isEqual; i++){
      if(!actual.contains(expected.get(i))){   // if it does not contain
        isEqual = false;
      }
    }

    assertTrue(isEqual);
  }

  // the test for when the given cells are some values and blanks
  @Test
  public void getSectionValues(){
    // setting up the board to be all formulas in given section
    basicSpreadsheet.setCellAt(new Coord(1,1),new StringValue("hi"));
    basicSpreadsheet.setCellAt(new Coord(2,1), new DoubleValue(9.0));
    basicSpreadsheet.setCellAt(new Coord(3,3), new BooleanValue(true));


    Coord index1 = new Coord(1,1);  // section of blank cells
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

    List<Cell> actual = basicSpreadsheet.getCellSection(index1,index2);

    boolean isEqual = true;

    assertEquals(expected.size(),actual.size());

    for(int i = 0; i < actual.size() && isEqual; i++){
      if(!actual.contains(expected.get(i))){   // if it does not contain
        isEqual = false;
      }
    }

    assertTrue(isEqual);
  }


  // the test for when the given cells have a mix of formulas, values, and empty
  @Test
  public void getSectionAllTypes(){
    // setting up the board to be all formulas in given section
    basicSpreadsheet.setCellAt(new Coord(1,1), new StringValue("hi"));
    basicSpreadsheet.setCellAt(new Coord(2,1), new DoubleValue(9.0));
    basicSpreadsheet.setCellAt(new Coord(3,1), new Formula("=B1"));
    basicSpreadsheet.setCellAt(new Coord(1,2), new Formula("=(SUM B1 C1)"));
    basicSpreadsheet.setCellAt(new Coord(3,3), new BooleanValue(true));


    Coord index1 = new Coord(1,1);  // section of blank cells
    Coord index2 = new Coord(3, 3);

    List<Cell> expected = new ArrayList<>();

    expected.add(new StringValue("hi"));  // adding all blanks to the expected list
    expected.add(new DoubleValue(9.0));
    expected.add(new Formula("=B1"));
    expected.add(new Formula("=(SUM B1 C1)"));
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new BooleanValue(true));

    List<Cell> actual = basicSpreadsheet.getCellSection(index1,index2);

    boolean isEqual = true;

    assertEquals(expected.size(),actual.size());

    for(int i = 0; i < actual.size() && isEqual; i++){
      if(!actual.contains(expected.get(i))){   // if it does not contain
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
  public void getSectionAllTypesColOut(){
    Coord index1 = new Coord(11,1);  // section of blank cells
    Coord index2 = new Coord(11, 4);

    List<Cell> expected = new ArrayList<>();


    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());
    expected.add(new Blank());

    List<Cell> actual = basicSpreadsheet.getCellSection(index1,index2);

    boolean isEqual = true;

    assertEquals(expected.size(),actual.size());

    // repeat as long as they are equal and less than size
    for(int i = 0; i < actual.size() && isEqual; i++){
      if(!actual.contains(expected.get(i))){   // if it does not contain
        isEqual = false;
      }
    }

    assertTrue(isEqual);
  }


  // the test for when the cell at the end is out of range (row and column)
  // some cells are not out of range
  // two columns and 5 rows
  @Test
  public void getSectionSomeOutOfRange(){
    // setting up the board to be all formulas in given section
    basicSpreadsheet.setCellAt(new Coord(10,1), new StringValue("hi"));
    basicSpreadsheet.setCellAt(new Coord(10,2), new DoubleValue(9.0));
    basicSpreadsheet.setCellAt(new Coord(10,3), new Formula("=J2"));
    basicSpreadsheet.setCellAt(new Coord(10,4), new Formula("=(SUM J3 J2)"));
    basicSpreadsheet.setCellAt(new Coord(10,5), new BooleanValue(true));


    Coord index1 = new Coord(10,1);  // section of blank cells
    Coord index2 = new Coord(11, 5);

    List<Cell> expected = new ArrayList<>();

    expected.add(new StringValue("hi"));  // adding all blanks to the expected list
    expected.add(new Blank());
    expected.add(new DoubleValue(9.0));
    expected.add(new Blank());
    expected.add(new Formula("=J2"));
    expected.add(new Blank());
    expected.add(new Formula("=(SUM J3 J2)"));
    expected.add(new Blank());
    expected.add(new BooleanValue(true));
    expected.add(new Blank());
    List<Cell> actual = basicSpreadsheet.getCellSection(index1,index2);

    boolean isEqual = true;

    assertEquals(expected.size(),actual.size());

    for(int i = 0; i < actual.size() && isEqual; i++){
      if(!actual.contains(expected.get(i))){   // if it does not contain
        isEqual = false;
      }
    }

    assertTrue(isEqual);
  }

}
