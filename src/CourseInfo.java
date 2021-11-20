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