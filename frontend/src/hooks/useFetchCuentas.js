import { useState, useEffect } from 'react'
import { buscarTodasCuentas } from '../APIS/apiCC';


export const useFetchCuentas = ( ) => {
    const [state, setState] = useState({
        data: [],
        loading: true
    });
    useEffect( () => {

        buscarTodasCuentas( )
            .then( cuenta => {
                setState({
                    data: cuenta,
                    loading: false
                });
            })

    }, [])
    return state; 
}


