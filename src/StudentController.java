import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {
    StudentView view;
    StudentModel model;

    // Constructor, creates the controller by combining the StudentView and the StudentModel
    public StudentController(StudentView inputView, StudentModel inputModel) throws SQLException {
        // Defines the view
        this.view = inputView;

        // Defines the model, and establishes a database-connection
        this.model = inputModel;
        this.model.connectToStudentData();
        this.model.createStatement();

        // Defines functionality for the exit button, so it closes the SQL connection on exit
        this.view.ExitBtn.setOnAction(e-> {
            Platform.exit();
            try {
                model.closeStudentDataConnection();
                System.out.println("Closed data connection");
            } catch (SQLException exitExcpt) {
                System.out.println(exitExcpt.getMessage());
            }
        });

        //Students
        this.view.studentNames = getStudentNames();
        this.view.getGradesBtn.setOnAction(e->
            handlerPrintStudentGrades(view.studentNamesComB.getValue(),view.textfield)
        );
        this.view.getGradeAverageBtn.setOnAction(e->
            handlerPrintStudentAverage(view.studentNamesComB.getValue(),view.textfield)
        );

        //Courses
        this.view.courseIDs = getCourseIDs();
        this.view.getCourseAverageBtn.setOnAction(e ->
            handlerPrintCourseAverage(view.courseIDComB.getValue(), view.courseTextfield)
        );
        this.view.courseIDComB.setOnAction(e -> {
            try{
                this.view.courseName.setText(
                        handlerGetCourseNamebyID(view.courseIDComB.getValue())
                );
            } catch (SQLException courseIDExcpt){
                System.out.println(courseIDExcpt.getMessage());
            }
        });

        //Configues the view, updating the ComboBoxes to contain the updated Observable Lists
        view.configure();
    }

    // Method to input student names into an observable list and return that list
    public ObservableList<String> getStudentNames() throws SQLException {
        ArrayList<String> students= model.SQLQueryStudentNames();
        ObservableList<String> studentNames= FXCollections.observableArrayList(students);
        return studentNames;
    }

    // Method to input course ids into an observable list and return that list
    public ObservableList<Integer> getCourseIDs() throws SQLException {
        ArrayList<Integer> courses = model.SQLQueryCourseIDs();
        ObservableList<Integer> courseIDs= FXCollections.observableArrayList(courses);
        return courseIDs;
    }

    // Method to get a course name by ID
    public String handlerGetCourseNamebyID(Integer ID) throws SQLException {
        String courseName = model.QueryForCourseName(ID);
        return courseName;
    }

    // Method to print student grades
    // Queries the database using student name and appends the result to the text-area
    public void handlerPrintStudentGrades(String fullname, TextArea txtArea){
        txtArea.clear();
        txtArea.appendText("ID : Student name : Course : Grade \n");
        try{
            ArrayList<StudentInfo> allGrades = model.QueryForStudentGrades(fullname);
            for (StudentInfo student : allGrades) {
                txtArea.appendText(student.id + " : " + student.name + " : " + student.course + " : " + student.grade+"\n");
            }
        }catch (SQLException printStudentGradesExcpt){
            System.out.println(printStudentGradesExcpt.getMessage());
        }
    }

    // Method to print student average
    // Queries the database using student name and appends the result to the text-area
    public void handlerPrintStudentAverage(String fullname, TextArea txtArea){
        try{
            StudentInfo student = model.QueryForStudentAverage(fullname);
            txtArea.appendText(student.id + " : " + student.name + " : " + student.course + " : " + student.grade+"\n");
        }catch (SQLException printStudentAVGExcpt){
            System.out.println(printStudentAVGExcpt.getMessage());
        }
    }

    // Method to print course average
    // Queries the database using course id and appends the result to the text-area
    public void handlerPrintCourseAverage(Integer courseId, TextArea txtArea){
        // If statement to handle a null-input from courseID
        // This happens when button is clicked, but no courseID has been selected
        if (courseId == null) {
            System.out.println("No value input");
            txtArea.clear();
            txtArea.appendText("Please select a course");
            return;
        }
        try{
            CourseInfo course = model.QueryForCourseAverage(courseId);
            txtArea.clear();
            txtArea.appendText("Course id : Teacher : Student Average\n");
            // If statement to handle null-input from the query
            // (in case there are no grades in the course / or only the grade 00)
            if (course.average == 0) {
                txtArea.appendText(""+course.courseId+" : "+course.teacherName+" : Course not yet graded");
            } else {
                txtArea.appendText(""+course.courseId+" : "+course.teacherName+" : "+course.average);
            }
        } catch (SQLException printCourseAVGExcpt){
            System.out.println(printCourseAVGExcpt.getMessage());
        }
    }


}
