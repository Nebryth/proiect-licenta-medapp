import React, { Component } from "react";
import ApiClient from "../../services/apiClient";

export default class AddPatient extends Component {
  constructor(props) {
    super(props);
    this.state = {
      patient: {}
    };
  }

  handleChange = event => {
    this.setState({
      patient: {
        ...this.state.patient,
        [event.target.id.replace("form_create-patient_", "")]: event.target
          .value
      }
    });
  };

  handleSubmit = event => {
    event.preventDefault();
    this.props.sendPatient({});
    ApiClient.createPatientFetch(this.state.patient).then(data => {
      if (data.ok === true) window.location.reload();
    });
  };

  componentWillReceiveProps(nextProps) {
    this.setState({ patient: nextProps.patient });
  }

  render() {
    return (
      <div>
        <h2 className="text-muted blockquote text-center">
          Create new patient
        </h2>
        <form className="text-center">
          <div className="form-group">
            <input
              type="text"
              className="form-control"
              id="form_create-patient_first_name"
              placeholder="First Name"
              onChange={this.handleChange}
              value={this.state.patient.first_name || ""}
            />
          </div>
          <div className="form-group">
            <input
              type="text"
              className="form-control"
              id="form_create-patient_last_name"
              placeholder="Last Name"
              onChange={this.handleChange}
              value={this.state.patient.last_name || ""}
            />
          </div>
          <div className="form-group">
            <input
              type="text"
              className="form-control"
              id="form_create-patient_address"
              placeholder="Address"
              onChange={this.handleChange}
              value={this.state.patient.address || ""}
            />
          </div>
          <div className="form-group">
            <input
              type="telephone"
              className="form-control"
              id="form_create-patient_telephone"
              placeholder="Telephone"
              onChange={this.handleChange}
              value={this.state.patient.telephone || ""}
            />
          </div>
          <div className="form-group">
            <input
              type="email"
              className="form-control"
              id="form_create-patient_email"
              placeholder="Email"
              onChange={this.handleChange}
              value={this.state.patient.email || ""}
            />
          </div>
          <button
            type="submit"
            // disabled={!this.validateForm()}
            onClick={this.handleSubmit}
            className="btn btn-primary"
          >
            Save Patient Details
          </button>
        </form>
      </div>
    );
  }
}
