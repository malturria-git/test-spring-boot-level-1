import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { guardarPersona } from '../APIS/apiPersona';


const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
}));

export const AltaPersona = () => {
  const classes = useStyles();

    function handleInputChange(evt) {
        const value = evt.target.value;
        setPersona({
        ...persona,
        [evt.target.name]: value
    });
    }
    function handleInputChangeFisica(evt) {
      const value = evt.target.value;
      setPersonaFisica({
      ...personaFisica,
      [evt.target.name]: value
  });
  }

    
    const handleSubmit = (e) => {
        e.preventDefault();
        guardarPersona(persona);
    }
    const handleSubmitFisica = (e) => {
      e.preventDefault();
      guardarPersona(personaFisica);
  }

    const [persona, setPersona] = React.useState({
        nombre: "",
        apellido: "",
        rut: "",
        tipo: "Juridica",
        cuentaCorriente: ""
      });
      const [personaFisica, setPersonaFisica] = React.useState({
        rut: "",
        tipo: "Fisica",
        razonSocial: "",
        anioFundacion: ""
      });

  return (
    <>
    <form className={classes.root} noValidate autoComplete="off">
        <TextField id="outlined-Tipo" name="tipo" label="Tipo" variant="outlined" value="Persona Fisica" InputProps={{ readOnly: true, }} />
        <TextField id="outlined-Rut" name="rut" label="Rut" variant="outlined" value={personaFisica.rut} onChange={handleInputChangeFisica} />
        <TextField id="outlined-Nombre" name="nombre" label="Nombre" variant="outlined" value={personaFisica.nombre} onChange={handleInputChangeFisica} />
        <TextField id="outlined-Apellido" name="apellido" label="Apellido" variant="outlined" value={personaFisica.apellido} onChange={handleInputChangeFisica} />
        <TextField id="outlined-CC" name="cuentaCorriente" label="Cuenta Corriente" variant="outlined" value={personaFisica.cuentaCorriente} onChange={handleInputChangeFisica} />
        <Button id="outlined-botonFisica" name="outlined-botonFisica" variant="contained" size="large" color="primary" onClick={ handleSubmitFisica } >
            Guardar Persona Fisica
        </Button>
    </form>
    <hr />
    <form className={classes.root} noValidate autoComplete="off">
    <TextField id="outlined-Tipo" name="tipo" label="Tipo" variant="outlined" value="Persona Juridica" InputProps={{ readOnly: true, }} />
    <TextField id="outlined-Rut" name="rut" label="Rut" variant="outlined" value={persona.rut} onChange={handleInputChange} />
    <TextField id="outlined-RazonSocial" name="razonSocial" label="Razon Social" variant="outlined" value={persona.razonSocial} onChange={handleInputChange} />
    <TextField id="outlined-AnioFundacion" name="anioFundacion" label="Anio de Fundacion" variant="outlined" value={persona.anioFundacion } onChange={handleInputChange} />
    <Button id="outlined-botonJuridica" name="outlined-botonJuridica" variant="contained" size="large" color="primary" onClick={ handleSubmit } >
        Guardar Persona Juridica
    </Button>
</form>    
</>
  );
}
