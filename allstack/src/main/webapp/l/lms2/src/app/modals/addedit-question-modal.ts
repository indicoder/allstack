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
  //question: Question;
  questionType: number;
  modalAction: string;
  @Input() quizCollection: QuizCollection;
  @Input() question: Question;
  public multiChoiceAnswer:any;// = {left: false, middle: false, right: false};
  public singleChoiceAnswer: string;// = 'Middle';
  constructor(
    private modalService: BsModalService,
    private questionService: QuestionService
  ) {}

  ngOnInit(): void {
    if(!this.question){//If question not there, then it's add
      this.question = new Question();
      this.modalAction = 'add';
    }else{
      this.modalAction = 'edit';
    }
  }

  public openModal(template: TemplateRef<any>, questionType) {
    if(this.modalAction == 'add'){
      this.questionType = questionType;
    } else {
        this.questionType = this.question.questionType;
    }
    this.modalRef = this.modalService.show(template);
  }

  //called while adding a new Q
  addQuestion(): void {
    this.question.questionType = this.questionType;
    this.question.quizQuestionHTML = this.question.quizQuestionHTML;
    this.question.quizCollection = this.quizCollection;
    //First all mandatory
    if (!this.question.quizQuestionHTML || !this.question.questionType || !this.question.quizCollection) { return; }
    //Do specific treatment for each of the Q type
    if(this.questionType == 1){//For SingleChoice
      //At least 2 choices need to be there
       if(!this.question.choice1HTML || !this.question.choice2HTML) return;
       if(!this.singleChoiceAnswer) return;
       if(this.singleChoiceAnswer == 'Opt1') this.question.isChoice1Correct = 1;
       if(this.singleChoiceAnswer == 'Opt2') this.question.isChoice2Correct = 1;
       if(this.singleChoiceAnswer == 'Opt3' && this.question.choice3HTML) {
          this.question.isChoice3Correct = 1;
        }
       if(this.singleChoiceAnswer == 'Opt4') this.question.isChoice4Correct = 1;
    }

    //this.questionService.create( this.quizCollection.quizCollectionId, this.question.quizQuestionHTML, this.question.questionType)
    this.questionService.create(this.question)
      .then(question => {
        this.quizCollection.questions.push(question);
        //this.quizCollection = null;
        this.modalRef.hide();
      });
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

  validateQuestion(): void{

  }
}
