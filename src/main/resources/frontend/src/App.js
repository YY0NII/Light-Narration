import logo from './logo.svg';
import './App.css';
import { Component } from 'react';

class App extends Component {
  state = {
    books: []
  };

  async componentDidMount() {
    const response = await fetch('/books');
    const body = await response.json();
    this.setState({ books: body });
  }

  render() {
    const {books} = this.state;

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro">
            <h2>Books</h2>
            {books.map(book =>
              <div key={book.id}>
                {book.title} 
              </div> 
              )
            }
          </div>
        </header>
      </div>
    );
  }
}

export default App;
