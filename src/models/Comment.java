package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    private static int idCounter = 1;
    private int id;
    private int authorId;
    private String authorName;
    private String content;
    private LocalDateTime timestamp;

    public Comment(int authorId, String authorName, String content) {
        this.id = idCounter++;
        this.authorId = authorId;
        this.authorName = authorName;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "💬 [" + authorName + "] " + content + " (" + timestamp.format(formatter) + ")";
    }
}
