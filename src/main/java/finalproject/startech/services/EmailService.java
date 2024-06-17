package finalproject.startech.services;

public interface EmailService
{
    void sendConfirmationEmail(String email, String token);
}
