import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

public class Main extends Application {
    public void start(Stage primaryStage){
        //Variable and object creation
        String url="jdbc:sqlite:C:\\Users\\annet\\OneDrive\\studentDatabase\\studentData.db";
        StudentView view=new StudentView(); //Creates the view
        StudentModel model = new StudentModel(url); //Creates the SQL student model
        StudentController control = null;
        try {
            //Creates the controller with connection to the view and the model
            control = new StudentController(view, model);

            //Configuring the primary stage
            primaryStage.setTitle("Student database");
            primaryStage.setScene(new Scene(view.asParent(),600,475));
            primaryStage.show();
        } catch (SQLException mainException){
            System.out.println(mainException.getMessage());
        }
        // Method that closes SQL-connection on a forced exit of the view.
        primaryStage.setOnCloseRequest(e->{
            try {
                model.closeStudentDataConnection();
                System.out.println("Closed data connection");
            } catch (SQLException exitExcpt){
                System.out.println(exitExcpt.getMessage());
            }
        });
    }
    public static void main(String[] args){
        launch(args);
    }
}
