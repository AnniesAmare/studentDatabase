
// A class to contain the student-information that is sent
// from the StudentModel to the Controller

class StudentInfo{
    Integer id = null;
    String name = null;
    String course = null;
    String grade = null;

    StudentInfo(Integer id, String fullname, String course, String grade){
        this.id = id;
        this.name = fullname;
        this.course = course;
        this.grade = grade;
    }

}
