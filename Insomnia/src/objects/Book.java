package objects;

import java.time.LocalDate;

public class Book {
    private Status status;
    private String title;
    private String author = "";
    private String fromDate;
    private String tillDate;
    private boolean isAudio;
    private String genre = "";
    private String notes = "";

    public Book( Status status, String title, String author, LocalDate fromDate, LocalDate tillDate, boolean isAudio, String genre, String notes) {
        this.status = status;
        this.title = title;
        this.author = author;
        this.fromDate = fromDate.toString();
        this.tillDate = tillDate.toString();
        this.isAudio = isAudio;
        this.genre = genre;
        this.notes = notes;
    }

    public Book(String title, String author, String tillDate) {
        this.author = author;
        this.title = title;
        this.tillDate = tillDate;
    }

    public Book() {

    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getTillDate() {
        return tillDate;
    }

    public void setTillDate(String tillDate) {
        this.tillDate = tillDate;
    }

    public boolean isAudio() {
        return isAudio;
    }

    public void setAudio(boolean audio) {
        isAudio = audio;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
