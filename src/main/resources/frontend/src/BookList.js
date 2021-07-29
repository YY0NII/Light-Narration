import React, { Component } from 'react';
import { Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class BookList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            books: []
        };
    }

    componentDidMount() {
        fetch('/books')
            .then(response => response.json())
            .then(jsonData => this.setState({ books: jsonData }));
    }

    render() {
        const { books, isLoading } = this.state;

        if (isLoading) {
            return (
                <Container>
                    <AppNavbar title="Loading..." />
                </Container>
            );
        }

        const bookList = books.map(book => {
            return (
                <tr key={book.id}>
                    <td>{book.title}</td>
                    <td>{book.authors}</td>
                    <td>
                        {/* I think this will link to the request for a single book via the ID */}
                        <Link to={`/book/${book.id}`}>View</Link>
                    </td>
                </tr>
            );
        });

        return (
            <div>
                <AppNavbar title="Books" />
                <Container fluid>
                    <h3>Books</h3>
                    <Table className="mt-4">
                        <thead>
                            <tr>
                                <th width="30%">Title</th>
                                <th width="30%">Author</th>
                                <th width="40%">View</th>
                            </tr>
                        </thead>
                        <tbody>{bookList}</tbody>
                    </Table>
                    </Container>
                </div>
        );
    }

}

export default BookList;