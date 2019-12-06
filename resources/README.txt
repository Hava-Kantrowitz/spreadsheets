Homework 8, Code Review

By Victoria Bowen and Hava Kantrowitz

HOMEWORK 8

When our provider's gave us their code, they informed us that they had never gotten their views to
render. They also did not provide us with a controller interface. We asked them to send their
controller interface and to give us an estimate as to when their view would be working. They replied
that they did not have a controller interface and were dropping the class and therefore unable to
create and provide one. The lack of a controller interface or any connection to the model in the
view classes that we were provided meant we were unable to connect the given view to our controller,
and therefore it wasn't possible to implement any features. In order to work around their broken
view and get it to render, we created several decorators that added the basic functionality needed
for rendering. The code we added, and the reasoning behind each, is documented and described in our
Code Critique.

ProviderAdapter: Placed within our model code, this class adapts our model into the model interface
the provider's view is expecting to work with.

ProviderViewExtender: Placed within our view code, this class enables the provider's concrete view
classes to implement their own view interface in order to allow us to interact with it.

ProviderViewRenderer: Placed within our view code, this class enhances the provider's view in order
to allow it to render.

HOMEWORK 7

Our view code is based around a SpreadsheetView interface which was explained in the homework 6
portion of the read me. In this assignment, we added an additional editable view. With the addition
of the editable view we also added methods to our interface and additional view classes which are
explained below.

SpreadsheetEditableView Class: Implements the SV interface for an editable gui view and contains
                               a NoEditTable (from the SpreadsheetTable Class).

AcceptActionListener Class: The action listener for the accept button of the editable view with the
                            purpose of alerting the controller when the accept button is pressed.

CellClickListener Class: The action listener added to the NoEditTable component of the editable view
                         with the purpose of alerting the controller that a cell has been clicked.

ClickedCellRenderer Class: A class to render the highlighted version of a cell.

DeleteListener Class: The key listener for the delete button of the editable view with the
                      purpose of alerting the controller when a cell should be deleted.


LoadListener Class: The action listener for when the load selection is chosen from the file menu
                    with the purpose of alerting the controller that a new file should be loaded.

NoBorderRenderer Class: A subclass of ClickedCellRenderer to remove the highlight from the border
                        of a highlighted cell. This is activated to the previous cell when a new
                        cell is selected.

NoEditTable Class: A class to represent a JTable that does not have the ability to edit the cells
                   by double clicking on them. This is used in the SpreadsheetTable class.

RevertActionListener Class: The action listener for the revert button of the editable view with the
                            purpose of alerting the controller when the cell should be reverted.

SaveListenerClass: The action listener for when the save selection is chosen from the file menu
                   with the purpose of alerting the controller that the data should be saved.

ValidInputListener: The action listener for when the valid input menu is selected from the help
                    menu with the purpose of displaying a message to the user regarding the valid
                    input types.


Our code also contains a controller which is located in the controller package. The controller
portion of the code is centered around the Features interface to abstract the swing components out
of the controller and keep them to the view. The way this works is the view has the listeners and
when a listener that corresponds to a given feature is activated the feature is called in the
controller.

Features Interface: Contains the functionality that the spreadsheet controller must have.

EditableSheetController: Implements the Features interface and implements the given methods to
                         control to control the changes to the model and the view.

With the implementation of cells with errors in the view we had to have some way to represent an
error cell in the model so that the contents would be there but it could not be evaluated.
Therefore, we added an additional cell type to the model called ErrorCell.

ErrorCell class: Implements the cell interface and contains the feature of throwing an error
                 when the cell is used in a function call.


CHANGES BETWEEN HW6 AND HW7

The only changes we made between homework 6 and homework 7, besides the new editable features,
are the additions we made to the ViewInterface and the addition of the ErrorCell. We decided to
add the methods used in the editable view to the view interface so that the view could be used more
interchangeably. The methods are phrased such that they can be applied to any of the three views.
However, the only view that they a needed in is the editable view (besides the render method).

We also updated the testing for the textual view of our model so that we ensure that the functions
and references are shown with their raw values rather than their evaluated values.








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

SpreadsheetRowHeaderTable Class: The one column table for the row headers of the table.

CHANGES BETWEEN HOMEWORKS 5 AND 6

The largest change we made between these homeworks was changing our spreadsheet model from a 2D
array to a HashMap mapping coordinates to cell values. In class discussions and in reviewing prior
feedback, we realized both that a full 2D array was unnecessary considering how many spaces in the
spreadsheet were blank, and that having and needing to handle all those extra cells was incredibly
wasteful. We reconfigured our model to use a hashmap as its base, and this actually made the view
considerably easier.

HOMEWORK 5

Our code is based around a Spreadsheet, which contains Cells. Cells can be Values, Formulas, or
Blank. Formulas can be Values, Functions, or References. Values can be Doubles, Strings, or
Booleans.

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

