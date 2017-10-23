import { Component, Input, TemplateRef, ViewChild, OnInit } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/modal-options.class';

import { QuestionService } from '../question.service';
import { Question }        from '../domain/question';
import { QuizCollection } from '../domain/quiz-collection'

@Component({
  selector: 'edit-question-modal',
  templateUrl: './edit-question-modal.html'
})
export class EditQuestionModalComponent implements OnInit{
  public modalRef: BsModalRef;
  //question: Question;
  //@Input() quizCollection: QuizCollection;
  @Input() question: Question;
  constructor(
    private modalService: BsModalService,
    private questionService: QuestionService
  ) {}

  ngOnInit(): void {
    //this.question = new Question();
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  updateQuestion(): void {
    this.question.quizQuestionHTML = this.question.quizQuestionHTML.trim();
    if (!this.question.quizQuestionHTML) { return; }
    this.questionService.update( this.question )
      .then(question => {
        //this.quizCollection.questions.push(question);
        //this.quizCollection = null;
      });
  }
}
