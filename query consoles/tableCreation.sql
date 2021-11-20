DROP TABLE IF EXISTS Students;
CREATE TABLE IF NOT EXISTS Students (
                            "StudentID"	INTEGER,
                            "FirstName"	TEXT,
                            "LastName"	TEXT,
                            "City"	TEXT,
                            FOREIGN KEY("City") REFERENCES Cities("City"),
                            PRIMARY KEY("StudentID")
);

DROP TABLE IF EXISTS Teachers;
CREATE TABLE IF NOT EXISTS Teachers (
                            "ID"	INTEGER,
                            "FirstName"	TEXT,
                            "LastName"	TEXT,
                            "City"	TEXT,
                            FOREIGN KEY("City") REFERENCES Cities("City"),
                            PRIMARY KEY("ID")
);

DROP TABLE IF EXISTS Cities;
CREATE TABLE IF NOT EXISTS Cities (
                            "City"      TEXT,
                            "ZipCode"	INTEGER,
                            "Country"	TEXT DEFAULT 'Denmark',
                            PRIMARY KEY("City")
);

DROP TABLE IF EXISTS Courses;
CREATE TABLE IF NOT EXISTS Courses (
                           "CourseID"	INTEGER,
                           "CourseName"	TEXT,
                           "Year"	INTEGER,
                           "Season"	TEXT,
                           "TeacherID"	INTEGER,
                           FOREIGN KEY("TeacherID") REFERENCES Teachers("ID"),
                           PRIMARY KEY("CourseID")
);

DROP TABLE IF EXISTS Grades;
CREATE TABLE IF NOT EXISTS Grades (
                          "StudentID"	INTEGER,
                          "CourseID"	INTEGER,
                          "Grade"	INTEGER,
                          FOREIGN KEY("StudentID") REFERENCES Students("StudentID") ON DELETE CASCADE,
                          FOREIGN KEY("CourseID") REFERENCES Courses("CourseID") ON DELETE CASCADE,
                          PRIMARY KEY("StudentID","CourseID")
);