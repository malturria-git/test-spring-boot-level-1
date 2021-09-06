import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { borrarCC } from '../APIS/apiCC';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
}));

export const BajaCC = () => {
  const classes = useStyles();

    function handleInputChange(evt) {
        const value = evt.target.value;
        setCuentaCorriente({
        ...cuentaCorriente,
        [evt.target.name]: value
    });
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        borrarCC(cuentaCorriente);
    }

    const [cuentaCorriente, setCuentaCorriente] = React.useState({
        cuenta: ""
      });

  return (
    <>
    <form className={classes.root} noValidate autoComplete="off" style={{backgroundColor:'white'}}>
        <TextField id="outlined-Cuenta" name="cuenta" label="Cuenta a borrar" variant="outlined" value={cuentaCorriente.cuenta} onChange={handleInputChange} />
        <Button id="outlined-botonBorrar" name="outlined-botonBorrar" variant="contained" size="small" color="primary" onClick={ handleSubmit } style={{float: 'right'}} >
            Borrar Cuenta Corriente
        </Button>
    </form>

</>
  );
}
