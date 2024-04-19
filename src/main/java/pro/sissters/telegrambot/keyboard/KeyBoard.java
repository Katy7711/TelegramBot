package pro.sissters.telegrambot.keyboard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sissters.telegrambot.listener.TelegramBotUpdatesListener;


@Service
public class KeyBoard {

  @Autowired
  private TelegramBot telegramBot;

  private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

  public void mainMenu(long chatId) {
    logger.info("Method mainMenu has been run: {}, {}", chatId, "Вызвано меню выбора ");
    String emoji_info = EmojiParser.parseToUnicode(":point_right:");
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
        new KeyboardButton(" Записаться на пробный урок (мастер-класс)"));
    replyKeyboardMarkup.addRow(new KeyboardButton(emoji_info + " Узнать больше о школе"));
    replyKeyboardMarkup.addRow(new KeyboardButton(" Летние КИБЕРканикулы"));
    returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Выберите действие:");
  }

  public void kiberkanikulyMenu(long chatId) {
    logger.info("Method kiberkanikulyMenu has been run: {}, {}", chatId, "Вызвано меню выбора ");
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
        new KeyboardButton(" Смены летних КИБЕРканикул"));
    replyKeyboardMarkup.addRow(new KeyboardButton(" 5 причин побывать на КИБЕРКаникулах"));
    replyKeyboardMarkup.addRow(new KeyboardButton(" Вернуться в меню"));
    returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId,
        "\uD83D\uDC69\u200D\uD83D\uDCBB");
  }

  public void smenyKiberkanikulMenu(long chatId) {
    logger.info("Method smenyKiberkanikulMenu has been run: {}, {}", chatId,
        "Вызвано меню выбора ");
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
        new KeyboardButton(" Оставить заявку"));
    replyKeyboardMarkup.addRow(new KeyboardButton(" Вернуться в меню"));
    returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId,
        "Оставьте заявку и наш менеджер перезвонит вам для уточнения деталей!");
  }

  public void sendMenuGirlBoy(long chatId) {
    logger.info("Method sendMenuGirlBoy has been run: {}, {}", chatId,
        "Вызвано меню выбора мальчика или девочки ");
    String emoji_boy = EmojiParser.parseToUnicode(":boy:");
    String emoji_girl = EmojiParser.parseToUnicode(":girl:");
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
        new KeyboardButton(emoji_girl + " Девочку"),
        new KeyboardButton(emoji_boy + " Мальчика"));
    replyKeyboardMarkup.addRow(new KeyboardButton(" Вернуться в меню"));
    returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, emoji_boy + emoji_girl);
  }

  public void returnToMainMenu(long chatId) {
    logger.info("Method returnToMainMenu has been run: {}, {}", chatId,
        "Выбрано венуться в меню");
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
        new KeyboardButton(" Записаться на пробный урок (мастер-класс)"));
    replyKeyboardMarkup.addRow(new KeyboardButton("Вернуться в меню"));
    returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "");
  }

  public void personData(long chatId) {
    logger.info("Method personData has been run: {}, {}", chatId,
        "Оставляют данные для связи");
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
        new KeyboardButton("Вернуться в меню"));
    returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Отлично!");
  }

  public void returnResponseReplyKeyboardMarkup(ReplyKeyboardMarkup replyKeyboardMarkup,
      Long chatId, String text) {
    replyKeyboardMarkup.resizeKeyboard(true);
    replyKeyboardMarkup.oneTimeKeyboard(false);
    replyKeyboardMarkup.selective(false);
    SendMessage request = new SendMessage(chatId, text)
        .replyMarkup(replyKeyboardMarkup)
        .parseMode(ParseMode.HTML)
        .disableWebPagePreview(true);
    SendResponse sendResponse = telegramBot.execute(request);
    if (!sendResponse.isOk()) {
      int codeError = sendResponse.errorCode();
      String description = sendResponse.description();
      logger.info("code of error: {}", codeError);
      logger.info("description -: {}", description);
    }
  }
}