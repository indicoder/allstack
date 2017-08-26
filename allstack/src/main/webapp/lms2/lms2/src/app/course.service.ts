import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Course } from './domain/course';

@Injectable()
export class CourseService {
  private headers = new Headers({'Content-Type': 'application/json'});
  private coursesUrl = 'http://localhost:9080/api/course/courses';  // URL to web api

  constructor(private http: Http) { }

  getAllCourses(): Promise<Course[]> {
    return this.http.get(this.coursesUrl)
               .toPromise()
               .then(response => response.json().data as Course[])
               .catch(this.handleError);
  }

  getCourse(id: number): Promise<Course> {
    const url = `${this.coursesUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as Course)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
