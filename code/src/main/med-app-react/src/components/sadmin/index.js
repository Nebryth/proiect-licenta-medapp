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
        <div>
            <h1>
                DID I REALLY MAKE IT?
            </h1>
        </div>
    );
  }
}
