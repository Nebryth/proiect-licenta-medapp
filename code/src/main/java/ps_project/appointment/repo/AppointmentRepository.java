//package ps_project.appointment.repo;
//
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//import ps_project.appointment.models.AppointmentModel;
//
//import java.util.Date;
//
///**
// * Contains all the methods definitions for the existing 'appointments' operations
// */
//@Repository
//public interface AppointmentRepository extends CrudRepository<AppointmentModel, Integer> {
//
//    AppointmentModel findAppointmentById(Integer id);
//
//    Iterable<AppointmentModel> findAppointmentByUserIdAndAppointmentTypeNot(Integer doctorId, String appointmentType);
//
//    Iterable<AppointmentModel> findByAppointmentDateBetweenAndAppointmentTypeNot(Date begin, Date end, String appointmentType);
//
//    Iterable<AppointmentModel> findByAppointmentDateBetweenAndUserIdAndAppointmentTypeNot(Date begin, Date end, Integer doctorId, String appointmentType);
//
//}
