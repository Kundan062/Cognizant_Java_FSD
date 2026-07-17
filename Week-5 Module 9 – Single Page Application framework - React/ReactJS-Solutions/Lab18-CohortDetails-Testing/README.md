# Lab 18 - CohortDetails Unit Testing (Jest + Enzyme)

```
npx create-react-app cohortapp
cd cohortapp
npm install
npm install --save-dev enzyme @wojtekmaj/enzyme-adapter-react-17 prop-types
# copy src/Cohort.js, src/CohortDetails.js, src/CohortDetails.module.css,
#      src/setupTests.js, src/CohortDetails.test.js
npm test
```

Tests defined inside the `describe('Cohort Details Component', ...)`
suite:
1. **should create the component** – shallow-mounts `CohortDetails` in isolation and checks it exists.
2. **should initialize the props** – mounts the component and asserts the `cohort` prop matches the object passed in.
3. **should display cohort code in h3** – uses `find('h3')` and verifies the text equals the cohort's `code`.
4. **should always render same html** – snapshot test of the rendered output.

> Note: If the project uses React 16, install and import
> `enzyme-adapter-react-16` instead of the React 17 adapter.
