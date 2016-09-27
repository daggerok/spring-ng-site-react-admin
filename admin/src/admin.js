/**
 * Created by mak on 9/26/16.
 */
import 'bootstrap/dist/css/bootstrap.css';
import './admin.css';

import React from 'react';
import ReactDOM from 'react-dom';
import users from './user/users';

const App = () => (
  <ul>
    {users.map((user, i) => <div key={i}>{user.username}</div>)}
  </ul>
);

ReactDOM.render(
  <App />,
  document.getElementById('admin-app')
);
