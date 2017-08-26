import 'rxjs/add/operator/switchMap';
import { Component, OnInit }        from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';

import { CourseSection }        from './domain/course-section';
import { CourseSectionService } from './course-section.service';

@Component({
  selector: 'course-section',
  templateUrl: './course-section.component.html',
  styleUrls: [ './course-section.component.css' ]
})
export class CourseSectionComponent implements OnInit {
  courseSection: CourseSection;

  constructor(
    private courseSectionService: CourseSectionService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.courseSectionService.getCourseSection(+params.get('id')))
      .subscribe(courseSection => this.courseSection = courseSection);
  }

  /*save(): void {
    this.courseSectionService.update(this.courseSection)
      .then(() => this.goBack());
  }*/

  goBack(): void {
    this.location.back();
  }
}
