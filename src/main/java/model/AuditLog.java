package model;

import java.time.LocalDateTime;

public class AuditLog {
    private int id;
    private String message;
    private LocalDateTime timestamp;

    public AuditLog(int id, String message) {
        this.id = id;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
