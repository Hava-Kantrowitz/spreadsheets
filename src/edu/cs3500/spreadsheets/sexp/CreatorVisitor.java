package edu.cs3500.spreadsheets.sexp;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.Spreadsheet;
import edu.cs3500.spreadsheets.model.StringValue;

public class CreatorVisitor implements SexpVisitor<Cell> {

  protected Spreadsheet sheet;
  protected String rawContent;

  /**
   * This constructs the visitor to take in the spreadsheet and rawContent.
   * @param sheet the spreadsheet
   * @param rawContent the raw content of the cell
   */
  public CreatorVisitor(Spreadsheet sheet, String rawContent){
    this.sheet = sheet;
    this.rawContent = rawContent;
  }

  @Override
  public Cell visitBoolean(boolean b) {
    Cell output = new BooleanValue(b, rawContent);
    return output;
  }

  @Override
  public Cell visitNumber(double d) {
    Cell output = new DoubleValue(d, rawContent);
    return output;
  }

  @Override
  public Cell visitSymbol(String s) {  // NEED TO FIGURE OUT HOW TO GET REFERENCE
    Cell symbol = new Reference(s, sheet, rawContent);

    return symbol;
  }

  @Override
  public Cell visitString(String s) {
    Cell output = new StringValue(s, rawContent);
    return output;
  }



  @Override
  public Cell visitSList(List<Sexp> l) {
    CreatorVisitor visit = new CreatorVisitor(sheet,rawContent);
    // set the name of the outer function
    ArrayList<Formula> params = new ArrayList<Formula>();
    Cell output = new Function(l.get(0).toString(),params, rawContent);


    // need to figure out how we are representing a formula value
    for(int i = 1; i < l.size(); i++){
      params.add((Formula) l.get(i).accept(visit)); // within formula no null
      System.out.println("In the list visit " + l.get(i).accept(visit).toString());
    }


    return output;
  }
}
