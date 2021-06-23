import React, { useState } from 'react';
import TodoForm from './TodoForm';
import { RiCloseCircleLine } from 'react-icons/ri';
import { TiEdit } from 'react-icons/ti';
import MultipleSelect from "../util/MultipleSelect";


const Todo = ({ todos, completeTodo, removeTodo, updateTodo }) => {
    const [edit, setEdit] = useState({
        id: null,
        value: '',
        selectValue: []
    });


    const submitUpdate = (value, selectValue) => {
        updateTodo(edit.id, value, selectValue);
        setEdit({
            id: null,
            value: '',
            selectValue: []
        });
    };

    if (edit.id) {
        return <TodoForm edit={edit} onSubmit={submitUpdate} />;
    }


    return todos.map((todo, index) => (
        <div
            className={todo.isComplete ? 'todo-row complete' : 'todo-row'}
            key={index}
        >
            <div key={todo.id} onClick={() => completeTodo(todo.id)}>
                <p className="info">
                    <label>
                        <input
                            className='todo-info'
                            name = 'title'
                            value={todo.title}
                        />
                    </label>
                    <label>
                        Enter the deadline
                        <input
                            type="date"
                            name= 'deadline'
                            value={todo.deadline}
                            className='todo-info' />
                    </label>
                    <label>
                        Pick up task status
                        <select
                            className='todo-info'
                            name='status'
                            value={todo.status}
                        >
                            <option value="waiting">waiting</option>
                            <option value="in progress">in progress</option>
                            <option value="finished">finished</option>
                        </select>
                    </label>
                    <label >
                        <input
                            className='todo-new-category'
                            name='newCategory'
                            value={todo.newCategory}
                        />
                    </label>
                    <button  className='todo-button'>
                        +
                    </button>
                </p>
                <label>
                    <br/>
                    Categories
                    <label className='todo-categories'>
                        {todo.categories.map((item, key) =>
                            item + " "
                        )}
                    </label>
                </label>
                <br />
                <input
                    value={todo.text}
                    name='text'
                    className='todo-input edit'
                />
            </div>
            <div className='icons'>
                <RiCloseCircleLine
                    onClick={() => removeTodo(todo.id)}
                    className='delete-icon'
                />
                <TiEdit
                    onClick={() => setEdit({ id: todo.id, value: todo, selectValue: todo.categories})}
                    className='edit-icon'
                />
            </div>
        </div>
    ));
};

export default Todo;