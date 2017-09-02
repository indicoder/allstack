import 'rxjs/add/operator/switchMap';
import { Component, OnInit }        from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';

import { LessonPage }        from './domain/lesson-page'
import { LessonPageService } from './lesson-page.service';

@Component({
  selector: 'lesson-page',
  templateUrl: './lesson-page.component.html',
  styleUrls: [ './lesson-page.component.css' ]
})

export class LessonPageComponent implements OnInit {
  lessonPage: LessonPage;

  constructor(
    private lessonPageService: LessonPageService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.lessonPageService.getLessonPage(+params.get('id')))
      .subscribe(lessonPage => this.lessonPage = lessonPage);
  }

  save(): void {
    this.lessonPageService.update(this.lessonPage)
      .then(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }
}
