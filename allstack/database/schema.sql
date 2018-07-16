--Run the script to setup new DB
create database i80;
use i80;

/*create table Leads(
	leadId int NOT NULL AUTO_INCREMENT, 
	courseName varchar(200), 
	leadName varchar(200),
	leadEmail varchar(120),
	leadMobile varchar(20),	
	PRIMARY KEY (leadId)
) AUTO_INCREMENT=1; */

--Actual; but not in use as of now
/*create table Users(
	userId int NOT NULL AUTO_INCREMENT, 
	userHandle varchar(256),
	courseId int, 
	fullName varchar(200),
	email varchar(120),
	mobile varchar(20),	
	PRIMARY KEY (userId),
	FOREIGN KEY (courseId)
    REFERENCES Courses(courseId)
    ON UPDATE CASCADE
    ON DELETE SET NULL
) AUTO_INCREMENT=1; */

--In Use
create table Users(
	userId int NOT NULL AUTO_INCREMENT, 
	userHandle varchar(256),
	courseId int, 
	fullName varchar(200),
	email varchar(120),
	mobile varchar(20),	
	PRIMARY KEY (userId)
) AUTO_INCREMENT=1; 
--insert into Users(userHandle,fullName,email,mobile,courseId) values("mkpanda","Manas Panda","manas.k.panda@gmail.com","9886707787",1);

--Not used currently
--e.g. "Complete Java Developer Program" will contain, Java, Advanced Java, SQL, Front-end programming
create table CourseBundles(
	courseBundleId int NOT NULL AUTO_INCREMENT, 
	courseBundleName varchar(400),
	PRIMARY KEY (courseBundleId)
)AUTO_INCREMENT=1; 


--e.g. Java, Python etc
create table Courses(
	courseId int NOT NULL AUTO_INCREMENT, 
	extCourseId varchar(100) UNIQUE,
	courseName varchar(400),
	descriptionHTML text,
	PRIMARY KEY (courseId)
)AUTO_INCREMENT=1; 
--insert into Courses(courseName,extCourseId) values("Java","java");
--insert into Courses(courseName,extCourseId) values("SQL","sql");
--insert into Courses(courseName,extCourseId) values("Advanced Java","adv-java");

-- OOP, Inheritence, Polymorphism etc
create table CourseSections(
	courseSectionId int NOT NULL AUTO_INCREMENT, 
	extSectionId varchar(100) UNIQUE,
	sectionName varchar(400),
	courseId int NOT NULL,
	descriptionHTML text,
	PRIMARY KEY (courseSectionId),
	FOREIGN KEY (courseId)
    REFERENCES Courses(courseId)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)AUTO_INCREMENT=1;
--insert into CourseSections(extSectionId,sectionName,courseId) values('java-intro','Introduction to Java',1);
--insert into CourseSections(extSectionId,sectionName,courseId) values('java-basics','Language Basics',1);
--insert into CourseSections(extSectionId,sectionName,courseId) values('java-oop','Object Oriented Programming',1);
--insert into CourseSections(extSectionId,sectionName,courseId) values('java-exceptions','Exception Handling',1);
--insert into CourseSections(extSectionId,sectionName,courseId) values('sql-intro','Introduction to SQL',2);
--insert into CourseSections(extSectionId,sectionName,courseId) values('sql-basic-join','Basic joins in SQL',2);

-- contentType is 0 or 1 - 0 is content and 1 is quiz 
create table LessonPages(
	pageId int NOT NULL AUTO_INCREMENT, 
	extPageId varchar(100) UNIQUE,
	pageName varchar(400),
	courseSectionId int NOT NULL,
	quizCollectionId int,
	contentType int,
	contentHTML text,
	PRIMARY KEY (pageId),
	FOREIGN KEY(courseSectionId)
    REFERENCES CourseSections(courseSectionId)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY(quizCollectionId)
    REFERENCES QuizCollections(quizCollectionId)
    ON UPDATE RESTRICT
    ON DELETE RESTRICT
)AUTO_INCREMENT=1; 
-- insert into LessonPages(extPageId,pageName,courseSectionId) values('java-oop-page-quiz1','Object Oriented Design - Quiz1', 3);

--This will have a collection of questions of various types
create table QuizCollections(
    quizCollectionId int NOT NULL AUTO_INCREMENT, 
    extQuizCollectionId varchar(100) UNIQUE,
	quizCollectionName varchar(400) NOT NULL,
	courseSectionId int,
	isDefault int  NOT NULL,
	PRIMARY KEY (quizCollectionId),
	FOREIGN KEY(courseSectionId)
    REFERENCES CourseSections(courseSectionId)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
--Blank names will be automatically shown as "Quiz 1" in the front-end
--insert into QuizCollections(extQuizCollectionId,quizCollectionName,courseSectionId,isDefault) values('java-oop-defaultquizcol','OOP Default Quiz',3,1);
--insert into QuizCollections(extQuizCollectionId,quizCollectionName,courseSectionId) values('java-oop-quizcol1','OOP Quiz - 1',3);
--insert into QuizCollections(extQuizCollectionId,quizCollectionName,courseSectionId) values('java-oop-quizcol2','OOP Quiz - 2',3);

create table QuestionType(
	questionTypeId int NOT NULL,
	questionTypeName varchar(100), -- Can be Single choice, Multiple Choice, Code Output
	PRIMARY KEY (questionTypeId)
);
--insert into QuestionType(questionTypeId,questionTypeName) values(1,'Single Choice');
--insert into QuestionType(questionTypeId,questionTypeName) values(2,'Multiple Choice');
--insert into QuestionType(questionTypeId,questionTypeName) values(3,'Code Output');
--insert into QuestionType(questionTypeId,questionTypeName) values(4,'Fill The Blanks');

create table Questions(
	questionId int NOT NULL AUTO_INCREMENT,
	extQuestionId varchar(100) UNIQUE,
	questionType int NOT NULL,
	quizCollectionId int NOT NULL,
	quizQuestionHTML text,
	choice1HTML text,
	choice2HTML text,
	choice3HTML text,
	choice4HTML text,
	isChoice1Correct int,
	isChoice2Correct int,
	isChoice3Correct int,
	isChoice4Correct int,
	answerHTML text,
	answerHintHTML text,
	answerExplanationHTML text,
	pointsForQuestion int,
	FOREIGN KEY(quizCollectionId)
	    REFERENCES QuizCollections(quizCollectionId)
	    ON UPDATE CASCADE
	    ON DELETE CASCADE,
    PRIMARY KEY (questionId)
);
--insert into Questions(extQuestionId,questionTypeId,quizCollectionId,quizQuestionHTML,choice1HTML,choice2HTML,choice3HTML,choice4HTML,isChoice1Correct,isChoice2Correct,isChoice3Correct,isChoice4Correct,answerHTML, answerHintHTML,answerExplanationHTML) 
--               values('java-oop-quizcol1-q1',2,6,'hello Question','world','happy','holidays','last',1,0,0,0,NULL,'','');
