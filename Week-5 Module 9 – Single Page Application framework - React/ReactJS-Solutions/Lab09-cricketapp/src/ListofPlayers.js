import React from 'react';

// Lab 9: ES6 map() to build player list, arrow function + filter for scores < 70
const players = [
  { name: 'Rohit', score: 85 },
  { name: 'Virat', score: 92 },
  { name: 'Rahul', score: 45 },
  { name: 'Bumrah', score: 12 },
  { name: 'Jadeja', score: 60 },
  { name: 'Pant', score: 75 },
  { name: 'Siraj', score: 8 },
  { name: 'Shami', score: 15 },
  { name: 'Kohli2', score: 68 },
  { name: 'Gill', score: 55 },
  { name: 'Iyer', score: 40 },
];

function ListofPlayers() {
  const playerRows = players.map((p, index) => (
    <li key={index}>{p.name} - {p.score}</li>
  ));

  const lowScorers = players.filter((p) => p.score < 70);

  return (
    <div>
      <h2>List of Players</h2>
      <ul>{playerRows}</ul>

      <h3>Players with score below 70</h3>
      <ul>
        {lowScorers.map((p, index) => (
          <li key={index}>{p.name} - {p.score}</li>
        ))}
      </ul>
    </div>
  );
}

export default ListofPlayers;
