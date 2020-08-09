import React, { Component } from "react";
import sidebarItems from "./sidebar-items";
import SidebarItem from "./SidebarItem";

export default class Sidebar extends Component {
  render() {
    const { user_id } = this.props;
    const { prefix, items } = sidebarItems[user_id];
    return (
      <div className="col-md-2 d-none d-md-block sidebar">
        <div className="logo">❤ Med App</div>
        <ul className="nav flex-column">
          {items.map(item => (
            <SidebarItem
              title={item.title}
              href={`${prefix}${item.src}`}
              key={item.title}
            />
          ))}
        </ul>
        <div className="sign-out-container">
          <div className="sign-out nav flex-column">
            <a className="nav-link active" href="/logout">
              Sign out
            </a>
          </div>
        </div>
      </div>
    );
  }
}
