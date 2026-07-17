import axios from 'axios';

// Lab 19: GitClient module - fetches repositories for a given GitHub user
class GitClient {
  getRepositories(username) {
    return axios
      .get(`https://api.github.com/users/${username}/repos`)
      .then((response) => response.data.map((repo) => repo.name));
  }
}

export default GitClient;
