import React from 'react';
import { useBuscarPersona } from '../hooks/useBuscarPersona';

export const BuscarPersona = ({ rut }) => {
    const { data:persona, loading } = useBuscarPersona( rut );
    if(persona != null){
        if(persona.rut != null){
            if(persona.tipo === "Fisica"){
               return (
                    <h3> El RUT { persona.rut } pertece a la persona { persona.tipo } { persona.nombre } { persona.apellido } CC: { persona.cuentaCorriente } </h3>
                )                     
			}else{
                 return (
                    <h3> El RUT { persona.rut } pertece a la persona { persona.tipo } { persona.razonSocial } fundada el { persona.anioFundacion }  </h3>
                )      
			}

        } else {
            return (
                <h3> { persona.ERROR } </h3>
            )
        
		}
	}
    return null;
}
