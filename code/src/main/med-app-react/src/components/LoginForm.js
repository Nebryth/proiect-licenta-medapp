import React, { Component } from "react";
import ApiClient from "../services/apiClient";
import SessionManager from "../services/sessionManager";
import sidebarItems from "./sidebar-items";

class LoginForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: ""
    };
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };

  validateForm = () => {
    return this.state.email.length > 0 && this.state.password.length > 0;
  };

  handleSubmit = event => {
    event.preventDefault();
    ApiClient.loginFetch(this.state.email, this.state.password).then(data => {
      SessionManager.setUserSession(data);
      if (SessionManager.hasUserSession())
        window.location.replace(sidebarItems[data.userRole].defaultUrl);
      else window.alert("Wrong credentials. Please try again");
    });
  };

  render() {
    return (
      <div className="LoginForm">
        <form onSubmit={this.handleSubmit} className="text-center">
          <div className="form-group mb-4">
            <input
              type="email"
              className="form-control"
              required
              onChange={this.handleChange}
              id="email"
              placeholder="Email address"
            />
          </div>
          <div className="form-group mb-4">
            <input
              type="password"
              className="form-control"
              required
              onChange={this.handleChange}
              id="password"
              placeholder="Password"
            />
          </div>
          <button
            type="submit"
            disabled={!this.validateForm()}
            onClick={this.handleSubmit}
            className="btn btn-primary  center-block"
          >
            Login
          </button>
        </form>
      </div>
    );
  }
}

export default LoginForm;
