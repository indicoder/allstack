import { Component, Input, TemplateRef, ViewChild, OnInit } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/modal-options.class';

import { CourseSection } from '../domain/course-section'
import { QuizCollectionService } from '../quiz-collection.service';
import { QuizCollection }        from '../domain/quiz-collection';

@Component({
  selector: 'add-quiz-collection-modal',
  templateUrl: './add-quiz-collection-modal.html'
})
export class AddQuizCollectionModalComponent implements OnInit{
  public modalRef: BsModalRef;
  quizCollection: QuizCollection;
  @Input() courseSection: CourseSection;
  constructor(
    private modalService: BsModalService,
    private quizCollectionService: QuizCollectionService
  ) {}

  ngOnInit(): void {
    this.quizCollection = new QuizCollection();
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  addQuizCollection(): void {
    this.quizCollection.quizCollectionName = this.quizCollection.quizCollectionName.trim();
    this.quizCollection.extQuizCollectionId = this.quizCollection.extQuizCollectionId.trim();
    if (!this.quizCollection.quizCollectionName || !this.quizCollection.extQuizCollectionId) { return; }
    this.quizCollectionService.create( this.courseSection.courseSectionId, this.quizCollection.quizCollectionName, this.quizCollection.extQuizCollectionId)
      .then(quizCollection => {
        this.courseSection.quizCollections.push(quizCollection);
        //this.quizCollection = null;
      });
  }
}
