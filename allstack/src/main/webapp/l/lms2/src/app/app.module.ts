import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AlertModule, ModalModule } from 'ngx-bootstrap';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { CoursesComponent }  from './courses.component';
import { CourseSectionComponent } from './course-section.component'
import { QuizCollectionComponent } from './quiz-collection.component'
import { LessonPageComponent } from './lesson-page.component'
import { QuestionComponent } from './question.component'

import { AddCourseSectionModalComponent } from './modals/add-course-section-modal'
import { AddQuizCollectionModalComponent } from './modals/add-quiz-collection-modal'
import { AddEditLessonPageModalComponent } from './modals/addedit-lesson-page-modal'
import { AddEditLessonPageContentModalComponent } from './modals/addedit-lesson-page-content-modal'
import { AddEditQuestionModalComponent } from './modals/addedit-question-modal'

import { CourseService }          from './course.service';
import { CourseSectionService }          from './course-section.service';
import { QuizCollectionService }          from './quiz-collection.service';
import { LessonPageService }          from './lesson-page.service';
import { QuestionService } from './question.service'

@NgModule({
  declarations: [
    AppComponent, AddCourseSectionModalComponent, QuizCollectionComponent, LessonPageComponent,
    CoursesComponent, CourseSectionComponent, AddQuizCollectionModalComponent, AddEditLessonPageModalComponent,
    QuestionComponent, AddEditQuestionModalComponent, AddEditLessonPageContentModalComponent
  ],
  imports: [
    BrowserModule, AlertModule.forRoot(), ModalModule.forRoot(), ButtonsModule.forRoot(), BsDropdownModule.forRoot() ,AppRoutingModule, HttpModule, FormsModule
  ],
  providers: [CourseService, CourseSectionService, QuizCollectionService, LessonPageService, QuestionService],
  bootstrap: [AppComponent]
})
export class AppModule { }
