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

    public StudentController(StudentView inputView, StudentModel inputModel) throws SQLException {
        this.view = inputView;
        this.model = inputModel;
        this.model.connectToStudentData();
        this.model.createStatement();

        this.view.ExitBtn.setOnAction(e-> {
            Platform.exit();
            try {
                model.closeStudentDataConnection();
                System.out.println("Closed data connection");
            } catch (SQLException f) {
                System.out.println(f.getMessage());
            }
        });

        //Students
        this.view.studentNames = getStudentNames();
        this.view.getGradesBtn.setOnAction(e-> handlerPrintStudentGrades(view.studentNamesComB.getValue(),view.textfield));
        this.view.getGradeAverageBtn.setOnAction(e-> handlerPrintStudentAverage(view.studentNamesComB.getValue(),view.textfield));

        //Courses
        this.view.courseIDs = getCourseIDs();
        this.view.getCourseAverageBtn.setOnAction(e -> handlerPrintCourseAverage(view.courseIDComB.getValue(), view.courseTextfield));
        this.view.courseIDComB.setOnAction(e ->{
            try{
                this.view.courseName.setText(handlerGetCourseNamebyID(view.courseIDComB.getValue()));
            } catch (SQLException l){
                System.out.println(l.getMessage());
            }
        });
        view.configure();
    }

    public ObservableList<String> getStudentNames() throws SQLException {
        ArrayList<String> students= model.SQLQueryStudentNames();
        ObservableList<String> studentNames= FXCollections.observableArrayList(students);
        return studentNames;
    }

    public ObservableList<Integer> getCourseIDs() throws SQLException {
        ArrayList<Integer> courses = model.SQLQueryCourseIDs();
        ObservableList<Integer> courseIDs= FXCollections.observableArrayList(courses);
        return courseIDs;
    }

    public String handlerGetCourseNamebyID(Integer ID) throws SQLException {
        String courseName = model.QueryForCourseName(ID);
        return courseName;
    }

    public void handlerPrintStudentGrades(String fullname, TextArea txtArea){
        txtArea.clear();
        txtArea.appendText("ID : Student name : Course : Grade \n");
        try{
            ArrayList<StudentInfo> allGrades = model.QueryForStudentGrades(fullname);
            for (StudentInfo student : allGrades) {
                txtArea.appendText(student.id + " : " + student.name + " : " + student.course + " : " + student.grade+"\n");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void handlerPrintStudentAverage(String fullname, TextArea txtArea){
        try{
            StudentInfo student = model.QueryForStudentAverage(fullname);
            txtArea.appendText(student.id + " : " + student.name + " : " + student.course + " : " + student.grade+"\n");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void handlerPrintCourseAverage(Integer courseId, TextArea txtArea){
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
            if (course.average == 0) {
                txtArea.appendText(""+course.courseId+" : "+course.teacherName+" : Course not yet graded");
            } else {
                txtArea.appendText(""+course.courseId+" : "+course.teacherName+" : "+course.average);
            }

        } catch (SQLException d){
            System.out.println(d.getMessage());
        }
    }


}
