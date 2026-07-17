# Lab 19 - gitclientapp (Jest Mocking)

```
npx create-react-app gitclientapp
cd gitclientapp
npm install axios
# copy src/GitClient.js, src/App.js, src/GitClient.test.js
npm start   # run the app
npm test    # run the unit tests
```
`GitClient.getRepositories(username)` calls the GitHub REST API via
axios and returns just the repository names. `GitClient.test.js` uses
`jest.mock('axios')` to mock the HTTP call so `getRepositories()` is
tested in isolation using dummy data instead of hitting the real
`api.github.com` endpoint.
