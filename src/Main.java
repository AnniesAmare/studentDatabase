import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
    public void start(Stage primaryStage){
        String url="jdbc:sqlite:C:\\Users\\annet\\OneDrive\\studentDatabase\\studentData.db";
        StudentView view=new StudentView();
        StudentModel model = new StudentModel(url);
        StudentController control = null;
        try {
            control = new StudentController(view, model);
            primaryStage.setTitle("Student database");
            primaryStage.setScene(new Scene(view.asParent(),600,475));
            primaryStage.show();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        primaryStage.setOnCloseRequest(e->{
            try {
                model.closeStudentDataConnection();
                System.out.println("Closed data connection");
            } catch (SQLException a){
                System.out.println(a.getMessage());
            }
        });
    }

    public static void main(String[] args){
        launch(args);
    }
}
