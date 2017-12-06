import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';
import { ModalModule } from 'ngx-bootstrap/modal'

import { Course }                from './domain/course';
import { CourseService }         from './course.service';
import { CourseSectionService } from './course-section.service'
import { CourseSection } from './domain/course-section'

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
  private courseSectionService: CourseSectionService,
  private router: Router) { }

  getAllCourses(): void {
    this.courseService
        .getAllCourses()
        .then(courses => this.courses = courses);
  }

  delete(courseSection: CourseSection): void {
    this.courseSectionService
        .delete(courseSection.courseSectionId)
        .then(() => {
          //this.courses = this.courses.filter(h => h !== courseSection);
          //if (this.selectedHero === hero) { this.selectedHero = null; }
        });
  }
}
