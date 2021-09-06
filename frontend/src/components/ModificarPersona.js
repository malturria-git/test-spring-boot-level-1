import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { useBuscarPersona } from '../hooks/useBuscarPersona';
import { modificarPersona } from '../APIS/apiPersona';


const useStyles = makeStyles((theme) => ({
    root: {
      '& > *': {
        margin: theme.spacing(1),
        width: '25ch',
      },
    },
  }));

export const ModificarPersona = ({ rutModi }) => {
    const classes = useStyles();
    const { data:persona, loading } = useBuscarPersona( rutModi );
    const [personaFisicaModi, setPersonaFisicaModi] = React.useState({
        rut: "", nombre: "", apellido: "", cuentaCorriente: "", tipo: ""
      });
      const [personaJuridicaModi, setPersonaJuridicaModi] = React.useState({
        rut: "", tipo: "", razonSocial: "", anioFundacion: ""
      });
      

    function handleInputChangeFisica(evt) {
    const value = evt.target.value;
    setPersonaFisicaModi({
        ...personaFisicaModi, [evt.target.name]: value
    });
    }

    function handleInputChangeJuridica(evt) {
    const value = evt.target.value;
    setPersonaJuridicaModi({
        ...personaJuridicaModi, [evt.target.name]: value
    });
    }
    
    const handleSubmitFisica = (e) => {
        e.preventDefault();
        personaFisicaModi.tipo = persona.tipo;
        personaFisicaModi.rut = persona.rut;
       
        modificarPersona(personaFisicaModi);
    }
    const handleSubmitJuridica = (e) => {
        e.preventDefault();
        personaJuridicaModi.tipo = persona.tipo;
        personaJuridicaModi.rut = persona.rut;
        modificarPersona(personaJuridicaModi);
    }    
    if(persona != null){
        if(persona.rut != null){
            if(persona.tipo === "Fisica"){
               return (
                <form className={classes.root} noValidate autoComplete="off">
                    <TextField disabled id="outlined-Rut" name="rut" label="Rut a modificar" variant="outlined" value={persona.rut} InputProps={{ readOnly: true, }} />
                    <TextField disabled id="outlined-Tipo" name="tipo" label="Tipo" variant="outlined" value="Persona Fisica" InputProps={{ readOnly: true, }} />
                    <div/>
                    <TextField disabled id="outlined-Nombre" name="nombre" label="Nombre anterior" variant="outlined" value={persona.nombre} />
                    <TextField id="outlined-NombreN" name="nombre" label="Nuevo Nombre" variant="outlined" value={personaFisicaModi.nombre} onChange={handleInputChangeFisica} />
                    <div/>
                    <TextField disabled id="outlined-Apellido" name="apellido" label="Apellido anterior" variant="outlined" value={persona.apellido} />
                    <TextField id="outlined-ApellidoN" name="apellido" label="Nuevo Apellido" variant="outlined" value={personaFisicaModi.apellido} onChange={handleInputChangeFisica} />
                    <div/>
                    <TextField disabled id="outlined-CC" name="cuentaCorriente" label="Cuenta Corriente anterior" variant="outlined" value={persona.cuentaCorriente}  />
                    <TextField id="outlined-CCN" name="cuentaCorriente" label="Nueva Cuenta Corriente" variant="outlined" value={personaFisicaModi.cuentaCorriente} onChange={handleInputChangeFisica} />
                    <Button id="outlined-botonModi" name="outlined-botonModi" variant="contained" size="large" color="primary" onClick={ handleSubmitFisica } >
                        Modificar
                    </Button>
                </form>
               )                     
			}else{
                 return (
                    <form className={classes.root} noValidate autoComplete="off">
                    <TextField disabled id="outlined-Rut" name="rut" label="Rut a modificar" variant="outlined" value={persona.rut} InputProps={{ readOnly: true, }} />
                    <TextField disabled id="outlined-Tipo" name="tipo" label="Tipo" variant="outlined" value="Persona Juridica" InputProps={{ readOnly: true, }} />
                    <div/>
                    <TextField disabled id="outlined-RazonSocial" name="razonSocial" label="Razon Social Anterior" variant="outlined" value={persona.razonSocial} />
                    <TextField id="outlined-RazonSocialN" name="razonSocial" label="Nueva Razon Social" variant="outlined" value={personaJuridicaModi.razonSocial} onChange={ handleInputChangeJuridica } />
                    <div/>
                    <TextField disabled id="outlined-anioFundacion" name="anioFundacion" label="Año de Fundacion anterior" variant="outlined" value={persona.anioFundacion }/>
                    <TextField id="outlined-anioFundacion" name="anioFundacion" label="Nuevo Año de Fundacion" variant="outlined" value={personaJuridicaModi.anioFundacion } onChange={ handleInputChangeJuridica } />

                    <Button id="outlined-botonModi" name="outlined-botonModi" variant="contained" size="large" color="primary" onClick={ handleSubmitJuridica } >
                        Modificar
                    </Button>
                </form>                )      
			}

        } else {
            return (
                <h3 className="animate__animated animate__fadeIn"> { persona.ERROR } </h3>
            )
        
		}
	}
    return null;
}
