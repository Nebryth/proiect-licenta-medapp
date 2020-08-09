import React, { Component } from "react";
import AddPatient from "./AddPatient";
import ModifyPatient from "./ModifyPatient";
import ViewPatientsTable from "./ViewPatientsTable";
// import Sidebar from "./Sidebar";

export default class AdminPatients extends Component {
  constructor(props) {
    super(props);
    this.state = {
      patient: {},
      newPatient: {}
    };
  }

  getSelectedPatient = patient => {
    this.setState({ patient });
  };

  newlyCreatedPatient = newPatient => {
    this.setState({ newPatient });
  };

  render() {
    return (
      <div className="container-fluid">
        <div className="row">
          <div className="col-lg-12 h1 text-dark mt-3">Patients Management</div>
          <div className="col-lg-12 lead text-muted mb-5">
            Create, Update & Delete patients
          </div>
          <div className="col-lg-6 col-md-6 col-sm-12">
            <AddPatient
              sendPatient={this.newlyCreatedPatient}
              patient={this.state.newPatient}
            />
          </div>
          <div className="col-lg-6 col-md-6 col-sm-12">
            <ModifyPatient
              patient={this.state.patient}
              sendPatient={this.getSelectedPatient}
            />
          </div>
          <div className="col-lg-12 col-md-12 col-sm-12">
            <ViewPatientsTable sendPatient={this.getSelectedPatient} />
          </div>
        </div>
      </div>
    );
  }
}
