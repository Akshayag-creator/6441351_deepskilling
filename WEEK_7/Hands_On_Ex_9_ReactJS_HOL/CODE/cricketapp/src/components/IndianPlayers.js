import React from 'react';

const IndianPlayers = () => {
  const T20Players = ['Virat Kohli', 'Rohit Sharma', 'Hardik Pandya', 'Rishabh Pant', 'Shubman Gill'];
  const RanjiTrophyPlayers = ['Cheteshwar Pujara', 'Ajinkya Rahane', 'Wriddhiman Saha', 'Washington Sundar', 'Sarfaraz Khan'];

  const allPlayers = [...T20Players, ...RanjiTrophyPlayers];

  const evenTeam = allPlayers.filter((_, index) => index % 2 === 0);
  const oddTeam = allPlayers.filter((_, index) => index % 2 !== 0);

  return (
    <div>
      <h2>Even Team Players</h2>
      <ul>
        {evenTeam.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>

      <h2>Odd Team Players</h2>
      <ul>
        {oddTeam.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>
    </div>
  );
};

export default IndianPlayers;
