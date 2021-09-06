import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import MenuItem from '@material-ui/core/MenuItem';
import { guardarMovimiento } from '../APIS/apiCC';


const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
}));
const tipos = [
    {value: 'CREDITO',label: 'Crédito'},
    {value: 'DEBITO', label: 'Débito'}];

    var now = new Date();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
    var now3 = now.toISOString().slice(0,16);
  
export const AltaMovimiento = () => {
  const classes = useStyles();

    function handleInputChange(evt) {
        const value = evt.target.value;
        setMovimiento({
        ...movimiento,
        [evt.target.name]: value
    });
    }

    const [tipo, setTipo] = React.useState();

    const handleChange = (event) => {
        movimiento.tipo = event.target.value;
        setTipo(event.target.value);
      };

    const handleSubmit = (e) => {
        e.preventDefault();
        movimiento.fecha = document.getElementById('datetime-local').value;
        guardarMovimiento(movimiento);
    }

    const [movimiento, setMovimiento] = React.useState({
        cuenta: "",
        fecha: "",
        tipo: "",
        descripcion: "",
        importe: ""
      });

  return (
    <>
    <form className={classes.root} noValidate autoComplete="off" style={{backgroundColor:'white'}} >
        <TextField id="outlined-Numero" name="cuenta" label="Numero de CC" variant="outlined" value={movimiento.cuenta} onChange={handleInputChange} />
        <TextField id="datetime-local"  label="Fecha" type="datetime-local" variant="outlined" defaultValue={ now3 } className={classes.textField} InputLabelProps={{ shrink: true, }} />        
        <TextField id="outlined-select-tipos" select label="Tipo Movimiento" variant="outlined" value={movimiento.tipo} onChange={handleChange} >
          {tipos.map((option) => ( <MenuItem key={option.value} value={option.value}> {option.label}</MenuItem> ))}
        </TextField>        
        <TextField id="outlined-importe" name="importe" label="Importe" variant="outlined" value={movimiento.importe} onChange={handleInputChange} />
        <TextField id="outlined-descripcion" name="descripcion" label="Descripción" variant="outlined" value={movimiento.descripcion} onChange={handleInputChange} />
        <Button size="small"  id="outlined-botonFisica" name="outlined-botonFisica" variant="contained" color="primary" onClick={ handleSubmit }   >
            Registrar Movimiento
        </Button>
      </form>
    </>
  );
}
