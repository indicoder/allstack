import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { QuizCollection } from './domain/quiz-collection';

@Injectable()
export class QuizCollectionService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private quizCollectionUrl = 'http://localhost:9080/api/quizCollection';  // URL to web api
  private courseSectionAddUrl = 'http://localhost:9080/api/quizCollection/add';  // URL to web api

  constructor(private http: Http) { }

  getQuizCollection(quizCollectionId: number): Promise<QuizCollection> {
    const url = `${this.quizCollectionUrl}/${quizCollectionId}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as QuizCollection)
      .catch(this.handleError);
  }

  create(courseSectionId: number, quizCollectionName: string, extQuizCollectionId: string): Promise<QuizCollection> {
    return this.http
      .post(this.courseSectionAddUrl, JSON.stringify({"courseSection": {courseSectionId: courseSectionId}, quizCollectionName: quizCollectionName, extQuizCollectionId: extQuizCollectionId}), {headers: this.headers})
      .toPromise()
      .then(res => res.json().data as QuizCollection)
      .catch(this.handleError);
  }

  delete(quizCollectionId: number): Promise<void> {
   const url = `${this.quizCollectionUrl}/${quizCollectionId}`;
   return this.http.delete(url, {headers: this.headers})
     .toPromise()
     .then(() => null)
     .catch(this.handleError);
 }

 update(quizCollection: QuizCollection): Promise<QuizCollection> {
    const url = `${this.quizCollectionUrl}/${quizCollection.quizCollectionId}`;
    return this.http
      .put(url, JSON.stringify(quizCollection), {headers: this.headers})
      .toPromise()
      .then(() => quizCollection)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
