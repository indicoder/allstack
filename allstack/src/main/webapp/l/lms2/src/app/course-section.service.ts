import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { CourseSection } from './domain/course-section';
import { Course } from './domain/course';

@Injectable()
export class CourseSectionService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private courseSectionUrl = 'http://localhost:9080/api/section';  // URL to web api
  private courseSectionAddUrl = 'http://localhost:9080/api/section/add';  // URL to web api

  constructor(private http: Http) { }

  getCourseSection(courseSectionId: number): Promise<CourseSection> {
    const url = `${this.courseSectionUrl}/${courseSectionId}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as CourseSection)
      .catch(this.handleError);
  }

  create(courseId: number, sectionName: string, extSectionId: string): Promise<CourseSection> {
    return this.http
      .post(this.courseSectionAddUrl, JSON.stringify({"course": {courseId: courseId}, sectionName: sectionName, extSectionId: extSectionId}), {headers: this.headers})
      .toPromise()
      .then(res => res.json().data as CourseSection)
      .catch(this.handleError);
  }

  delete(courseSectionId: number): Promise<void> {
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
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
