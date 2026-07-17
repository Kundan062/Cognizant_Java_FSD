import React from 'react';

// Conditional rendering technique 1: if-else inside a helper function
function BookDetails({ available }) {
  function renderStatus() {
    if (available) {
      return <p>Book is available in the library.</p>;
    } else {
      return <p>Book is currently checked out.</p>;
    }
  }

  return (
    <div>
      <h3>Book Details</h3>
      {renderStatus()}
    </div>
  );
}

export default BookDetails;
