import React from 'react';
import PropTypes from 'prop-types';
import styles from './CohortDetails.module.css';

// Component under test in Lab 18
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

CohortDetails.propTypes = {
  cohort: PropTypes.shape({
    code: PropTypes.string,
    trainer: PropTypes.string,
    status: PropTypes.string,
    startDate: PropTypes.string
  }).isRequired
};

export default CohortDetails;
