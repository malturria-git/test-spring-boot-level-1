import React, { useState } from 'react'
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';

export const BuscarParaModificar = ({ setRutModi }) => {

    const [inputValue, setInputValue] = useState(''); 

    const handleInputChange = ( e ) => {
        setInputValue( e.target.value );
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        setRutModi(cats => cats = inputValue);
        setInputValue('');
    }

    return (
    <>
        <form onSubmit={ handleSubmit }>
            <TextField id="standard-basic" label="Rut a buscar" variant="filled" value={ inputValue } onChange={ handleInputChange } />
        </form>
    </>
    )
}

BuscarParaModificar.propTypes = {
    setRutModi: PropTypes.func.isRequired
}
