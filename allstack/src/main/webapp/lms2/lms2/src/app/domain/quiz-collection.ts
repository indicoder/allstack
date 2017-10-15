import { CourseSection }         from './course-section';
import { Question }   from './question'

export class QuizCollection {
  quizCollectionId: number;
  quizCollectionName: string;
  extQuizCollectionId: string;
  courseSection: CourseSection;
  isDefault: number;
  questions: Question[];
}
