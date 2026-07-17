import React from 'react';
import { useParams } from 'react-router-dom';
import trainersData from '../TrainersMock';

// Lab 6: Retrieve the id from the URL using useParams and show the trainer
function TrainerDetail() {
  const { id } = useParams();
  const trainer = trainersData.find((t) => t.trainerId === Number(id));

  if (!trainer) {
    return <p>Trainer not found.</p>;
  }

  return (
    <div>
      <h2>Trainer Details</h2>
      <p><b>ID:</b> {trainer.trainerId}</p>
      <p><b>Name:</b> {trainer.name}</p>
      <p><b>Email:</b> {trainer.email}</p>
      <p><b>Phone:</b> {trainer.phone}</p>
      <p><b>Technology:</b> {trainer.technology}</p>
      <p><b>Skills:</b> {trainer.skills.join(', ')}</p>
    </div>
  );
}

export default TrainerDetail;
