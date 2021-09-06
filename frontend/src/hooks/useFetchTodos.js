import { useState, useEffect } from 'react'
import { buscarTodos } from '../APIS/apiPersona';


export const useFetchTodos = ( ) => {
    const [state, setState] = useState({
        data: [],
        loading: true
    });
    useEffect( () => {

        buscarTodos( )
            .then( persona => {
                setState({
                    data: persona,
                    loading: false
                });
            })

    }, [])
    return state; 
}


