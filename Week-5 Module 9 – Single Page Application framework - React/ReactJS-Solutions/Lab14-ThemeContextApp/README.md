# Lab 14 - Employee Management App (Context API)

```
npx create-react-app employeeapp
cd employeeapp
npm install
# copy src/ThemeContext.js, src/EmployeeData.js, src/EmployeeCard.js,
#      src/EmployeesList.js, src/App.js, src/App.css
npm start
```
`ThemeContext` (default `'light'`) is created via `createContext()`.
`App` wraps its JSX in `ThemeContext.Provider` with `value={theme}`
from its own state, and no longer passes `theme` as a prop through
`EmployeesList`. `EmployeeCard` reads the theme directly with
`useContext(ThemeContext)`.
