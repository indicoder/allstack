import { QuizCollection }   from './quiz-collection'
import { QuestionType } from './question-type'

export class Question {
  questionId: number;
  extQuestionId: string;
  quizCollection: QuizCollection;
  //questionType: QuestionType;
  questionType: number;
  quizQuestionHTML: string;
  choice1HTML: string;
  choice2HTML: string;
  choice3HTML: string;
  choice4HTML: string;
  isChoice1Correct: number;
  isChoice2Correct: number;
  isChoice3Correct: number;
  isChoice4Correct: number;
  answerHTML: string;
  answerHintHTML: string;
  answerExplanationHTML: string;
  pointsForQuestion: number;
}
