import React from 'react';

const ListofPlayers = () => {
  const players = [
    { name: 'Virat Kohli', score: 85 },
    { name: 'Rohit Sharma', score: 65 },
    { name: 'Shubman Gill', score: 72 },
    { name: 'KL Rahul', score: 50 },
    { name: 'Hardik Pandya', score: 90 },
    { name: 'Ravindra Jadeja', score: 68 },
    { name: 'R. Ashwin', score: 74 },
    { name: 'Mohammed Shami', score: 95 },
    { name: 'Jasprit Bumrah', score: 60 },
    { name: 'Suryakumar Yadav', score: 78 },
    { name: 'Yuzvendra Chahal', score: 55 }
  ];

  const below70 = players.filter(player => player.score < 70);

  return (
    <div>
      <h2>All Players</h2>
      <ul>
        {players.map((player, index) => (
          <li key={index}>{player.name} - {player.score}</li>
        ))}
      </ul>

      <h2>Players with Score Below 70</h2>
      <ul>
        {below70.map((player, index) => (
          <li key={index}>{player.name} - {player.score}</li>
        ))}
      </ul>
    </div>
  );
};

export default ListofPlayers;
