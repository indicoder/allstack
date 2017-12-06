import { Component, Input, TemplateRef, ViewChild, OnInit } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/modal-options.class';

import { CourseSection } from '../domain/course-section'
import { LessonPageService } from '../lesson-page.service';
import { LessonPage }        from '../domain/lesson-page';
import { QuizCollection }        from '../domain/quiz-collection';

@Component({
  selector: 'addedit-lesson-page-content-modal',
  templateUrl: './addedit-lesson-page-content-modal.html'
})
export class AddEditLessonPageContentModalComponent implements OnInit{
  public modalRef: BsModalRef;
  modalAction: string;
  public contentType: any;
  public selectedQuizCollection: QuizCollection;
  @Input() lessonPage: LessonPage;
  constructor(
    private modalService: BsModalService,
    private lessonPageService: LessonPageService
  ) {}

  ngOnInit(): void {
    if(!this.lessonPage.contentHTML){
      this.modalAction = 'add';
    }else{
      this.modalAction = 'edit';
    }
    if(!this.lessonPage.quizCollection){
      this.selectedQuizCollection = new QuizCollection();
    }else{
      if(typeof this.lessonPage.quizCollection === 'number'){//Only Id has been returned from API
        //Get the full QuizCollection obj
        let tempQuizCollectionId = parseInt(this.lessonPage.quizCollection);
        for(let i = 0; i < this.lessonPage.courseSection.quizCollections.length; i++){
          let tempQuizCollection = this.lessonPage.courseSection.quizCollections[i];
          if(tempQuizCollection.quizCollectionId == tempQuizCollectionId){
            this.lessonPage.quizCollection = tempQuizCollection;
            this.selectedQuizCollection = tempQuizCollection;
          }
        }
      }
      this.selectedQuizCollection = this.lessonPage.quizCollection;
    }
    if(this.lessonPage.contentType == 0){this.contentType = 'content';}
    if(this.lessonPage.contentType == 1){this.contentType = 'quiz';}
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  updateLessonPage(): void {
    this.lessonPage.quizCollection = this.selectedQuizCollection;
    if(this.contentType){
      if(this.contentType == 'content'){this.lessonPage.contentType = 0;}
      if(this.contentType == 'quiz'){this.lessonPage.contentType = 1;}
    }
    this.lessonPageService.validate(this.lessonPage);
    this.lessonPageService.update(this.lessonPage)
      .then(lessonPage => {
        //this.courseSection.lessonPages.push(lessonPage);
        this.modalRef.hide();
        //this.quizCollection = null;
      });
  }

  public isOpenChange(quizCollection: QuizCollection): void {
    console.log('Dropdown state is changed1');
    this.selectedQuizCollection = quizCollection;
  }

  public selectQuizCollection(quizCollection: QuizCollection): void {
    console.log('Dropdown state is changed2');
    this.selectedQuizCollection = quizCollection;
  }
}
