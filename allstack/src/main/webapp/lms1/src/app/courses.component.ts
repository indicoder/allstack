import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';
import { ModalModule } from 'ngx-bootstrap/modal'

import { Course }                from './domain/course';
import { CourseService }         from './course.service';

@Component({
  selector: 'all-courses',
  templateUrl: './courses.component.html',
  styleUrls: [ './courses.component.css' ]
})
export class CoursesComponent implements OnInit {
  courses: Course[];
  ngOnInit(): void {
    this.getAllCourses();
    console.log('Course are ' + this.courses)
  }

  constructor(
  private courseService: CourseService,
  private router: Router) { }

  getAllCourses(): void {
    this.courseService
        .getAllCourses()
        .then(courses => this.courses = courses);
  }
}
