import { Component, Input, TemplateRef, ViewChild, OnInit } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/modal-options.class';

import { CourseSection } from '../domain/course-section'
import { LessonPageService } from '../lesson-page.service';
import { LessonPage }        from '../domain/lesson-page';

@Component({
  selector: 'add-lesson-page-modal',
  templateUrl: './add-lesson-page-modal.html'
})
export class AddLessonPageModalComponent implements OnInit{
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
    this.lessonPage.pageName = this.lessonPage.pageName.trim();
    this.lessonPage.extPageId = this.lessonPage.extPageId.trim();
    if (!this.lessonPage.pageName || !this.lessonPage.extPageId) { return; }
    this.lessonPageService.create( this.courseSection.courseSectionId, this.lessonPage.pageName, this.lessonPage.extPageId)
      .then(lessonPage => {
        this.courseSection.lessonPages.push(lessonPage);
        //this.quizCollection = null;
      });
  }
}
