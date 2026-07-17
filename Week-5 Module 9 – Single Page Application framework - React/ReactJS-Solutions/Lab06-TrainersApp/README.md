# Lab 06 - TrainersApp (React Router)

```
npx create-react-app TrainersApp
cd TrainersApp
npm install react-router-dom
# copy src/trainer.js, src/TrainersMock.js, src/components/*, src/App.js
npm start
```
Routes:
- `/` -> Home component
- `/trainers` -> TrainersList component (clickable trainer names)
- `/trainers/:id` -> TrainerDetail component (reads `id` via `useParams()`)
