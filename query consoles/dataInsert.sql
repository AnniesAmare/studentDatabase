INSERT INTO Students (StudentID, FirstName, LastName, City)
                                   VALUES (1, 'Aisha', 'Lincoln', 'Nykøbing F'),
                                          (2, 'Anya', 'Nielsen','Nykøbing F'),
                                          (3, 'Alfred', 'Jensen','Karlskrona'),
                                          (4, 'Albert', 'Antonsen','Sorø'),
                                          (5, 'Berta', 'Bertelsen','Billund'),
                                          (6, 'Eske', 'Eriksen','Eskildstrup'),
                                          (7, 'Egon', 'Damdrup','Roskilde'),
                                          (8, 'Olaf', 'Olesen', 'Odense'),
                                          (9, 'Salma', 'Simonsen','Stockholm'),
                                          (10, 'Theis','Thomasen','Tølløse'),
                                          (11, 'Janet', 'Jensen', 'Jyllinge');

INSERT INTO Grades (StudentID, CourseID, Grade)
                                   VALUES (1, 1, 12),
                                          (1, 3, 10),
                                          (2, 2, null),
                                          (2, 3, 12),
                                          (3, 1, 7),
                                          (3, 3, 10),
                                          (4, 1, 10),
                                          (4, 3, 7),
                                          (5, 2, null),
                                          (5, 3, 2),
                                          (6, 2, null),
                                          (6, 3, 10),
                                          (7, 2, null),
                                          (7, 3, 2),
                                          (8, 1, 4),
                                          (8, 3, 12),
                                          (9, 2, null),
                                          (9, 3, 12),
                                          (10, 1, 12),
                                          (10, 3, 12),
                                          (11, 2, null),
                                          (11, 3, 7);


INSERT INTO Courses (CourseID, CourseName, Year, Season, TeacherID)
                                   VALUES (1, 'SD',2020,'autumn',1),
                                          (2, 'SD',2021,'autumn',1),
                                          (3, 'ES1',2020,'autumn',2);

INSERT INTO Teachers (ID, FirstName, LastName, City)
                                   VALUES (1, 'Line', 'Reinhardt','Nykøbing F'),
                                          (2, 'Bo', 'Holst', 'Roskilde');

INSERT INTO Cities (City, ZipCode) VALUES ('Nykøbing F', 4800),
                                          ('Sorø', 4180),
                                          ('Billund', 7190),
                                          ('Eskildstrup', 4863),
                                          ('Roskilde', 4000),
                                          ('Odense', 5000),
                                          ('Tølløse', 4340),
                                          ('Jyllinge', 4040);

INSERT INTO Cities (city, zipcode, country)
                                   VALUES ('Stockholm', null, 'Sweden'),
                                          ('Karlskrona', null, 'Sweden');