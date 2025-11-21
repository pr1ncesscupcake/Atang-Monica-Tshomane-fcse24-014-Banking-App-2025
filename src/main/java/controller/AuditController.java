package controller;

import model.AuditLog;
import java.util.ArrayList;
import java.util.List;

public class AuditController {

    private List<AuditLog> logs = new ArrayList<>();

    public void recordEvent(String message) {
        int id = logs.size() + 1;
        logs.add(new AuditLog(id, message));
    }

    public List<AuditLog> getLogs() {
        return logs;
    }
}

