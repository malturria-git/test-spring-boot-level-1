import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { borrarPersona } from '../APIS/apiPersona';


const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
}));

export const BajaPersona = () => {
  const classes = useStyles();

    function handleInputChange(evt) {
        const value = evt.target.value;
        setPersona({
        ...persona,
        [evt.target.name]: value
    });
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        borrarPersona(persona);
    }

    const [persona, setPersona] = React.useState({
        rut: ""
      });

  return (
    <>
    <form className={classes.root} noValidate autoComplete="off">
        <TextField id="outlined-Rut" name="rut" label="Rut a borrar" variant="outlined" value={persona.rut} onChange={handleInputChange} />
        <Button id="outlined-botonBorrar" name="outlined-botonBorrar" variant="contained" size="large" color="primary" onClick={ handleSubmit } >
            Borrar
        </Button>
    </form>

</>
  );
}
