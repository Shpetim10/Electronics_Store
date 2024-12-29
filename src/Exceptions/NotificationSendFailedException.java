package Exceptions;

public class NotificationSendFailedException extends Exception {
    public NotificationSendFailedException() {
        super("Failed to send a notification!");
    }
}
