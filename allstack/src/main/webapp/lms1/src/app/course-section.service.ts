import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { CourseSection } from './domain/course-section';

@Injectable()
export class CourseSectionService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private courseSectionUrl = 'http://localhost:9080/api/section';  // URL to web api

  constructor(private http: Http) { }

  getCourseSection(id: number): Promise<CourseSection> {
    const url = `${this.courseSectionUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as CourseSection)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
