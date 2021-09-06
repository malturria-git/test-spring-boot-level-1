import React, { useState } from 'react'
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';

export const HookBuscarPorRut = ({ setRut }) => {

    const [inputValue, setInputValue] = useState(''); 

    const handleInputChange = ( e ) => {
        setInputValue( e.target.value );
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        setRut(cats => cats = inputValue);
        setInputValue('');
    }

    return (
    <>
        <form onSubmit={ handleSubmit }>
            <TextField id="standard-basic" label="RUT a buscar" value={ inputValue } onChange={ handleInputChange } />
        </form>
    </>
    )
}

HookBuscarPorRut.propTypes = {
    setRut: PropTypes.func.isRequired
}
