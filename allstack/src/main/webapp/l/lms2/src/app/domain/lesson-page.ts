import { CourseSection }         from './course-section';
import { QuizCollection }         from './quiz-collection';
export class LessonPage {
  pageId: number;
  pageName: string;
  extPageId: string;
  contentType: number;
  contentHTML: string;
  courseSection: CourseSection;
  quizCollection: QuizCollection;
}
