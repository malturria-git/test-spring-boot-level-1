import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Accordion from '@material-ui/core/Accordion';
import AccordionDetails from '@material-ui/core/AccordionDetails';
import AccordionSummary from '@material-ui/core/AccordionSummary';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import { BuscarPersona } from '../components/BuscarPersona';
import { ModificarPersona } from '../components/ModificarPersona';
import { HookBuscarPorRut } from '../components/HookBuscarPorRut';
import { AltaPersona } from '../components/AltaPersona';
import { BajaPersona } from '../components/BajaPersona';
import { BuscarParaModificar } from '../components/BuscarParaModificar';
import { ListaPersonas } from '../components/ListaPersonas';
import Button from '@material-ui/core/Button';


const useStyles = makeStyles((theme) => ({
  root: {
    width: '100%',
  },
  heading: {
    fontSize: theme.typography.pxToRem(15),
    flexBasis: '100%',
    flexShrink: 0,
  },
  secondaryHeading: {
    fontSize: theme.typography.pxToRem(15),
    color: theme.palette.text.secondary,
  },
  detailRoot: {
    display: "initial",
  }

}));


export default function ControlledAccordions() {


  const classes = useStyles();
  const [expanded, setExpanded] = React.useState(false);

  const [rut, setRut] = useState([]);
  const [rutModi, setRutModi] = useState([]);

  const handleChange = (panel) => (event, isExpanded) => {
    setExpanded(isExpanded ? panel : false);
  };

  return (
  <>
    <h1 style={{textAlign: 'center'}}> Bienvenido al CRUD de Personas.</h1>
    <div style={{margin:"3%"}}>

    <div className={classes.root}>
      <Accordion expanded={expanded === 'panel1'} onChange={handleChange('panel1')}>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1bh-content"
          id="panel1bh-header"
        >
          <Typography className={classes.heading}>Buscar Persona por RUT - Ingrese el RUT y presione Enter</Typography>
        </AccordionSummary>
        <AccordionDetails>
        <Typography component={'div'}>

            <HookBuscarPorRut setRut={ setRut } />
            <BuscarPersona rut = { rut }/>
                  
          </Typography>
        </AccordionDetails>
      </Accordion>
      <Accordion expanded={expanded === 'panel5'} onChange={handleChange('panel5')}>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel5bh-content"
          id="panel5bh-header"
        >
          <Typography className={classes.heading}>Listar todos</Typography>
        </AccordionSummary>
        <AccordionDetails classes={{ root: classes.detailRoot }}>
        <Typography component={'div'}>
        
        <ListaPersonas />

          </Typography>
        </AccordionDetails>
      </Accordion>
      <Accordion expanded={expanded === 'panel2'} onChange={handleChange('panel2')}>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel2bh-content"
          id="panel2bh-header"
        >
          <Typography className={classes.heading}>Alta de Personas</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography component={'div'}>

          <AltaPersona />

          </Typography>
        </AccordionDetails>
      </Accordion>
      <Accordion expanded={expanded === 'panel3'} onChange={handleChange('panel3')}>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel3bh-content"
          id="panel3bh-header"
        >
          <Typography className={classes.heading}>Modificar Personas</Typography>
        </AccordionSummary>
        <AccordionDetails>
        <Typography component={'div'}>

          <BuscarParaModificar setRutModi={ setRutModi } />
          <ModificarPersona rutModi = { rutModi }/>

          </Typography>
        </AccordionDetails>
      </Accordion>
      <Accordion expanded={expanded === 'panel4'} onChange={handleChange('panel4')}>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel4bh-content"
          id="panel4bh-header"
        >
          <Typography className={classes.heading}>Borrar persona</Typography>
        </AccordionSummary>
        <AccordionDetails>
        <Typography component={'div'}>

          <BajaPersona />

        </Typography>
        </AccordionDetails>
      </Accordion>
    </div>
    </div>
  </>
  );
}
