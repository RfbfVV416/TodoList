import React, { Component } from 'react';
import {Redirect} from "react-router-dom";
import { getAllUsers } from "../util/APIUtils";
class TaskComponent extends Component{

    state = {
        users:[]
    }

    componentDidMount(){
        getAllUsers().then((response) => {
            this.setState({ users: response})
        });
    }

    render() {
        if (!this.props.authenticated) {
            return (
                <Redirect
                    to={{
                        pathname: "/login",
                        state: { from: this.props.location }
                    }}
                />
            );
        }

        return (
            <div>
                <h1 className = "text-center"> Users List</h1>
                <table className = "table table-striped">
                    <thead>
                    <tr>
                        <td> User Id</td>
                        <td> User Name</td>
                        <td> User Email</td>
                        <td> User Picture</td>
                    </tr>

                    </thead>
                    <tbody>
                    {
                        this.state.users.map(
                            user =>
                                <tr key = {user.id}>
                                    <td> {user.id}</td>
                                    <td> {user.name}</td>
                                    <td> {user.email}</td>
                                    <td> {user.imageUrl}</td>
                                </tr>
                        )
                    }

                    </tbody>
                </table>
            </div>
        )

    }

}

export default TaskComponent;