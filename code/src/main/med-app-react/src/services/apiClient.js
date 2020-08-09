// import SessionManager from "./sessionManager";

const baseUrl = process.env.BASE_URL || "http://localhost:8080";

class ApiClient {

  static usersFetch = () => {
    return fetch(`${baseUrl}/api/users`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "GET"
    }).then(data => data.json());
  };



















  static loginFetch = (email, password) => {
    const loginBody = {
      email,
      password
    };

    return fetch(`${baseUrl}/api/auth`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "POST",
      body: JSON.stringify(loginBody)
    }).then(data => data.json());
  };

  static usersFetch = () => {
    return fetch(`${baseUrl}/api/users`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "GET"
    }).then(data => data.json());
  };

  static createUserFetch = user => {
    return fetch(`${baseUrl}/api/users`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "POST",
      body: JSON.stringify(user)
    });
  };

  static updateUserFetch = user => {
    return fetch(`${baseUrl}/api/users`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "PATCH",
      body: JSON.stringify(user)
    });
  };

  static deleteUserFetch = user => {
    return fetch(`${baseUrl}/api/users`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "DELETE",
      body: JSON.stringify(user)
    });
  };

  static doctorsFetch = () => {
    return fetch(`${baseUrl}/api/users?userRole=doctor`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "GET"
    }).then(data => data.json());
  };

  // ! Appointments

  static appointmentsFetch = (doctor_id = 0) => {
    return fetch(
      `${baseUrl}/api/appointments${doctor_id ? `?user_id=${doctor_id}` : ""}`,
      {
        headers: {
          "Content-Type": "application/json"
        },
        method: "GET"
      }
    ).then(data => data.json());
  };

  static appointmentsBetweenFetch = (begin, end, doctor_id = 0) => {
    return fetch(
      `${baseUrl}/api/appointments?begin_date=${begin}&end_date=${end}${
        doctor_id ? `&user_id=${doctor_id}` : ""
      }`,
      {
        headers: {
          "Content-Type": "application/json"
        },
        method: "GET"
      }
    ).then(data => data.json());
  };

  static createAppointmentFetch = appointment => {
    return fetch(`${baseUrl}/api/appointments`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "POST",
      body: JSON.stringify({
        ...appointment,
        appointment_type: "new"
      })
    });
  };

  static updateAppointmentFetch = appointment => {
    return fetch(`${baseUrl}/api/appointments`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "PATCH",
      body: JSON.stringify(appointment)
    });
  };

  // ! Patients
  static patientsFetch = () => {
    return fetch(`${baseUrl}/api/patients`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "GET"
    }).then(data => data.json());
  };

  static createPatientFetch = user => {
    return fetch(`${baseUrl}/api/patients`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "POST",
      body: JSON.stringify(user)
    });
  };

  static updatePatientFetch = user => {
    return fetch(`${baseUrl}/api/patients`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "PATCH",
      body: JSON.stringify(user)
    });
  };

  static deletePatientFetch = user => {
    return fetch(`${baseUrl}/api/patients`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "DELETE",
      body: JSON.stringify(user)
    });
  };

  static patientFullNameFetch = patient_id => {
    return fetch(`${baseUrl}/api/patients?patient_id=${patient_id}`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "GET"
    }).then(data => data.json());
  };
}

export default ApiClient;
