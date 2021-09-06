import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { login } from '../APIS/apiUser';


const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
}));
  
export const Login = () => {
  const classes = useStyles();

    function handleInputChange(evt) {
        const value = evt.target.value;
        setUser({
        ...user,
        [evt.target.name]: value
    });
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        login(user);
    }

    const [user, setUser] = React.useState({
        user: "",
        password: ""
      });

  return (
    <>
    <form className={classes.root} noValidate autoComplete="off" style={{backgroundColor:'white'}} >
        <TextField id="outlined-User" name="user" label="Usuario" variant="outlined" value={user.user} onChange={handleInputChange} />
        <TextField id="outlined-password" name="password" label="Password" variant="outlined" value={user.password} onChange={handleInputChange} />
        <Button size="small"  id="outlined-Login" name="outlined-Login" variant="contained" color="primary" onClick={ handleSubmit } >
            Login
        </Button>
      </form>
    </>
  );
}
