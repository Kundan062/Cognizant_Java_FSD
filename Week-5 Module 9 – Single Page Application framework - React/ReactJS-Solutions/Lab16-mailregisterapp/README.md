# Lab 16 - mailregisterapp (Form Validation)

```
npx create-react-app mailregisterapp
cd mailregisterapp
# copy src/Register.js, src/App.js
npm start
```
Validation rules applied on submit (`handleSubmit`) using values
tracked via `handleChange`:
- Name >= 5 characters
- Email must contain `@` and `.`
- Password >= 8 characters
