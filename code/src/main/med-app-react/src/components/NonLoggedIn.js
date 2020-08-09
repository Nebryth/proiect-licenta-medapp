import React, { Component, Fragment } from "react";
import LoginForm from "./LoginForm";

class NonLoggedIn extends Component {
  render() {
    return (
      <Fragment>
        <h1 className="medapp-title text-center">Med App</h1>
        <h4 className="medapp-subtitle text-center">
          Enter your credentials in order to continue.
        </h4>
        <LoginForm />
      </Fragment>
    );
  }
}

export default NonLoggedIn;
