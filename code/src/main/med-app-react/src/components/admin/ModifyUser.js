import React, { Component } from "react";
import ApiClient from "../../services/apiClient";

export default class ModifyUser extends Component {
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
        [event.target.id.replace("form_modify-user_", "")]: event.target.value
      }
    });
  };

  handleSubmit = event => {
    event.preventDefault();
    this.props.sendUser({});
    ApiClient.updateUserFetch(this.state.user).then(data => {
      if (data.ok === true) window.location.reload();
    });
  };

  componentWillReceiveProps(nextProps) {
    this.setState({ user: nextProps.user });
  }

  render() {
    return (
      <div>
        <h2 className="text-muted blockquote text-center">
          Modify existing user
        </h2>
        <form className="text-center">
          <div className="form-group">
            <input
              type="text"
              className="form-control"
              id="form_modify-user_first_name"
              placeholder="First Name"
              onChange={this.handleChange}
              value={this.state.user.first_name || ""}
              disabled={!(Object.keys(this.state.user).length > 0)}
            />
          </div>
          <div className="form-group">
            <input
              type="text"
              className="form-control"
              id="form_modify-user_last_name"
              placeholder="Last Name"
              onChange={this.handleChange}
              value={this.state.user.last_name || ""}
              disabled={!(Object.keys(this.state.user).length > 0)}
            />
          </div>
          <div className="form-group">
            <input
              type="text"
              className="form-control"
              id="form_modify-user_username"
              placeholder="Username"
              onChange={this.handleChange}
              value={this.state.user.username || ""}
              disabled={!(Object.keys(this.state.user).length > 0)}
            />
          </div>
          <div className="form-group">
            <input
              type="password"
              className="form-control"
              id="form_modify-user_password"
              autoComplete="new-password"
              placeholder="Password"
              onChange={this.handleChange}
              disabled={!(Object.keys(this.state.user).length > 0)}
            />
          </div>
          <div className="form-group">
            <input
              type="telephone"
              className="form-control"
              id="form_modify-user_telephone"
              placeholder="Telephone"
              onChange={this.handleChange}
              value={this.state.user.telephone || ""}
              disabled={!(Object.keys(this.state.user).length > 0)}
            />
          </div>
          <div className="form-group">
            <input
              type="email"
              className="form-control"
              id="form_modify-user_email"
              placeholder="Email"
              onChange={this.handleChange}
              value={this.state.user.email || ""}
              disabled={!(Object.keys(this.state.user).length > 0)}
            />
          </div>
          <div className="form-group">
            <select
              className="form-control"
              id="form_modify-user_userRole"
              onChange={this.handleChange}
              defaultValue={this.state.user.userRole || ""}
              disabled={!(Object.keys(this.state.user).length > 0)}
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
