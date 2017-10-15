import { QuizCollection }   from './quiz-collection'

export class Question {
  questionId: number;
  extQuestionId: string;
  quizCollection: QuizCollection;
  quizQuestionHTML: string;
}
