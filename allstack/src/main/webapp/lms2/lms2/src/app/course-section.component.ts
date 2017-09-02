import 'rxjs/add/operator/switchMap';
import { Component, OnInit }        from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';

import { CourseSection }        from './domain/course-section';
import { QuizCollection }        from './domain/quiz-collection';
import { LessonPage }        from './domain/lesson-page';
import { CourseSectionService } from './course-section.service';
import { QuizCollectionService } from './quiz-collection.service';
import { LessonPageService } from './lesson-page.service';

@Component({
  selector: 'course-section',
  templateUrl: './course-section.component.html',
  styleUrls: [ './course-section.component.css' ]
})
export class CourseSectionComponent implements OnInit {
  courseSection: CourseSection;

  constructor(
    private courseSectionService: CourseSectionService,
    private quizCollectionService: QuizCollectionService,
    private lessonPageService: LessonPageService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.courseSectionService.getCourseSection(+params.get('id')))
      .subscribe(courseSection => this.courseSection = courseSection);
  }

  save(): void {
    this.courseSectionService.update(this.courseSection)
      .then(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }

  deleteQuizCollection(quizCollection: QuizCollection): void {
    this.quizCollectionService
        .delete(quizCollection.quizCollectionId)
        .then(() => {
          //this.courses = this.courses.filter(h => h !== courseSection);
          //if (this.selectedHero === hero) { this.selectedHero = null; }
        });
  }

  deleteLessonPage(lessonPage: LessonPage): void {
    this.lessonPageService
        .delete(lessonPage.pageId)
        .then(() => {
          //this.courses = this.courses.filter(h => h !== courseSection);
          //if (this.selectedHero === hero) { this.selectedHero = null; }
        });
  }
}
