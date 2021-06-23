
import React, { useState, useEffect, useRef } from 'react';
import {CurrentDate} from "../util/CurrentDate";
import MultipleSelect from "../util/MultipleSelect";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import Input from "@material-ui/core/Input";
import MenuItem from "@material-ui/core/MenuItem";
import { makeStyles, useTheme } from '@material-ui/core/styles';

function TodoForm(props) {
    const [input, setInput] = useState(props.edit ? props.edit.value.text : '');
    const [title, setTitle] = useState(props.edit ? props.edit.value.title : '');
    const [deadline, setDeadline] = useState(props.edit ? props.edit.deadline : CurrentDate());
    const [newCategory, setNewCategory] = useState(props.edit ? props.edit.newCategory : '');
    const [status, setStatus] = useState(props.edit ? props.edit.status : '');
    const [categories, setCategories] = useState(props.edit ? props.edit.selectValue : []);

    const handleChange = e => {
        setInput(e.target.value);
    };
    const handleTitleChange = e => { setTitle(e.target.value); };
    const handleDeadlineChange = e => { setDeadline(e.target.value); };
    const handleNewCategoryChange = e => { setNewCategory(e.target.value); };
    const handleStatusChange = e => { setStatus(e.target.value); };


    const names = [
        'Oliver Hansen',
        'Van Henry',
        'April Tucker',
        'Ralph Hubbard',
        'Omar Alexander',
        'Carlos Abbott',
        'Miriam Wagner',
        'Bradley Wilkerson',
        'Virginia Andrews',
        'Kelly Snyder',
    ];

    const handleSubmit = e => {
        e.preventDefault();
        props.onSubmit({
            id: Math.floor(Math.random() * 100000),
            text: input,
            title: title,
            deadline: deadline,
            status: status,
            newCategory: newCategory,
            categories: categories

        });
        setInput('');
        setTitle('');
        setDeadline(CurrentDate());
        setStatus('');
        setNewCategory('');
        setCategories([]);
    };


    const useStyles = makeStyles((theme) => ({
        formControl: {
            minWidth: 1290,
            maxWidth: 1290,
        },
        chips: {
            display: 'flex',
            flexWrap: 'wrap',
        },
        chip: {
            margin: 2,
        },
        noLabel: {
        },
    }));

    const ITEM_HEIGHT = 48;
    const ITEM_PADDING_TOP = 8;
    const MenuProps = {
        PaperProps: {
            style: {
                maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
                width: 200,
            },
        },
    };


    function getStyles(name, personName, theme) {
        return {
            fontWeight:
                personName.indexOf(name) === -1
                    ? theme.typography.fontWeightRegular
                    : theme.typography.fontWeightMedium,
        };
    }

    const classes = useStyles();
    const theme = useTheme();
    const [personName, setPersonName] = React.useState([]);

    const handleMultiSelectChange = event => {
        setCategories(event.target.value);
    };


    return (
        <form onSubmit={handleSubmit} className='todo-form'>
            {props.edit ? (
                <>
                    <p className="info">
                        <label>
                            <input
                                placeholder='Title'
                                className='todo-info'
                                name = 'title'
                                value={title}
                                onChange={handleTitleChange}
                            />
                        </label>
                        <label>
                            Enter the deadline
                            <input
                                placeholder='Due Date'
                                type="date"
                                name= 'deadline'
                                value={deadline}
                                onChange={handleDeadlineChange}
                                className='todo-info' />
                        </label>
                        <label>
                            Pick up task status
                            <select
                                className='todo-info'
                                name='status'
                                value={status}
                                onChange={handleStatusChange}
                            >
                                <option value="waiting">waiting</option>
                                <option value="in progress">in progress</option>
                                <option value="finished">finished</option>
                            </select>
                        </label>
                        <label >
                            <input
                                placeholder='New Category'
                                className='todo-new-category'
                                name='newCategory'
                                value={newCategory}
                                onChange={handleNewCategoryChange}
                            />
                        </label>
                        <button  className='todo-button'>
                            +
                        </button>
                    </p>
                    <label>Pick up existing categories
                        <br/>
                        <label
                            className='todo-categories'
                        >
                            <div>
                                <FormControl className={classes.formControl}>
                                    <Select
                                        labelId="demo-mutiple-name-label"
                                        id="demo-mutiple-name"
                                        multiple
                                        name="categories"
                                        value={categories}
                                        onChange={handleMultiSelectChange}
                                        input={<Input />}
                                        MenuProps={MenuProps}
                                    >
                                        {names.map((name) => (
                                            <MenuItem key={name} value={name} style={getStyles(name, personName, theme)}>
                                                {name}
                                            </MenuItem>
                                        ))}
                                    </Select>
                                </FormControl>

                            </div>
                        </label>
                    </label>
                    <br />
                    <input
                        placeholder='Update your item'
                        value={input}
                        onChange={handleChange}
                        name='text'
                        className='todo-input edit'
                    />
                    <button onClick={handleSubmit} className='todo-button edit'>
                        Update
                    </button>
                </>
            ) : (
                <>
                    <p className="info">
                        <label>
                            <input
                                placeholder='Title'
                                className='todo-info'
                                name = 'title'
                                value={title}
                                onChange={handleTitleChange}
                            />
                        </label>
                        <label>
                            Enter the deadline
                            <input
                                placeholder='Due Date'
                                type="date"
                                name= 'deadline'
                                value={deadline}
                                defaultValue={CurrentDate()}
                                onChange={handleDeadlineChange}
                                className='todo-info' />
                        </label>
                        <label>
                            Pick up task status
                            <select
                                className='todo-info'
                                name='status'
                                value={status}
                                onChange={handleStatusChange}
                            >
                                <option value="waiting">waiting</option>
                                <option value="in progress">in progress</option>
                                <option value="finished">finished</option>
                            </select>
                        </label>
                        <label >
                            <input
                                placeholder='New Category'
                                className='todo-new-category'
                                name='newCategory'
                                value={newCategory}
                                onChange={handleNewCategoryChange}
                            />
                        </label>
                        <button  className='todo-button'>
                            +
                        </button>
                    </p>
                    <label>Pick up existing categories
                        <br/>
                        <label
                            className='todo-categories'
                        >
                            <div>
                                <FormControl className={classes.formControl}>
                                    <Select
                                        labelId="demo-mutiple-name-label"
                                        id="demo-mutiple-name"
                                        multiple
                                        name="categories"
                                        value={categories}
                                        onChange={handleMultiSelectChange}
                                        input={<Input />}
                                        MenuProps={MenuProps}
                                    >
                                        {names.map((name) => (
                                            <MenuItem key={name} value={name} style={getStyles(name, personName, theme)}>
                                                {name}
                                            </MenuItem>
                                        ))}
                                    </Select>
                                </FormControl>

                            </div>
                        </label>
                    </label>
                    <br />
                    <input
                        placeholder='Add a task'
                        value={input}
                        onChange={handleChange}
                        name='text'
                        className='todo-input'
                    />
                    <button onClick={handleSubmit} className='todo-button'>
                        Add
                    </button>
                </>
            )}
        </form>

    );
}

export default TodoForm;