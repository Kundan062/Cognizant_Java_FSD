# ReactJS Hands-On Lab Solutions — Digital Nurture Java FSE (Deep Skilling)

This folder contains complete solutions for all 19 ReactJS hands-on
labs (ReactJS-HOL 1 through 19). Each `LabXX-*` folder is a
self-contained solution with:

- The React source files (components, styles, tests) requested by the
  problem statement
- A `README.md` explaining how to scaffold the app with
  `create-react-app` and drop the files in

## How to run any lab

```
npx create-react-app <app-name>     # name given in that lab's README
cd <app-name>
npm install <any extra packages listed in that lab's README>
# copy the src/* files from the Lab folder into the new app's src folder
npm start                            # or npm test for Lab 18 / Lab 19
```

## Lab index

| # | Folder | App Name | Topic |
|---|--------|----------|-------|
| 1 | Lab01-myfirstreact | myfirstreact | SPA & React setup, create-react-app |
| 2 | Lab02-StudentApp | StudentApp | Class components |
| 3 | Lab03-scorecalculatorapp | scorecalculatorapp | Function components & styling |
| 4 | Lab04-blogapp | blogapp | Component lifecycle (componentDidMount / componentDidCatch) |
| 5 | Lab05-CohortDetails-Styling | cohortapp | CSS Modules |
| 6 | Lab06-TrainersApp | TrainersApp | React Router (routes, Link, useParams) |
| 7 | Lab07-shoppingapp | shoppingapp | Props |
| 8 | Lab08-counterapp | counterapp | React State |
| 9 | Lab09-cricketapp | cricketapp | ES6 (map, filter, arrow fns, destructuring, spread) |
| 10 | Lab10-officespacerentalapp | officespacerentalapp | JSX & inline CSS |
| 11 | Lab11-eventexamplesapp | eventexamplesapp | Event handling & synthetic events |
| 12 | Lab12-ticketbookingapp | ticketbookingapp | Conditional rendering (element variables) |
| 13 | Lab13-bloggerapp | bloggerapp | Conditional rendering (multiple techniques) & keys |
| 14 | Lab14-ThemeContextApp | employeeapp | Context API (createContext, Provider, useContext) |
| 15 | Lab15-ticketraisingapp | ticketraisingapp | Controlled forms |
| 16 | Lab16-mailregisterapp | mailregisterapp | Form validation |
| 17 | Lab17-fetchuserapp | fetchuserapp | Consuming REST APIs (fetch) |
| 18 | Lab18-CohortDetails-Testing | cohortapp | Unit testing with Jest + Enzyme |
| 19 | Lab19-gitclientapp | gitclientapp | Jest mocking with axios |

## Notes on submission (per the handbook)

- Cross-check the **mandatory hands-on list** in
  `DN - Java FSE Mandatory hands-on detail.xlsx` (same GitHub repo) and
  prioritize those; the rest are optional/additional practice — all 19
  are solved here regardless so you have full coverage.
- To submit: create a public GitHub repository under your personal
  account, organize solutions week-wise (this folder structure already
  mirrors that), push it, and share the repo URL with your POC on
  request.
