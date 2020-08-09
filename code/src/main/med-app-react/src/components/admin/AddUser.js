import React, { Component } from "react";
import ApiClient from "../../services/apiClient";

export default class AddUser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: {}
    };
  }

  handleChange = event => {
    this.setState({
      user: {
        ...this.state.user,
        [event.target.id.replace("form_create-user_", "")]: event.target.value
      }
    });
  };

  handleSubmit = event => {
    event.preventDefault();
    this.props.sendUser({});
    ApiClient.createUserFetch(this.state.user).then(data => {
      if (data.ok === true) window.location.reload();
    });
  };

  componentWillReceiveProps(nextProps) {
    this.setState({ user: nextProps.user });
  }

  render() {
    return (
      <div>
        <h2 className="text-muted blockquote text-center">Create new user</h2>
        <form className="text-center">
          <div className="form-group">
            <input
              type="text"
              className="form-control"
              id="form_create-user_first_name"
              placeholder="First Name"
              onChange={this.handleChange}
              value={this.state.user.first_name || ""}
            />
          </div>
          <div className="form-group">
            <input
              type="text"
              className="form-control"
              id="form_create-user_last_name"
              placeholder="Last Name"
              onChange={this.handleChange}
              value={this.state.user.last_name || ""}
            />
          </div>
          <div className="form-group">
            <input
              type="text"
              className="form-control"
              id="form_create-user_username"
              placeholder="Username"
              onChange={this.handleChange}
              value={this.state.user.username || ""}
            />
          </div>
          <div className="form-group">
            <input
              type="password"
              className="form-control"
              id="form_create-user_password"
              autoComplete="new-password"
              placeholder="Password"
              onChange={this.handleChange}
              value={this.state.user.password || ""}
            />
          </div>
          <div className="form-group">
            <input
              type="telephone"
              className="form-control"
              id="form_create-user_telephone"
              placeholder="Telephone"
              onChange={this.handleChange}
              value={this.state.user.telephone || ""}
            />
          </div>
          <div className="form-group">
            <input
              type="email"
              className="form-control"
              id="form_create-user_email"
              placeholder="Email"
              onChange={this.handleChange}
              value={this.state.user.email || ""}
            />
          </div>
          <div className="form-group">
            <select
              className="form-control"
              id="form_create-user_userRole"
              onChange={this.handleChange}
              defaultValue={this.state.user.userRole || ""}
            >
              <option
                value=""
                hidden
                selected={this.state.user.userRole === "nurse"}
              >
                Select your option
              </option>
              <option
                value="admin"
                selected={this.state.user.userRole === "admin"}
              >
                Admin
              </option>
              <option
                value="doctor"
                selected={this.state.user.userRole === "doctor"}
              >
                Doctor
              </option>
              <option
                value="nurse"
                selected={this.state.user.userRole === "nurse"}
              >
                Nurse
              </option>
            </select>
          </div>
          <button
            type="submit"
            // disabled={!this.validateForm()}
            onClick={this.handleSubmit}
            className="btn btn-primary"
          >
            Save User Details
          </button>
        </form>
      </div>
    );
  }
}
