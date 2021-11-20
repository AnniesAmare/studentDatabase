import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class StudentView {
    GridPane startview;

    //Buttons
    Button ExitBtn;
    Button getGradesBtn;
    Button getGradeAverageBtn;
    Button getCourseAverageBtn;

    //Labels
    Label studentLbl;
    Label courseLbl;

    //ComboBoxes
    ComboBox<String> studentNamesComB;
    ComboBox<Integer> courseIDComB;

    //Lists
    ObservableList<String> studentNames;
    ObservableList<Integer> courseIDs;

    //Textfields
    TextArea textfield;
    TextArea courseTextfield;
    Text courseName;

    // Constructor. Creates a startview and adds all the elements using CreateView.
    public StudentView(){
        startview=new GridPane();
        CreateView();
    }

    // Creates all the elements for the view
    private void CreateView() {
        startview.setMinSize(300, 200);
        startview.setPadding(new Insets(10, 10, 10, 10));
        startview.setHgap(3);
        startview.setVgap(5);

        //Exit button
        ExitBtn=new Button("Exit");
        startview.add(ExitBtn,20,1);

        //Student overview
        studentLbl = new Label("Student name: ");
        startview.add(studentLbl, 1,1);

        studentNamesComB = new ComboBox<>();
        startview.add(studentNamesComB, 2, 1);

        getGradesBtn = new Button("Get grades");
        startview.add(getGradesBtn,3,1);

        getGradeAverageBtn = new Button("Get student average");
        startview.add(getGradeAverageBtn,4,1);

        textfield = new TextArea();
        startview.add(textfield, 1,2,15,10);

        //Course overview
        courseLbl = new Label("Select Course:");
        startview.add(courseLbl,1,12);

        courseName = new Text("No course selected");
        startview.add(courseName,2,12);

        courseIDComB = new ComboBox<>();
        startview.add(courseIDComB, 3, 12);

        getCourseAverageBtn = new Button("Get course average");
        startview.add(getCourseAverageBtn,4,12);

        courseTextfield = new TextArea();
        startview.add(courseTextfield,1,13,15,10);
    }

    // Updates the ComboBoxes to contain the content of the observable list.
    // This method is used in the StudentController
    public void configure(){
        studentNamesComB.setItems(studentNames);
        studentNamesComB.getSelectionModel().selectFirst();

        courseIDComB.setItems(courseIDs);
    }

    // Method to export the startview, including all child-objects.
    // This method is used in Main
    public Parent asParent(){
        return  startview;
    }
}
