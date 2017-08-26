import { Component, Input, TemplateRef, ViewChild, OnInit } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/modal-options.class';

import {Course} from '../domain/course'
import { CourseSectionService } from '../course-section.service';
import { CourseSection }        from '../domain/course-section';

@Component({
  selector: 'add-course-section-modal',
  templateUrl: './add-course-section-modal.html'
})
export class AddCourseSectionModalComponent implements OnInit{
  public modalRef: BsModalRef;
  courseSection: CourseSection;
  @Input() course: Course;
  constructor(
    private modalService: BsModalService,
    private courseSectionService: CourseSectionService
  ) {}

  ngOnInit(): void {
    this.courseSection = new CourseSection();
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  addCourseSection(): void {
    this.courseSection.sectionName = this.courseSection.sectionName.trim();
    this.courseSection.extSectionId = this.courseSection.extSectionId.trim();
    if (!this.courseSection.sectionName || !this.courseSection.extSectionId) { return; }
    this.courseSectionService.create( this.course.courseId, this.courseSection.sectionName, this.courseSection.extSectionId)
      .then(courseSection => {
        this.course.courseSections.push(courseSection);
        //this.courseSection = null;
      });
  }
}
