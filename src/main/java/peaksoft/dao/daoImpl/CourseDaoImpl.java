package peaksoft.dao.daoImpl;

import com.sun.net.httpserver.Authenticator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.CourseDao;
import peaksoft.entity.Course;

import java.util.List;

public class CourseDaoImpl implements CourseDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManager();

    public String saveCourse(Course course) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }

    public Course getCourseById(Long courseId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Course course = entityManager.createQuery("select c from Course c where c.id = :courseId", Course.class)
                    .setParameter("courseId", courseId).getSingleResult();
            entityManager.getTransaction().commit();
            return course;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //    TODO with Java code
    @Override
    public String updateCourse(Long courseId, Course newCourse) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            course.setCourseName(newCourse.getCourseName());
            course.setPrice(newCourse.getPrice());
            course.setStadyFormat(newCourse.getStadyFormat());
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            entityManager.close();
        }
        return "Success";
    }


//    TODO With query
//    public String updateCourse(Long courseId, Course newCourse) {
//        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
//            entityManager.getTransaction().begin();
//            entityManager.createQuery("update Course c set c.courseName = :courseName , c.price = :price , c.stadyFormat = :stadyFormat " +
//                            "where c.id = :courseId")
//                    .setParameter("courseName", newCourse.getCourseName())
//                    .setParameter("price", newCourse.getPrice())
//                    .setParameter("stadyFormat",newCourse.getStadyFormat())
//                    .setParameter("courseId", courseId)
//                    .executeUpdate();
//            entityManager.getTransaction().commit();
//        }
//        return "Success";
//    }

    public List<Course> getAllCourses() {
        List<Course> courses;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            courses = entityManager.createQuery("select c from Course c", Course.class).getResultList();
            entityManager.getTransaction().commit();
        }
        return courses;
    }

    public String deleteCourseId(Long courseId) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            entityManager.remove(course);
            entityManager.getTransaction().commit();
            return "Success";
        } catch (HibernateException e) {
            return e.getMessage();
        }
    }
}
