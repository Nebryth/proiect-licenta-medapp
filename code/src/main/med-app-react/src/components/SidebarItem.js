import React from "react";

function SidebarItem(props) {
  return (
    <li className="nav-item">
      <a className="nav-link active" href={props.href}>
        {props.title}
      </a>
    </li>
  );
}

export default SidebarItem;
