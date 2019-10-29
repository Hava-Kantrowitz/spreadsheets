package edu.cs3500.spreadsheets.sexp;

import java.util.List;

import edu.cs3500.spreadsheets.model.BooleanValue;
import edu.cs3500.spreadsheets.model.Cell;
import edu.cs3500.spreadsheets.model.DoubleValue;
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

  // Not sure what symbol is used for right now
  //
  @Override
  public Cell visitSymbol(String s) {
    //Cell symbol = new Reference();
    // need to figure out if it is an operation or cell reference
    return null;
  }

  @Override
  public Cell visitString(String s) {
    Cell output = new StringValue(s);
    return output;
  }



  @Override
  public Cell visitSList(List l) {
    // need to figure out how we are representing a formula value
    return null;
  }
}
