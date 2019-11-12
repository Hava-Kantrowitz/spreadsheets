Homework 5, Spreadsheet Model

By Victoria Bowen and Hava Kantrowitz

HOMEWORK 6

Our view code is based around a SpreadsheetView interface, which allows us to implement multiple
methods of viewing. We currently have two views, a GUI view and a textual view.

SpreadsheetView Interface: Contains functionality that all views must have.

SpreadsheetTextualView Class: Implements the SV interface for textual view.

SpreadsheetGraphicsView Class: Implements the SV interface for GUI view.

SpreadsheetTable Class: Implements a table JComponent to model the spreadsheet.

SpecialTableModel Class: Implements specific functionality for a DefaultTableModel.

HorizontalScrollListener Class: Implements a listener for horizontal scrolling.

VerticalScrollListener Class: Implements a listener for vertical scrolling.

CHANGES BETWEEN HOMEWORKS 5 AND 6

The largest change we made between these homeworks was changing our spreadsheet model from a 2D
array to a HashMap mapping coordinates to cell values. In class discussions and in reviewing prior
feedback, we realized both that a full 2D array was unnecessary considering how many spaces in the spreadsheet
were blank, and that having and needing to handle all those extra cells was incredibly wasteful. We
reconfigured our model to use a hashmap as its base, and this actually made the view considerably
easier.

HOMEWORK 5

Our code is based around a Spreadsheet, which contains Cells. Cells can be Values, Formulas, or Blank. Formulas 
can be Values, Functions, or References. Values can be Doubles, Strings, or Booleans. 

Spreadsheet Interface: Contains functionality that all spreadsheets must have. 

BasicSpreadsheet Class: Implements a basic spreadsheet inputted from a file. 

Cell Interface: Contains functionality that all cells must have. 

Blank Class: Implements cells which have not been populated with Values or Formulas. 

Formula Interface: Stub interface which allows the model to keep track of which types of cells
are formulas.

Function Class: Implements cells which perform functions. 

Reference Class: Implements cells which reference other cells. 

Value Abstract Class: Stub abstract class which allows the model to keep
track of which types of formulas are values.

BooleanValue Class: Implements cells which are populated by a boolean. 

DoubleValue Class: Implements cells which are populated by a double. 

StringValue Class: Implements cells which are populated by a string.

