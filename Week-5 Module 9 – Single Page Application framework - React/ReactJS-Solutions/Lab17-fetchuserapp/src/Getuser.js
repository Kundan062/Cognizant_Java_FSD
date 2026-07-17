import React from 'react';

// Lab 17: Fetch user details from the Random User API in componentDidMount()
class Getuser extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      user: null,
      loading: true
    };
  }

  componentDidMount() {
    fetch('https://api.randomuser.me/')
      .then((response) => response.json())
      .then((data) => {
        this.setState({ user: data.results[0], loading: false });
      })
      .catch((error) => {
        console.error('Error fetching user:', error);
        this.setState({ loading: false });
      });
  }

  render() {
    const { user, loading } = this.state;

    if (loading) {
      return <p>Loading user...</p>;
    }

    if (!user) {
      return <p>Unable to load user.</p>;
    }

    return (
      <div>
        <h2>Random User</h2>
        <img src={user.picture.large} alt="user" />
        <p>Title: {user.name.title}</p>
        <p>First Name: {user.name.first}</p>
      </div>
    );
  }
}

export default Getuser;
