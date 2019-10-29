package edu.cs3500.spreadsheets.sexp;

import java.util.ArrayList;
import java.util.List;

import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.DoubleValue;
import edu.cs3500.spreadsheets.model.Formula;
import edu.cs3500.spreadsheets.model.Function;
import edu.cs3500.spreadsheets.model.Reference;
import edu.cs3500.spreadsheets.model.StringValue;
import edu.cs3500.spreadsheets.model.Value;

public class BasicSexpVisitor implements SexpVisitor {

  @Override
  public Cell visitBoolean(boolean b) {
    Cell output = new BooleanValue(b);
    return output;
  }

  @Override
  public Cell visitNumber(double d) {
    Cell output = new DoubleValue(d);
    return output;
  }

  @Override
  public Cell visitSymbol(String s) {
    Cell symbol = new Reference(s);

    return symbol;
  }

  @Override
  public Cell visitString(String s) {
    Cell output = new StringValue(s);
    return output;
  }



  @Override
  public Cell visitSList(List l) {
    SList inputList = (SList) l;
    BasicSexpVisitor visit = new BasicSexpVisitor();
    // set the name of the outer function
    ArrayList<Formula> params = new ArrayList<Formula>();
    Cell output = new Function(l.get(0).toString(),params);


    // need to figure out how we are representing a formula value
    for(int i = 1; i < l.size(); i++){
      params.add((Formula) inputList.getSexpAt(i).accept(visit));
    }
    return output;
  }
}
