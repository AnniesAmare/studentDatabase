import java.sql.*;
import java.util.ArrayList;

public class StudentModel {
    Connection connection=null;
    String url=null;
    Statement stmt=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    StudentModel (String url) {
        this.url = url;
    }

    public void connectToStudentData() throws SQLException {
        connection = DriverManager.getConnection(url);
    }
    public void closeStudentDataConnection() throws  SQLException{
        if(connection!= null)
            connection.close();
    }
    public void createStatement() throws SQLException{
        this.stmt=connection.createStatement();
    }

    public ArrayList<String> SQLQueryStudentNames() throws SQLException{
        ArrayList<String> students=new ArrayList<>();
        String sql = "SELECT FirstName, LastName FROM Students ORDER BY FirstName;";
        rs=stmt.executeQuery(sql);
        while(rs!=null && rs.next()){
            String firstName=rs.getString(1);
            String lastName=rs.getString(2);
            students.add(firstName+" "+lastName);
        }
        return students;
    }

    public ArrayList<Integer> SQLQueryCourseIDs() throws SQLException{
        ArrayList<Integer> courses=new ArrayList<>();
        String sql = "SELECT CourseID FROM Courses ORDER BY CourseID;";
        rs=stmt.executeQuery(sql);
        while(rs!=null && rs.next()){
            Integer courseID=rs.getInt(1);
            courses.add(courseID);
        }
        return courses;
    }

    public String QueryForCourseName(Integer ID) throws SQLException{
        String sql = "SELECT (CourseName ||':'|| Year) AS Course FROM Courses WHERE CourseID = ?;";
        pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,ID);
        rs=pstmt.executeQuery();
        String courseName=rs.getString("Course");
        return courseName;
    }

    public ArrayList<StudentInfo> QueryForStudentGrades(String fullName) throws SQLException{
        ArrayList<StudentInfo> StudentInfos = new ArrayList<>();
        String sql =
                "SELECT S.StudentID, FirstName, LastName, (CourseName ||':'|| Year) AS Course,\n" +
                        "        CASE\n" +
                        "           WHEN Grade IS NULL THEN 'Not graded'\n" +
                        "           ELSE Grade\n" +
                        "        END AS Grade\n" +
                        "FROM Grades\n" +
                        "         JOIN Students S on S.StudentID = Grades.StudentID\n" +
                        "         JOIN Courses C on C.CourseID = Grades.CourseID\n" +
                        "WHERE FirstName LIKE ? AND LastName LIKE ?;\n";
        pstmt=connection.prepareStatement(sql);
        String[] name = fullName.split(" ");
        pstmt.setString(1,name[0]);
        pstmt.setString(2,name[1]);
        rs=pstmt.executeQuery();
        while(rs!=null && rs.next()){
            Integer id= rs.getInt("StudentID");
            String course=rs.getString("Course");
            String grade=rs.getString("Grade");
            System.out.println("Name: "+fullName+" Course: "+course+" Grade: "+grade);
            StudentInfo student = new StudentInfo(id, fullName, course, grade);
            StudentInfos.add(student);
        }
        return StudentInfos;
    }

    public StudentInfo QueryForStudentAverage(String fullName) throws SQLException{
        String sql =
                "SELECT S.StudentID, FirstName, LastName, AVG(Grade) AS Grade\n" +
                        "FROM Grades JOIN Students S on S.StudentID = Grades.StudentID\n" +
                        "WHERE FirstName LIKE ? AND LastName LIKE ?;";
        pstmt=connection.prepareStatement(sql);
        String[] name = fullName.split(" ");
        pstmt.setString(1,name[0]);
        pstmt.setString(2,name[1]);
        rs=pstmt.executeQuery();
        Integer id= rs.getInt("StudentID");
        String grade=rs.getString("Grade");
        String course = "Grade Average";
        System.out.println("Name: "+fullName+" Course: "+course+" Grade: "+grade);
        StudentInfo student = new StudentInfo(id, fullName, course, grade);
        return student;
    }

    public CourseInfo QueryForCourseAverage(Integer courseID) throws SQLException{
        String sql = "SELECT C.CourseID, (FirstName ||' '|| LastName) AS Name, AVG(Grade) AS Average FROM Grades\n" +
                "    JOIN Courses C on Grades.CourseID = C.CourseID\n" +
                "    JOIN Teachers T on T.ID = C.TeacherID\n" +
                "WHERE C.CourseID = ?;";
        pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1,courseID);
        rs=pstmt.executeQuery();
        Float average=rs.getFloat("Average");
        String teacherName=rs.getString("Name");
        CourseInfo course = new CourseInfo(courseID,teacherName,average);
        return course;
    }
}


