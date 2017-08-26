import { LessonPage }         from './lesson-page';
import { Course }         from './course';
export class CourseSection {
  courseSectionId: number;
  sectionName: string;
  extSectionId: string;
  lessonPages: LessonPage[];
  course: Course;
}
