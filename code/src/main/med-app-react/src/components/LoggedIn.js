import React, { Component } from "react";
import { Route, withRouter, Redirect } from "react-router-dom";
import AdminUsers from "./admin";
import sidebarItems from "./sidebar-items";
import SessionManager from "../services/sessionManager";
import Logout from "./Logout";
import Appointments from "./doctor/Appointments";
import AdminPatients from "./patients";

class LoggedIn extends Component {
  constructor(props) {
    super(props);
    this.accountType = SessionManager.getUserProperty("userRole");
  }
  render() {
    return (
      <div>
        {!SessionManager.hasPermisionForUrl(window.location.pathname) ? (
          <Redirect to={sidebarItems[this.accountType].defaultUrl} />
        ) : null}
        {!SessionManager.hasUserSession() ? window.location.replace("/") : null}
        <Route exact path="/admin/users" component={AdminUsers} />
        <Route exact path="/app/appointments" component={Appointments} />
        <Route exact path="/app/patients" component={AdminPatients} />
        <Route
          exact
          path="/app/diagnoses"
          component={() => <div>Diagnoses</div>}
        />
        <Route exact path="/logout" component={Logout} />
        {/* <Route exact path="/presence" component={info} /> */}
      </div>
    );
  }
}

export default withRouter(LoggedIn);
