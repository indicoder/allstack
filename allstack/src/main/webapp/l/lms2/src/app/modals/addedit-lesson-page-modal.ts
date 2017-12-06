import { Component, Input, TemplateRef, ViewChild, OnInit } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/modal-options.class';

import { CourseSection } from '../domain/course-section'
import { LessonPageService } from '../lesson-page.service';
import { LessonPage }        from '../domain/lesson-page';

@Component({
  selector: 'addedit-lesson-page-modal',
  templateUrl: './addedit-lesson-page-modal.html'
})
export class AddEditLessonPageModalComponent implements OnInit{
  public modalRef: BsModalRef;
  lessonPage: LessonPage;
  @Input() courseSection: CourseSection;
  constructor(
    private modalService: BsModalService,
    private lessonPageService: LessonPageService
  ) {}

  ngOnInit(): void {
    this.lessonPage = new LessonPage();
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  addLessonPage(): void {
    this.lessonPage.courseSection = this.courseSection;
    if (!this.lessonPage.pageName || !this.lessonPage.extPageId) { return; }
    this.lessonPageService.create(this.lessonPage)
      .then(lessonPage => {
        this.courseSection.lessonPages.push(lessonPage);
        this.modalRef.hide();
        //this.quizCollection = null;
      });
  }
}
