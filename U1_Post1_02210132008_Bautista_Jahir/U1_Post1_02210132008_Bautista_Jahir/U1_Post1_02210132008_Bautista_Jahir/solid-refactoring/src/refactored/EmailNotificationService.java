package refactored;

public class EmailNotificationService implements NotificationService {

    @Override
    public void notify(String recipient, String message) {
        System.out.println("EMAIL a " + recipient + ": " + message);
    }
}