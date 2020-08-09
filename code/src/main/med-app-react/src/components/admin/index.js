import React, { Component } from "react";
import AddUser from "./AddUser";
import ModifyUser from "./ModifyUser";
import ViewUsersTable from "./ViewUsersTable";
// import Sidebar from "./Sidebar";

export default class AdminUsers extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: {},
      newUser: {}
    };
  }

  getSelectedUser = user => {
    this.setState({ user });
  };

  newlyCreatedUser = newUser => {
    this.setState({ newUser });
  };

  render() {
    return (
      <div className="container-fluid">
        <div className="row">
          <div className="col-lg-12 h1 text-dark mt-3">Users Management</div>
          <div className="col-lg-12 lead text-muted mb-5">
            Create, Update & Delete users
          </div>
          <div className="col-lg-6 col-md-6 col-sm-12">
            <AddUser
              sendUser={this.newlyCreatedUser}
              user={this.state.newUser}
            />
          </div>
          <div className="col-lg-6 col-md-6 col-sm-12">
            <ModifyUser
              user={this.state.user}
              sendUser={this.getSelectedUser}
            />
          </div>
          <div className="col-lg-12 col-md-12 col-sm-12">
            <ViewUsersTable sendUser={this.getSelectedUser} />
          </div>
        </div>
      </div>
    );
  }
}
