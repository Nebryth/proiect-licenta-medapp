import React, { Component } from "react";
import ApiClient from "../../services/apiClient";

export default class ViewPatientsTable extends Component {
  constructor(props) {
    super(props);
    this.state = {
      patients: []
    };
  }

  handleDelete = patient => {
    const canDelete = window.confirm(
      `Are you sure you want to remove patient ${patient.first_name} ${
        patient.last_name
      }`
    );
    if (canDelete)
      ApiClient.deletePatientFetch(patient).then(data => {
        if (data.ok === true) window.location.reload();
      });
  };

  handleModify = patient => {
    this.props.sendPatient(patient);
  };

  getPatients = () => {
    ApiClient.patientsFetch().then(patients => {
      this.setState({ patients });
    });
  };

  componentWillMount() {
    this.getPatients();
  }

  render() {
    return (
      <div className="table-wrapper-scroll-y">
        <h2 className="text-muted blockquote text-center">Patients Table</h2>
        <table className="table text-muted table-hover">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">First Name</th>
              <th scope="col">Last Name</th>
              <th scope="col">Address</th>
              <th scope="col">Telephone</th>
              <th scope="col">Email</th>
              <th scope="col">Actions</th>
            </tr>
          </thead>
          <tbody>
            {this.state.patients.map(patient => {
              return (
                <tr key={patient.id}>
                  <th scope="row">{patient.id}</th>
                  <td>{patient.first_name}</td>
                  <td>{patient.last_name}</td>
                  <td>{patient.address}</td>
                  <td>{patient.telephone}</td>
                  <td>{patient.email}</td>
                  <td>
                    <button
                      onClick={() => this.handleDelete(patient)}
                      className="btn btn-danger btn-sm mr-2"
                    >
                      Delete
                    </button>
                    <button
                      onClick={() => this.handleModify(patient)}
                      className="btn btn-primary btn-sm"
                    >
                      Modify
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    );
  }
}
