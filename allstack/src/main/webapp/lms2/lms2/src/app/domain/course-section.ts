import { LessonPage }         from './lesson-page';
import { Course }         from './course';
import { QuizCollection }         from './quiz-collection';
export class CourseSection {
  courseSectionId: number;
  sectionName: string;
  extSectionId: string;
  lessonPages: LessonPage[];
  quizCollections: QuizCollection[];
  course: Course;
}
