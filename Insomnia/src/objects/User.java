package objects;

import java.util.Vector;

public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private Vector<Book> books;

    public User(String name, String surname, String email, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.books = new Vector<Book>();
    }

    public User() {
        this.books = new Vector<Book>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(Book book) {
        for (Book b : books) {
            if (b.getId() == book.getId()) b = book;
        }
    }

    public void deleteBook(Book book) {
        for (Book b : books) {
            if (b.getId() == book.getId()) books.remove(b);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vector<Book> getBooks() {
        return books;
    }

    public void setBooks(Vector<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
