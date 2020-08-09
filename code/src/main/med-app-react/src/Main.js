import React, { Component } from "react";
import { BrowserRouter as Router } from "react-router-dom";
import "./styles/Main.scss";
import NonLoggedIn from "./components/NonLoggedIn";
import SessionManager from "./services/sessionManager";
import LoggedIn from "./components/LoggedIn";
import Sidebar from "./components/Sidebar";

class Main extends Component {
  componentWillMount() {
    if (!SessionManager.hasUserSession() && window.location.pathname !== "/")
      window.location.replace("/");
  }
  render() {
    return (
      <Router>
        <div className="container-fluid">
          <div className="row">
            {SessionManager.hasUserSession() && (
              <Sidebar
                accountType={SessionManager.getUserProperty("user_id")}
              />
            )}
            <div
              className={
                (SessionManager.hasUserSession()
                  ? "col-md-9 col-lg-10"
                  : "col-md-12 col-lg-12") + " ml-sm-auto px-4"
              }
            >
              {SessionManager.hasUserSession() ? <LoggedIn /> : <NonLoggedIn />}
            </div>
          </div>
        </div>
      </Router>
    );
  }
}

export default Main;
