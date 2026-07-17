# Lab 04 - blogapp (Life-cycle hooks)

```
npx create-react-app blogapp
cd blogapp
# copy src/Post.js, src/Posts.js, src/App.js from this folder
npm start
```
`Posts` fetches data from https://jsonplaceholder.typicode.com/posts
inside `componentDidMount()` and stores it in state. `componentDidCatch()`
alerts the user if a rendering error happens in this component subtree.
