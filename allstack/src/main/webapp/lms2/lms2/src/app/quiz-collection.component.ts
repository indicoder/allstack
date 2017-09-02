import 'rxjs/add/operator/switchMap';
import { Component, OnInit }        from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';

import { QuizCollection }        from './domain/quiz-collection';
import { QuizCollectionService } from './quiz-collection.service';

@Component({
  selector: 'quiz-collection',
  templateUrl: './quiz-collection.component.html',
  styleUrls: [ './quiz-collection.component.css' ]
})

export class QuizCollectionComponent implements OnInit {
  quizCollection: QuizCollection;

  constructor(
    private quizCollectionService: QuizCollectionService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.quizCollectionService.getQuizCollection(+params.get('id')))
      .subscribe(quizCollection => this.quizCollection = quizCollection);
  }

  save(): void {
    this.quizCollectionService.update(this.quizCollection)
      .then(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }
}
