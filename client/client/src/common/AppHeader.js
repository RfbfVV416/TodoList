import React, { Component } from "react";
import { Link, NavLink } from "react-router-dom";
import "./AppHeader.css";

class AppHeader extends Component {
  render() {
    return (
      <header className="app-header">
        <div className="container">
          <div className="app-branding">
            <Link to="/" className="app-title">
              Todo List
            </Link>
          </div>
          <div className="app-options">
            <nav className="app-nav">
              {this.props.authenticated ? (
                <ul>
                  <li>
                    <NavLink to="/test">Test</NavLink>
                    <NavLink to="/create">Create task</NavLink>
                    <a onClick={this.props.onLogout}>Logout</a>
                  </li>
                </ul>
              ) : (
                <ul>
                  <li>
                    <NavLink to="/login">Login</NavLink>
                  </li>
                </ul>
              )}
            </nav>
          </div>
        </div>
      </header>
    );
  }
}

export default AppHeader;
