import React, { Component } from "react";
import ApiClient from "../../services/apiClient";

export default class ViewUsersTable extends Component {
  constructor(props) {
    super(props);
    this.state = {
      users: []
    };
  }

  handleDelete = user => {
    const canDelete = window.confirm(
      `Are you sure you want to remove user ${user.first_name} ${
        user.last_name
      }`
    );
    if (canDelete)
      ApiClient.deleteUserFetch(user).then(data => {
        if (data.ok === true) window.location.reload();
      });
  };

  handleModify = user => {
    this.props.sendUser(user);
  };

  getUsers = () => {
    ApiClient.usersFetch().then(users => {
      this.setState({ users });
    });
  };

  componentWillMount() {
    this.getUsers();
  }

  render() {
    return (
      <div className="table-wrapper-scroll-y">
        <h2 className="text-muted blockquote text-center">Users Table</h2>
        <table className="table text-muted table-hover">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">First Name</th>
              <th scope="col">Last Name</th>
              <th scope="col">Username</th>
              <th scope="col">Telephone</th>
              <th scope="col">Email</th>
              <th scope="col">User Role</th>
              <th scope="col">Actions</th>
            </tr>
          </thead>
          <tbody>
            {this.state.users.map(user => {
              return (
                <tr key={user.id}>
                  <th scope="row">{user.id}</th>
                  <td>{user.first_name}</td>
                  <td>{user.last_name}</td>
                  <td>{user.username}</td>
                  <td>{user.telephone}</td>
                  <td>{user.email}</td>
                  <td>{user.userRole}</td>
                  <td>
                    <button
                      onClick={() => this.handleDelete(user)}
                      className="btn btn-danger btn-sm mr-2"
                    >
                      Delete
                    </button>
                    <button
                      onClick={() => this.handleModify(user)}
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
