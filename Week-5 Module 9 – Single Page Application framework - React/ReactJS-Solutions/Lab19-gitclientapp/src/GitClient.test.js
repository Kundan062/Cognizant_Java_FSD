import axios from 'axios';
import GitClient from './GitClient';

jest.mock('axios');

describe('Git Client Tests', () => {
  test('should return repository names for techiesyed', () => {
    const mockResponse = {
      data: [
        { name: 'repo-one' },
        { name: 'repo-two' },
        { name: 'repo-three' }
      ]
    };

    axios.get.mockResolvedValue(mockResponse);

    const gitClient = new GitClient();

    return gitClient.getRepositories('techiesyed').then((repoNames) => {
      expect(repoNames).toEqual(['repo-one', 'repo-two', 'repo-three']);
      expect(axios.get).toHaveBeenCalledWith(
        'https://api.github.com/users/techiesyed/repos'
      );
    });
  });
});
