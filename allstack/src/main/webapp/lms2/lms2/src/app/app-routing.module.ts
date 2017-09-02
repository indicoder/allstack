import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CoursesComponent }   from './courses.component';
import { CourseSectionComponent } from './course-section.component'
import { QuizCollectionComponent } from './quiz-collection.component'
import { LessonPageComponent } from './lesson-page.component'

const routes: Routes = [
  { path: '', redirectTo: '/courses', pathMatch: 'full' },
  { path: 'courses',  component: CoursesComponent },
  { path: 'coursesection/:id',  component: CourseSectionComponent },
  { path: 'quizcollection/:id',  component: QuizCollectionComponent },
  { path: 'lessonpage/:id',  component: LessonPageComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
