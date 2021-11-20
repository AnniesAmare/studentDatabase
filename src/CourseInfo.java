
// A class to contain the course-information that is sent
// from the StudentModel to the Controller

class CourseInfo{
    Integer courseId = null;
    String teacherName= null;
    Float average = null;
    CourseInfo(Integer id, String fullName, Float average){
        this.courseId = id;
        this.teacherName = fullName;
        this.average = average;
    }
}