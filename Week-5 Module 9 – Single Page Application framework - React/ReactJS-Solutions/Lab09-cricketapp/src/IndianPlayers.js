import React from 'react';

// Lab 9: Destructuring to split Odd/Even team players and merge two arrays
const allPlayers = ['Rohit', 'Virat', 'Rahul', 'Bumrah', 'Jadeja', 'Pant'];
const [oddTeamPlayer, evenTeamPlayer, ...rest] = allPlayers;

const T20players = ['Rohit', 'Virat', 'Surya'];
const RanjiTrophyPlayers = ['Mayank', 'Prithvi', 'Shreyas'];
const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

function IndianPlayers() {
  return (
    <div>
      <h2>Indian Players</h2>
      <p><b>Odd Team Player (destructured 1st element):</b> {oddTeamPlayer}</p>
      <p><b>Even Team Player (destructured 2nd element):</b> {evenTeamPlayer}</p>

      <h3>Merged T20 + Ranji Trophy Players</h3>
      <ul>
        {mergedPlayers.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>
    </div>
  );
}

export default IndianPlayers;
