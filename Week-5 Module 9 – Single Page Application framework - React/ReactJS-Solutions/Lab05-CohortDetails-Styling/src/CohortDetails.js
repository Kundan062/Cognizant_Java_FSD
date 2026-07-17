import React from 'react';
import styles from './CohortDetails.module.css';

// Lab 5: Style a react component using a CSS Module
function CohortDetails({ cohort }) {
  const titleColor = cohort.status === 'ongoing' ? { color: 'green' } : { color: 'blue' };

  return (
    <div className={styles.box}>
      <h3 style={titleColor}>{cohort.code}</h3>
      <dl>
        <dt>Trainer</dt>
        <dd>{cohort.trainer}</dd>
        <dt>Status</dt>
        <dd>{cohort.status}</dd>
        <dt>Start Date</dt>
        <dd>{cohort.startDate}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;
