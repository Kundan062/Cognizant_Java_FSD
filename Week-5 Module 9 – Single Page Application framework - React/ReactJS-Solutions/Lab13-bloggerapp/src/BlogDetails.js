import React from 'react';

// Conditional rendering technique 2: ternary operator
function BlogDetails({ published }) {
  return (
    <div>
      <h3>Blog Details</h3>
      <p>{published ? 'This blog is published.' : 'This blog is still a draft.'}</p>
    </div>
  );
}

export default BlogDetails;
