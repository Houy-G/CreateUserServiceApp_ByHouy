package Model;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramNotifier {
    private static final String BOT_TOKEN = "8042240038:AAGF-BkGLVW4AsRjBCX2zB9IaMXg9W-yZ_A";
    private static final String CHAT_ID = "1162294040";

    public static void sendNotification(String message) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBot bot = new TelegramBot(BOT_TOKEN);
            SendMessage sendMessage = new SendMessage(CHAT_ID, message);
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Failed to send notification: " + e.getMessage());
        }
    }
}

class TelegramBot extends org.telegram.telegrambots.bots.TelegramLongPollingBot {
    private final String token;

    public TelegramBot(String token) {
        this.token = token;
    }

    @Override
    public String getBotUsername() {
        return "YourBotUsername";
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(org.telegram.telegrambots.meta.api.objects.Update update) {
        // Not used for notifications
    }
}
