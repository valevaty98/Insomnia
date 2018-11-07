package objects;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private LocalDate fromData;
    private LocalDate tillData;
    private boolean isAudio;
    private String genre;
    private Status status;

    public Book(String title, String author, LocalDate fromData, LocalDate tillData, boolean isAudio, String genre, Status status, String notes) {
        this.title = title;
        this.author = author;
        this.fromData = fromData;
        this.tillData = tillData;
        this.isAudio = isAudio;
        this.genre = genre;
        this.notes = notes;
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private String notes;

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

    public LocalDate getFromData() {
        return fromData;
    }

    public void setFromData(LocalDate fromData) {
        this.fromData = fromData;
    }

    public LocalDate getTillData() {
        return tillData;
    }

    public void setTillData(LocalDate tillData) {
        this.tillData = tillData;
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
