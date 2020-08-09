import React, { Component } from "react";
import ApiClient from "../../services/apiClient";
import SessionManager from "../../services/sessionManager";

export default class Appointments extends Component {
  constructor(props) {
    super(props);
    this.state = {
      appointment: {},
      appointments: [],
      patients: [],
      doctors: []
    };
  }

  handleSubmit = event => {
    event.preventDefault();
    ApiClient.createAppointmentFetch(this.state.appointment).then(data => {
      if (data.ok) window.location.reload();
    });
  };

  getAppointments = () => {
    const now = new Date();
    const firstday = Math.floor(
      new Date(now.setDate(now.getDate() - now.getDay() + 1)).getTime() / 1000
    );
    const lastday = Math.floor(
      new Date(now.setDate(now.getDate() - now.getDay() + 5)).getTime() / 1000
    );
    if (SessionManager.getUserProperty("userRole") === "doctor")
      ApiClient.appointmentsBetweenFetch(
        firstday,
        lastday,
        SessionManager.getUserProperty("id")
      ).then(appointments => {
        this.setState({ appointments });
      });
    else {
      ApiClient.appointmentsBetweenFetch(firstday, lastday).then(
        appointments => {
          this.setState({ appointments });
        }
      );
    }
    ApiClient.patientsFetch().then(patients => {
      this.setState({ patients });
    });
    ApiClient.doctorsFetch().then(doctors => {
      this.setState({ doctors });
    });
  };

  getPatientFullName = patient_id => {
    const data = this.state.patients.find(patient => patient.id === patient_id);
    return data && `${data.first_name} ${data.last_name}`;
  };

  handleChange = event => {
    let value = event.target.value;
    if (event.target.id === "form_create-appointment_appointment_date") {
      value = new Date(value).getTime() / 1000;
    }
    this.setState({
      appointment: {
        ...this.state.appointment,
        [event.target.id.replace("form_create-appointment_", "")]: value
      }
    });
  };

  handleDeny = currentAppointment => {
    const appointment = {
      ...currentAppointment,
      appointment_type: "denied"
    };
    ApiClient.updateAppointmentFetch(appointment).then(data => {
      if (data.ok) window.location.reload();
    });
  };

  handleAccept = currentAppointment => {
    const appointment = {
      ...currentAppointment,
      appointment_type: "accepted"
    };
    ApiClient.updateAppointmentFetch(appointment).then(data => {
      if (data.ok) window.location.reload();
    });
  };

  componentWillMount() {
    this.getAppointments();
  }

  render() {
    const now = new Date();
    const weekDay = now.getDay();
    const hour = now.getHours();
    return (
      <div className="container-fluid">
        <div className="row">
          <div className="col-lg-12 h1 text-dark mt-3">
            Appointments Management
          </div>
          <div className="col-lg-12 lead text-muted mb-5">
            View appointments
          </div>
          <div className="col-lg-12 col-md-12 col-sm-12">
            <div className="table-responsive">
              <h2 className="text-muted blockquote text-center">
                Appointments
              </h2>
              <table
                className="table text-muted table-sm table-bordered"
                style={{ tableLayout: "fixed" }}
              >
                <thead>
                  <tr>
                    <th scope="col" style={{ width: "70px" }}>
                      Hour
                    </th>
                    <th scope="col">Monday</th>
                    <th scope="col">Tuesday</th>
                    <th scope="col">Wednesday</th>
                    <th scope="col">Thursday</th>
                    <th scope="col">Friday</th>
                  </tr>
                </thead>
                <tbody>
                  {Array(10)
                    .join()
                    .split(",")
                    .map((item, i) => {
                      const nowHour = i + 8;
                      const nextHour = i + 9;
                      return (
                        <tr key={i}>
                          <th scope="row">
                            {nowHour} - {nextHour}
                          </th>
                          {Array(5)
                            .join()
                            .split(",")
                            .map((item, k) => {
                              const appointment = this.state.appointments.find(
                                appointment => {
                                  const appointmentTime = new Date(
                                    appointment.appointment_date * 1000
                                  );
                                  const appointmentHour =
                                    appointment && appointmentTime.getHours();
                                  const appointmentWeekDay =
                                    appointment && appointmentTime.getDay();
                                  return (
                                    nowHour <= appointmentHour &&
                                    appointmentHour < nextHour &&
                                    appointmentWeekDay === k + 1
                                  );
                                }
                              );
                              const timeNow =
                                weekDay === k + 1 &&
                                (nowHour <= hour && hour < nextHour);
                              return (
                                <td
                                  key={k}
                                  className={
                                    "text-white " +
                                    (timeNow ? "bg-success" : null)
                                  }
                                >
                                  {appointment ? (
                                    <div>
                                      <strong>
                                        Appointment with{" "}
                                        {this.getPatientFullName(
                                          appointment.patient_id
                                        )}
                                      </strong>
                                      {SessionManager.getUserProperty(
                                        "userRole"
                                      ) === "doctor" &&
                                      appointment.appointment_type === "new" ? (
                                        <div>
                                          <button
                                            type="submit"
                                            onClick={() =>
                                              this.handleAccept(appointment)
                                            }
                                            className="btn btn-primary"
                                          >
                                            Accept
                                          </button>
                                          <button
                                            type="submit"
                                            onClick={() =>
                                              this.handleDeny(appointment)
                                            }
                                            className="btn btn-danger"
                                          >
                                            Deny
                                          </button>
                                        </div>
                                      ) : null}
                                    </div>
                                  ) : null}
                                </td>
                              );
                            })}
                        </tr>
                      );
                    })}
                </tbody>
              </table>
            </div>
          </div>
          {SessionManager.getUserProperty("userRole") === "nurse" ? (
            <div className="col-lg-12 col-md-12 col-sm-12">
              <div className="table-responsive">
                <h2 className="text-muted blockquote text-center">
                  Create Appointments
                </h2>
                <form className="text-center">
                  <div className="form-group">
                    <select
                      className="form-control"
                      id="form_create-appointment_patient_id"
                      onChange={this.handleChange}
                      defaultValue=""
                    >
                      <option value="" hidden>
                        Select a patient
                      </option>
                      {this.state.patients.map(patient => {
                        return (
                          <option value={patient.id} key={patient.id}>
                            {patient.first_name} {patient.last_name} |{" "}
                            {patient.address}
                          </option>
                        );
                      })}
                    </select>
                  </div>
                  <div className="form-group">
                    <select
                      className="form-control"
                      id="form_create-appointment_doctor_id"
                      onChange={this.handleChange}
                      defaultValue=""
                    >
                      <option value="" hidden>
                        Select a doctor
                      </option>
                      {this.state.doctors.map(doctor => {
                        return (
                          <option value={doctor.id} key={doctor.id}>
                            {doctor.first_name} {doctor.last_name}
                          </option>
                        );
                      })}
                    </select>
                  </div>
                  <div className="form-group">
                    <input
                      type="datetime-local"
                      className="form-control"
                      id="form_create-appointment_appointment_date"
                      onChange={this.handleChange}
                    />
                  </div>
                  <button
                    type="submit"
                    onClick={this.handleSubmit}
                    className="btn btn-primary"
                  >
                    Create Appointment
                  </button>
                </form>
              </div>
            </div>
          ) : null}
        </div>
      </div>
    );
  }
}
