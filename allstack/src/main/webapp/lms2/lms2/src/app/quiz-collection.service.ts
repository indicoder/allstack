import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { QuizCollection } from './domain/quiz-collection';

@Injectable()
export class QuizCollectionService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private quizCollectionUrl = 'http://localhost:9080/api/quizcollection';  // URL to web api
  //private courseSectionAddUrl = 'http://localhost:9080/api/section/add';  // URL to web api

  constructor(private http: Http) { }

  getQuizCollection(quizCollectionId: number): Promise<QuizCollection> {
    const url = `${this.quizCollectionUrl}/${quizCollectionId}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as QuizCollection)
      .catch(this.handleError);
  }

  create(quizCollectionId: number, quizCollectionName: string, extQuizCollectionId: string): Promise<QuizCollection> {
    return this.http
      .post(this.quizCollectionUrl, JSON.stringify({"course": {quizCollectionId: quizCollectionId}, quizCollectionName: quizCollectionName, extQuizCollectionId: extQuizCollectionId}), {headers: this.headers})
      .toPromise()
      .then(res => res.json().data as QuizCollection)
      .catch(this.handleError);
  }

  /*delete(courseSectionId: number): Promise<void> {
   const url = `${this.courseSectionUrl}/${courseSectionId}`;
   return this.http.delete(url, {headers: this.headers})
     .toPromise()
     .then(() => null)
     .catch(this.handleError);
 }

 update(courseSection: CourseSection): Promise<CourseSection> {
    const url = `${this.courseSectionUrl}/${courseSection.courseSectionId}`;
    return this.http
      .put(url, JSON.stringify(courseSection), {headers: this.headers})
      .toPromise()
      .then(() => courseSection)
      .catch(this.handleError);
  }*/

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
