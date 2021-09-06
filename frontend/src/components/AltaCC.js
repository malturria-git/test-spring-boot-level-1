import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import MenuItem from '@material-ui/core/MenuItem';
import { guardarCC } from '../APIS/apiCC';


const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
}));
const currencies = [
    {value: 'DOLAR',label: 'Dolar'},
    {value: 'EURO', label: 'Euro'},
    {value: 'PESO', label: 'Peso'}];
  
  
export const AltaCC = () => {
  const classes = useStyles();

    function handleInputChange(evt) {
        const value = evt.target.value;
        setCuentaCorriente({
        ...cuentaCorriente,
        [evt.target.name]: value
    });
    }

    const [currency, setCurrency] = React.useState('EUR');

    const handleChange = (event) => {
        cuentaCorriente.moneda = event.target.value;
        setCurrency(event.target.value);
      };

    const handleSubmit = (e) => {
        e.preventDefault();
        guardarCC(cuentaCorriente);
    }

    const [cuentaCorriente, setCuentaCorriente] = React.useState({
        cuenta: "",
        moneda: "",
        saldo: ""
      });

  return (
    <>
    <form className={classes.root} noValidate autoComplete="off" style={{backgroundColor:'white'}} >
        <TextField id="outlined-Numero" name="cuenta" label="Numero de CC" variant="outlined" value={cuentaCorriente.cuenta} onChange={handleInputChange} />
        <TextField id="outlined-select-currency" select label="Moneda" variant="outlined" value={cuentaCorriente.moneda} onChange={handleChange} >
          {currencies.map((option) => ( <MenuItem key={option.value} value={option.value}> {option.label}</MenuItem> ))}
        </TextField>        
        <TextField id="outlined-SaldoInicial" name="saldo" label="Saldo Inicial" variant="outlined" value={cuentaCorriente.saldo} onChange={handleInputChange} />
        <Button size="small"  id="outlined-botonFisica" name="outlined-botonFisica" variant="contained" color="primary" onClick={ handleSubmit } >
            Registrar Cuenta Corriente
        </Button>
      </form>
    </>
  );
}
