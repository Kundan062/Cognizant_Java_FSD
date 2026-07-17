import React, { useEffect, useState } from 'react';
import GitClient from './GitClient';
import './App.css';

const gitClient = new GitClient();

function App() {
  const [repositories, setRepositories] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    gitClient
      .getRepositories('techiesyed')
      .then((repos) => {
        setRepositories(repos);
        setLoading(false);
      })
      .catch(() => setLoading(false));
  }, []);

  return (
    <div className="App">
      <h2>GitHub Repositories</h2>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <ul>
          {repositories.map((repo, index) => (
            <li key={index}>{repo}</li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default App;
