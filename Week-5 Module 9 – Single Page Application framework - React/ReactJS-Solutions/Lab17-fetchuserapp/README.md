# Lab 17 - fetchuserapp (Consuming REST APIs)

```
npx create-react-app fetchuserapp
cd fetchuserapp
# copy src/Getuser.js, src/App.js
npm start
```
`Getuser` calls `https://api.randomuser.me/` inside `componentDidMount()`
using `fetch`, stores the result in state, and the `render()` method
displays the user's title, first name, and picture.
