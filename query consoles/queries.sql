
SELECT FirstName, LastName, (CourseName ||':'|| Year) AS Course, Grade
FROM Grades
    JOIN Students S on S.StudentID = Grades.StudentID
    JOIN Courses C on C.CourseID = Grades.CourseID;


SELECT S.StudentID, FirstName, LastName, (CourseName ||':'|| Year) AS Course,
        CASE
           WHEN Grade IS NULL THEN 'Not graded'
           ELSE Grade
        END AS Grade
FROM Grades
         JOIN Students S on S.StudentID = Grades.StudentID
         JOIN Courses C on C.CourseID = Grades.CourseID
WHERE FirstName LIKE '%' AND LastName LIKE '%';

SELECT S.StudentID, FirstName, LastName, AVG(Grade)
FROM Grades JOIN Students S on S.StudentID = Grades.StudentID
WHERE FirstName LIKE ? AND LastName LIKE ?;

SELECT (CourseName ||':'|| Year) AS Course FROM Courses WHERE CourseID = 1;


SELECT C.CourseID, (FirstName ||' '|| LastName) AS Name, AVG(Grade) AS Average FROM Grades
    JOIN Courses C on Grades.CourseID = C.CourseID
    JOIN Teachers T on T.ID = C.TeacherID
WHERE C.CourseID = 1;
