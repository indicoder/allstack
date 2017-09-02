import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { LessonPage } from './domain/lesson-page';

@Injectable()
export class LessonPageService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private lessonPageUrl = 'http://localhost:9080/api/lessonPage';  // URL to web api
  private lessonPageAddUrl = 'http://localhost:9080/api/lessonPage/add';  // URL to web api

  constructor(private http: Http) { }

  getLessonPage(lessonPageId: number): Promise<LessonPage> {
    const url = `${this.lessonPageUrl}/${lessonPageId}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as LessonPage)
      .catch(this.handleError);
  }

  create(courseSectionId: number, pageName: string, extPageId: string): Promise<LessonPage> {
    return this.http
      .post(this.lessonPageAddUrl, JSON.stringify({"courseSection": {courseSectionId: courseSectionId}, pageName: pageName, extPageId: extPageId}), {headers: this.headers})
      .toPromise()
      .then(res => res.json().data as LessonPage)
      .catch(this.handleError);
  }

  delete(pageId: number): Promise<void> {
   const url = `${this.lessonPageUrl}/${pageId}`;
   return this.http.delete(url, {headers: this.headers})
     .toPromise()
     .then(() => null)
     .catch(this.handleError);
 }

 update(lessonPage: LessonPage): Promise<LessonPage> {
    const url = `${this.lessonPageUrl}/${lessonPage.pageId}`;
    return this.http
      .put(url, JSON.stringify(lessonPage), {headers: this.headers})
      .toPromise()
      .then(() => lessonPage)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
