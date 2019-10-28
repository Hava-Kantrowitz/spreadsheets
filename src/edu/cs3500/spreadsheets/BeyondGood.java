package edu.cs3500.spreadsheets;

import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   * @param args any command-line arguments
   */
  public static void main(String[] args) {
    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it, 
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */



    Parser parser = new Parser();

    String output = parser.parse("(PRODUCT (SUB C1 A1) (SUB C1 A1))").toString();

    System.out.println(output);

    String output2 = parser.parse("3").toString();

    System.out.println(output2);
  }
}
