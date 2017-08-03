import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent }  from './app.component';
import { CoursesComponent }  from './courses.component';
import { CourseSectionComponent } from './course-section.component'

import { CourseService }          from './course.service';
import { CourseSectionService }          from './course-section.service';

@NgModule({
  imports:      [ BrowserModule, AppRoutingModule, HttpModule, FormsModule ],
  declarations: [ AppComponent, CoursesComponent, CourseSectionComponent ],
  providers: [ CourseService, CourseSectionService ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
