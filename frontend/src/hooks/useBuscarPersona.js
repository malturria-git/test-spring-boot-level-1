import { useState, useEffect } from 'react'
import { buscarPersona } from '../APIS/apiPersona';


export const useBuscarPersona = ( rut ) => {
    const [state, setState] = useState({
        data: [],
        loading: true
    });
    useEffect( () => {

        buscarPersona( rut )
            .then( persona => {
                console.log(persona)
                setState({
                    data: persona,
                    loading: false
                });
            })

    }, [rut])
    return state; 
}


