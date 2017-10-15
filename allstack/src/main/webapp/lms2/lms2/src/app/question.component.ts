import 'rxjs/add/operator/switchMap';
import { Component, OnInit }        from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';

import { Question }        from './domain/question';
import { QuestionService } from './question.service';

@Component({
  selector: 'question',
  templateUrl: './question.component.html'
  //styleUrls: [ './question.component.css' ]
})

export class QuestionComponent implements OnInit {
  question: Question;

  constructor(
    private questionService: QuestionService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.questionService.getQuestion(+params.get('id')))
      .subscribe(question => this.question = question);
  }

  save(): void {
    this.questionService.update(this.question)
      .then(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }
}
