package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import structures.Queue;

public class Publication {
    private static int idCounter = 1;
    private int id;
    private int authorId;
    private String authorName;
    private String content;
    private LocalDateTime timestamp;
    private Queue<Comment> comments;

    public Publication(int authorId, String authorName, String content) {
        this.id = idCounter++;
        this.authorId = authorId;
        this.authorName = authorName;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.comments = new Queue<>();
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

    public Queue<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.enqueue(comment);
    }

    public int getCommentCount() {
        return comments.size();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "📝 [ID: " + id + "] " + authorName + " - " + content + 
               "\n   Publié le: " + timestamp.format(formatter) + 
               " | Commentaires: " + comments.size();
    }
}
