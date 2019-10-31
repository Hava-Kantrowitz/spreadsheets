package edu.cs3500.spreadsheets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import edu.cs3500.spreadsheets.model.BasicSpreadsheet;
import edu.cs3500.spreadsheets.sexp.Parser;
import edu.cs3500.spreadsheets.sexp.SList;
//import edu.cs3500.spreadsheets.sexp.SexpVisitor;

/**
 * The main class for our program.
 */
public class BeyondGood {
  /**
   * The main entry point.
   * @param args any command-line arguments
   */
  public static void main(String[] args) throws FileNotFoundException {
    /*
      TODO: For now, look in the args array to obtain a filename and a cell name,
      - read the file and build a model from it, 
      - evaluate all the cells, and
      - report any errors, or print the evaluated value of the requested cell.
    */



//    Parser parser = new Parser();
//
//    String output = parser.parse("(PRODUCT (SUB (SUM C1 A1) (SUM 4 5)) (SUB C1 A1))").toString();
//
//    System.out.println(output);
//
//    String output2 = parser.parse("3").toString();
//
//    System.out.println(output2);
//
//    SList list = ((SList)parser.parse("(PRODUCT (SUB (SUM C1 A1) (SUM 4 5)) (SUB C1 A1))"));
//    Object element = list.getSexpAt(0);
//    System.out.println(element.toString());
//    element = list.getSexpAt(1);
//    System.out.println(element.toString());
//    System.out.println("Sexp inside the previous element");
//    element = ((SList) list.getSexpAt(1)).getSexpAt(0);
//    System.out.println(element);
//    element = ((SList) list.getSexpAt(1)).getSexpAt(1);
//    System.out.println(element);
//
//
//    System.out.println("\nThis is back to the whole original formula");
//    element = list.getSexpAt(2);
//    System.out.println(element.toString());

//    if(args.length != 4 || !args[0].equals("-in") || !args[3].equals("-eval")){
//
//    }

    BasicSpreadsheet sheet = new BasicSpreadsheet();

    Reader fileName = new FileReader(args[1]);

    sheet.initializeSpreadsheet(fileName);

    sheet.evaluateSheet();


  }

}
