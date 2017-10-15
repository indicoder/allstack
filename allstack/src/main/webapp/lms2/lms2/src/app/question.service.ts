import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Question } from './domain/question';

@Injectable()
export class QuestionService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private questionUrl = 'http://localhost:9080/api/question';  // URL to web api
  private questionAddUrl = 'http://localhost:9080/api/question/add';  // URL to web api

  constructor(private http: Http) { }

  getQuestion(questionId: number): Promise<Question> {
    const url = `${this.questionUrl}/${questionId}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as Question)
      .catch(this.handleError);
  }

  create(quizCollectionId: number, quizQuestionHTML: string): Promise<Question> {
    return this.http
      .post(this.questionAddUrl, JSON.stringify({"quizCollection": {quizCollectionId: quizCollectionId}, quizQuestionHTML: quizQuestionHTML}), {headers: this.headers})
      .toPromise()
      .then(res => res.json().data as Question)
      .catch(this.handleError);
  }

  delete(questionId: number): Promise<void> {
   const url = `${this.questionUrl}/${questionId}`;
   return this.http.delete(url, {headers: this.headers})
     .toPromise()
     .then(() => null)
     .catch(this.handleError);
 }

 update(question: Question): Promise<Question> {
    const url = `${this.questionUrl}/${question.questionId}`;
    return this.http
      .put(url, JSON.stringify(question), {headers: this.headers})
      .toPromise()
      .then(() => question)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
