import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AlertModule, ModalModule } from 'ngx-bootstrap';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { CoursesComponent }  from './courses.component';
import { CourseSectionComponent } from './course-section.component'
import { QuizCollectionComponent } from './quiz-collection.component'
import { AddCourseSectionModalComponent } from './modals/add-course-section-modal'

import { CourseService }          from './course.service';
import { CourseSectionService }          from './course-section.service';
import { QuizCollectionService }          from './quiz-collection.service';

@NgModule({
  declarations: [
    AppComponent, AddCourseSectionModalComponent, QuizCollectionComponent,
    CoursesComponent, CourseSectionComponent,
  ],
  imports: [
    BrowserModule, AlertModule.forRoot(), ModalModule.forRoot(),AppRoutingModule, HttpModule, FormsModule
  ],
  providers: [CourseService, CourseSectionService, QuizCollectionService],
  bootstrap: [AppComponent]
})
export class AppModule { }
