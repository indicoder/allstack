import { Component, Input, TemplateRef, ViewChild, OnInit } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/modal-options.class';

import { QuestionService } from '../question.service';
import { Question }        from '../domain/question';
import { QuizCollection } from '../domain/quiz-collection'

@Component({
  selector: 'addedit-question-modal',
  templateUrl: './addedit-question-modal.html'
})
export class AddEditQuestionModalComponent implements OnInit{
  public modalRef: BsModalRef;
  question: Question;
  @Input() quizCollection: QuizCollection;
  constructor(
    private modalService: BsModalService,
    private questionService: QuestionService
  ) {}

  ngOnInit(): void {
    this.question = new Question();
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  addQuestion(): void {
    this.question.quizQuestionHTML = this.question.quizQuestionHTML.trim();
    if (!this.question.quizQuestionHTML) { return; }
    this.questionService.create( this.quizCollection.quizCollectionId, this.question.quizQuestionHTML)
      .then(question => {
        this.quizCollection.questions.push(question);
        //this.quizCollection = null;
      });
  }
}
