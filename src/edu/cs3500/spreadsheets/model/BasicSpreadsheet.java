package edu.cs3500.spreadsheets.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.cs3500.spreadsheets.sexp.CreatorVisitor;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

public class BasicSpreadsheet implements Spreadsheet {

  private static final int MAXINT = 10000000;
  private ArrayList<ArrayList<Cell>> sheet;
  private int numRows;
  private int numCols;
  private ArrayList<String> references = new ArrayList<>();
  private ArrayList<String> badReferences = new ArrayList<>();

  /**
   * This is the blank constructor for a basic spreadsheet. Constructs a 10 by 10 spreadsheet of
   * blank cells.
   */
  public BasicSpreadsheet() {
  }

  @Override
  public void initializeSpreadsheet(Readable fileName) {
    sheet = new ArrayList<ArrayList<Cell>>();   // setting the values back to zero
    numRows = 0;
    numCols = 0;
    WorksheetBuild builder = new WorksheetBuild(this);  // calling the builder to actually initialize
    WorksheetReader.read(builder,fileName);  // reading from the file and passing it in
  }


  @Override
  public Cell getCellAt(Coord coord) {
    int givenCol = coord.col - 1;
    int givenRow = coord.row - 1;

    // checking if it is greater than the number of columns (1 based index)
    // expand the board to fit
    if (givenCol >= numCols || givenRow >= numRows) {
      expandSheet(givenCol, givenRow);
    }

    return sheet.get(givenRow).get(givenCol);
  }

  @Override
  public void setCellAt(Coord coord, Cell cellVal) {
    int givenCol = coord.col - 1;  // adjust for the 1 based indexing
    int givenRow = coord.row - 1;

    // checking if it is greater than the number of columns (1 based index)
    // expand the board to fit
    if (givenCol >= numCols || givenRow >= numRows) {
      expandSheet(givenCol, givenRow);
    }
    // get the given row then set the column of that row
    sheet.get(givenRow).set(givenCol, cellVal);

    System.out.println("Cell at " +coord.toString() + " is " + cellVal.toString());

  }

  @Override
  public void setCellAt(Coord coord, String rawContents) {
    String contentCopy = new String(rawContents);
    char[] arrayForm = rawContents.toCharArray();

    // checking if it is a formula to get only the s expression
    // NEED TO DEAL WITH IF IT HAS EQUALS BUT IT IS A VALUE
    if(arrayForm[0] == '='){
      rawContents = rawContents.substring(1,rawContents.length());
    }
    SexpVisitor visit = new CreatorVisitor(this);
    Cell addedCell = (Cell) Parser.parse(rawContents).accept(visit,contentCopy);

    setCellAt(coord, addedCell);

  }

  @Override
  public ArrayList<Cell> getCellSection(Coord coord, Coord cord) {
    int givenCol1 = coord.col - 1;
    int givenRow1 = coord.row - 1;
    int givenCol2 = cord.col - 1;
    int givenRow2 = cord.row - 1;

    if (givenCol2 < givenCol1 || givenRow2 < givenRow1) {
      throw new IllegalArgumentException("Cannot find cell range");
    }

    // checking if it is greater than the number of columns (1 based index) or rows for the
    // second input
    // expand the board to fit
    if (givenCol2 >= numCols || givenRow2 >= numRows) {
      expandSheet(givenCol2, givenRow2);
    }

    ArrayList multRows = new ArrayList<>();

    for (int i = givenRow1; i <= givenRow2; i++) {
      for (int j = givenCol1; j <= givenCol2; j++) {
        multRows.add(sheet.get(i).get(j));
      }

    }



    return multRows;
  }


  @Override
  public void evaluateSheet() throws IllegalArgumentException{

    badReferences.clear();

    for (int i = 1; i < numCols; i++) {
      for (int j = 1; j < numRows; j++) {
        Coord currCor = new Coord(i, j);
        testDirectRef(currCor);
        testDirectFun(currCor);
        if (hasIndirectRef(currCor)) {
          badReferences.add(currCor.toString());
          throw new IllegalArgumentException("There is a self reference in cell " + currCor.toString());
        }
        getCellAt(currCor).evaluateCell();
      }
    }

    if (badReferences.size() != 0) {
      throw new IllegalArgumentException("There is a self reference in cell a cell");
    }

  }

  /**
   * Helper to determine if the cell contains a self reference.
   * @param currCor the coordinate of the cell
   * @return true if it contains a self reference, false otherwise
   */
  private boolean hasIndirectRef(Coord currCor) {
    if (getCellAt(currCor).isRef()) {
      Reference ref = (Reference) getCellAt(currCor);
      references.add(ref.symbol);
      if (ref.isFunction()) {
        return testAsFunction(ref);
      }
      else if (ref.getReferredCell().isRef()) {
        Reference refIn = (Reference) ref.getReferredCell();
        references.add(refIn.symbol);
        Coord newCoord = new Coord(refIn.colOver, refIn.rowOver);
        return hasIndirectRef(newCoord);
      }
      else {
        Set<String> noDuplicates = new HashSet<>(references);
        return (references.size() != noDuplicates.size());
      }
    }
    return false;
  }

  private boolean testAsFunction(Reference ref) {
    Function funIn = (Function) ref.getReferredCell();
    int numRefs = 0;
    int numFuns = 0;
    ArrayList<Reference> actualRefs = new ArrayList<>();
    ArrayList<Function> actualFuns = new ArrayList<>();
    for (Formula f : funIn.funParams) {
      if (f.isRef()) {
        numRefs++;
        Reference nextRef = (Reference) f;
        references.add(nextRef.symbol);
        actualRefs.add(nextRef);
      }
      if (f.isFunction()) {
        numFuns++;
        assert f instanceof Function;
        Function nextFun = (Function) f;
        actualFuns.add(nextFun);
      }
    }

    if (numRefs == 0 && numFuns == 0) {
      Set<String> noDuplicates = new HashSet<>(references);
      return !(references.size() < noDuplicates.size());
    }

    else if (numRefs != 0 && numFuns == 0){
      for (Reference r : actualRefs) {
        if (hasIndirectRef(new Coord(r.colOver, r.rowOver))) {
          return true;
        }
      }

      for (Function f : actualFuns) {
        for (Formula formula : f.funParams) {
          if (formula.isFunction()) {
            Function nextFun = (Function) formula;
            for (Formula nextParams : nextFun.funParams) {
              if (nextParams.isRef()) {
                Reference nextRef = (Reference) nextParams;
                if (hasIndirectRef(new Coord(nextRef.colOver, nextRef.rowOver))) {
                  return true;
                }
              }
            }
          }
          else if (formula.isRef()) {
            Reference nextRef = (Reference) formula;
            if (hasIndirectRef(new Coord(nextRef.colOver, nextRef.rowOver))) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  private void testDirectFun(Coord currCor) {
    if (getCellAt(currCor).isFunction()) {
      Function fun = (Function) getCellAt(currCor);
      List<Formula> formulas = fun.funParams;
      ArrayList<String> refSymbols = new ArrayList<>();
      for (Formula f : formulas) {
        if (f.isRef()) {
          Reference newRef = (Reference) f;
          refSymbols.add(newRef.symbol);
        }
      }

      for (String s : refSymbols) {
        if (s.equals(currCor.toString())) {
          throw new IllegalArgumentException("Cell " + currCor.toString()
                  + " references itself directly.");
        }
      }
    }
  }

  private void testDirectRef(Coord currCor) {
    if (getCellAt(currCor).isRef()) {
      Reference ref = (Reference) getCellAt(currCor);
      String refSymbol = ref.symbol;
      if (currCor.toString().equals(refSymbol)) {
        throw new IllegalArgumentException("Cell " + currCor.toString()
                + " references itself directly.");
      }
    }
  }

  @Override
  public void evaluateCellAt(Coord coord) throws IllegalArgumentException {
    getCellAt(coord).evaluateCell();
  }



  /**
   * This is the method to expand the spreadsheet when the coordinates selected are out of bounds.
   * If the column is out of bounds then expand column wise, same with row. If both are out of
   * bounds expand in both directions. When new cells are added they should be blank.
   *
   * @param inputCol the column that was input
   * @param inputRow the row that was input
   */
  private void expandSheet(int inputCol, int inputRow) {

    if (inputCol >= MAXINT) {
      while (sheet.size() <= MAXINT) {
        sheet.add(new ArrayList<Cell>());
      }
    }

    if (inputRow >= MAXINT) {
      for (int i = 0; i <= MAXINT; i++) {
        while (sheet.get(i).size() <= inputRow) {
          sheet.get(i).add(new Blank());
        }
      }
    } else {
      sheet.ensureCapacity(inputRow);

      while (sheet.size() <= inputRow) {
        sheet.add(new ArrayList<Cell>());
      }

      for (int i = 0; i <= inputRow; i++) {
        sheet.get(i).ensureCapacity(inputCol);
        while (sheet.get(i).size() <= inputCol) {
          sheet.get(i).add(new Blank());
        }
      }
    }

    numRows = sheet.size();        // ADDED CHANGING THE NUM ROWS AND NUM COLS
    numCols = sheet.get(0).size();

  }


}
