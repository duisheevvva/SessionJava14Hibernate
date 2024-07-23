package peaksoft;


import peaksoft.config.HibernateConfig;
import peaksoft.entity.Course;
import peaksoft.enums.StadyFormat;
import peaksoft.service.CourseService;
import peaksoft.service.serviceImpl.CourseServiceImpl;

public class App {
    public static void main(String[] args) {


        CourseService courseService = new CourseServiceImpl();
//        System.out.println(courseService.saveCourse(new Course("Java", 14000, StadyFormat.OFFLINE)));
        System.out.println(courseService.getCourseById(1L));
//        System.out.println(courseService.updateCourse(1L, new Course("JS", 12000, StadyFormat.ONLINE)));
//        System.out.println(courseService.getAllCourses());
//        System.out.println(courseService.deleteCourseId(1L));
//        System.out.println(courseService.getAllCourses());


    }
}
