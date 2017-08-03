import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CoursesComponent }   from './courses.component';
import { CourseSectionComponent } from './course-section.component'

const routes: Routes = [
  { path: '', redirectTo: '/courses', pathMatch: 'full' },
  { path: 'courses',  component: CoursesComponent },
  { path: 'coursesection/:id',  component: CourseSectionComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
