import 'rxjs/add/operator/switchMap';
import { Component,NgModule,  OnInit, AfterViewChecked }        from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location }                 from '@angular/common';
import { Http } from '@angular/http';
import { HttpModule } from '@angular/http';
import { FormsModule }   from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser'

import { QuizCollection }        from './domain/quiz-collection';
import { Question } from './domain/question';
import { QuizCollectionService } from './quiz-collection.service';
import { QuestionService } from './question.service'

declare var PR: any;
import "../google-code-prettify/prettify.js?skin=sunburst"

@Component({
  selector: 'quiz-collection',
  templateUrl: './quiz-collection.component.html',
  styleUrls: [ './quiz-collection.component.css' ]
})

export class QuizCollectionComponent implements OnInit, AfterViewChecked {
  quizCollection: QuizCollection;
  code: string;

  constructor(
    private quizCollectionService: QuizCollectionService,
    private questionService: QuestionService,
    private route: ActivatedRoute,
    private location: Location,
    private http: Http
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

  deleteQuestion(question: Question): void {
    this.questionService
        .delete(question.questionId)
        .then(() => {
          //this.courses = this.courses.filter(h => h !== courseSection);
          //if (this.selectedHero === hero) { this.selectedHero = null; }
        });
  }

  goBack(): void {
    this.location.back();
  }

  ngAfterViewChecked(){
      //var PR;
      //console.log('ngAfterViewChecked')
      PR.prettyPrint();
  }

  /*public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }*/

  /*refresh(){
    this.http.get("code.html")
    .subscribe(
      res => {
        this.code = res._body;
      },
      ()=>{},
      ()=>{})
  }*/
}
