package pro.sissters.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import pro.sissters.telegrambot.keyboard.KeyBoard;
import pro.sissters.telegrambot.repository.PersonRepository;
import pro.sissters.telegrambot.service.PersonService;


@Service
@Data
@Slf4j
public class TelegramBotUpdatesListener implements UpdatesListener {

  private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

  private static final String START_CMD = "/start";

  private static final String GREETING_TEXT =
      ", приветствуем вас в КиберШколе креативных цифровых технологий для девочек SISSTERS!\n"
          + "SISSTERS - девочковое направление\n"
          + "популярного проекта KIBERone, который признан ЮНЕСКО ЛУЧШИМ В МИРЕ в сфере цифровых технологий для детей. "
          + "\n"
          + "В нашей КиберШколе мы научим главным навыкам будущего IT. МЕДИА. ДИЗАЙН:\n"
          + "✅ Создавать и упаковывать IT - продукты;\n"
          + "✅ Читать и управлять кодами;\n"
          + "✅ Применять профессиональные инструменты, которые используют в Google, Apple, YouTube;\n"
          + "✅ Создавать интернет- магазины, сайты и приложения;\n"
          + "✅ Монтировать видео и обрабатывать фотографии;\n"
          + "✅ Создавать анимационные ролики, работать в 3D и многое другое!\n"
          + "\n"
          + "<i>Бот поможет записать ребенка на пробный урок, летние КИБЕРканикулы или узнать больше о SISSTERS! </i>\n"
          + "P.s. при исчезновении главного меню, введите " + START_CMD + " и онo появится снова!";

  private static final String KIBERKANIKULY_TEXT =
      "КиберКаникулы 2024 пройдут в формате IT-интенсивов. \n"
          + "Занятость: пн-пт (2 часа) \n"
          + "\n"
          + "✅ 4 тематические смены на выбор! \n"
          + "✅ Создание собственного IT-проекта для портфолио!\n"
          + "✅ Английский!\n"
          + "✅ Опыт практикующих IT-специалистов!\n"
          + "✅ Творчество и развлечения!\n"
          + "✅ Полезный перекус!";
  public static final String ANSI_PURPLE = "\u001B";

  private static final String KIBERKANIKULY_SMENY =
      "1️⃣ <b> 03.06 - 07.06; 01.07 - 05.07; 29.07 - 02.08: </b> \n"
          + "Построение 3D-миров в Roblox! \n"
          + "Девчонки создадут виртуальный мир в Roblox, "
          + "оживят своего персонажа и научат его выполнять определённые действия. "
          + "КИБЕРдевчонки познакомятся с языком программирования Lua, "
          + "поймут, как работать с системой координат, разовьют творческий потенциал и фантазию! \n"
          + "2️⃣ <b>10.06 - 14.06; 08.07 - 12.07; 05.08 - 09.08:</b> \n"
          + "Магия нейросетей. Курс ИИ в помощь ребёнку! \n"
          + "Девочки узнают, как использовать нейросети для создания контента и креативных анимаций. "
          + "Освоим нейросети семейства ChatGPT–образных для генерации видео и изображений. "
          + "Научимся писать работающие промты.\n"
          + "3️⃣ <b>17.06 - 21.06; 15.07 - 19.07; 12.08 - 16.08:</b> \n"
          + "Создание игры по мотивам Among Us! \n"
          + "Дети познакомятся с программированием на платформе Construct 2 и создадут аналог популярной игры Among Us. "
          + "Девчонки приобретут необходимые для будущего навыки: умение думать логически, строить стратегию, "
          + "а также прокачают творческое и пространственное мышление! \n"
          + "4️⃣ <b>24.06 - 28.06; 22.07 - 26.07; 19.08 - 23.08:</b> \n"
          + "Делаем суперсайты со спецэффектами. Анимация в Figma!"
          + "Авторский интенсив по анимации. Будем делать разные виды анимации – от простых до самых сложных. "
          + "Создадим интерактивные прототипы, анимацию слайдера, бегущей строки, появления букв, "
          + "карточек с ховер-эффектом и др.\n";

  private static final String KIBERKANIKULY_PRICHINY_TEXT =
      "1️⃣ Отличная возможность провести время "
          + "с пользой и в хорошей компании. "
          + "Будет о чём рассказать подружкам. \n"
          + "2️⃣ Живое общение с практикующими "
          + "IT-специалистами. \n"
          + "3️⃣ Интересная и познавательная программа "
          + "вовлекает девчонок в мир цифровых "
          + "технологий и расширяет их кругозор. \n"
          + "4️⃣ Получение сертификата "
          + "для школьного портфолио.\n"
          + "5️⃣ Возможность реализовать собственный "
          + "IT-проект под руководством тьютора.";
  private static final String infoAboutSchool =
      "Вы можете посетить сайт нашей школы:  \nhttps://ntagil.it-sissters.com/ \n"
          + "Наша страничка ВК:\n"
          + "https://vk.com/sissters_nt \n"
          + "Контактные данные нашей школы\n"
          + " \uD83D\uDC47 \n"
          + "Адрес: ул. Дружинина, 82 \n"
          + "Телефон: +7 (965) 517-76-64 \n"
          + "E-mail: ntagil@it-sissters.com";


  private static final String reportExample = "89991112233";

  private static final String REGEX_MESSAGE = "(\\W+)\n" +
      "(\\d+)\n" +
      "(\\d+)";

  private static final String managerChatId = "id чата менеджера с ботом";


  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private KeyBoard keyBoard;
  @Autowired
  private TelegramBot telegramBot;
  @Autowired
  private PersonService personService;

  public TelegramBotUpdatesListener(TelegramBot telegramBot) {
    this.telegramBot = telegramBot;
  }

  @PostConstruct
  public void init() {
    telegramBot.setUpdatesListener(this);
  }

  @Override
  public int process(List<Update> updates) {
    updates.forEach(update -> {
      logger.info("Пользователь " + update.message().chat().firstName() + " запустил бот: {}",
          update);
      String name = update.message().chat().firstName();
      String userName = update.message().chat().username();
      String textUpdate = update.message().text();
      Integer messageId = update.message().messageId();
      long chatId = update.message().chat().id();
      boolean probnyiUrok = textUpdate.matches("\\d+");
      boolean kiberkanikuly = textUpdate.matches("\\d \\d+");
      if (probnyiUrok) {
        if (textUpdate.length() == 11 && textUpdate.startsWith("8")) {
          personService.savePersonData(update.message().chat().id(), name, userName, textUpdate);
          sendMessage(chatId,
              "Ваш номер телефона: " + textUpdate
                  + " успешно добавлен. Скоро наш менеджер свяжется с вами!");
          logger.info("Контакт " + name + " " + textUpdate + " добавлен в базу данных!");
          sendMessage(Long.parseLong(managerChatId), name + " (" + userName + ") " + textUpdate
              + " ждет звонка для записи на пробный урок " + LocalDateTime.now());
          logger.info("Контакт " + name + " " + textUpdate + " отправлен менеджеру");
        } else {
          sendMessage(chatId,
              "Где-то ошибка! Номер должен содержать 11 цифр и быть в формате: " + reportExample
                  + "\n"
                  + "Попробуйте еще раз!");
          logger.info("Контакт " + name + " ввел некорректный номер телефона!");
        }
      }
      if (kiberkanikuly) {
        if (textUpdate.length() == 13 && textUpdate.startsWith("1") || textUpdate.startsWith("2")
            || textUpdate.startsWith("3")
            || textUpdate.startsWith("4")) {
          sendMessage(chatId,
              "Ваша заявка принята. Скоро наш менеджер свяжется с вами!");
          logger.info("Контакт " + name + " оставил заявку на КИБЕРканикулы: " + textUpdate);
          sendMessage(Long.parseLong(managerChatId),
              name + " (" + userName + ") оставил заявку на КИБЕРканикулы: " + textUpdate
                  + LocalDateTime.now());
          logger.info("Заявка на КИБЕРканикулы " + textUpdate + " от контакта " + name
              + " отправлена менеджеру");
        } else {
          sendMessage(chatId,
              "Где-то ошибка! Нужно начать ввод с номера смены (1,2,3 или 4)! Номер телефона должен содержать 11 цифр начиная с 8: 1 89223334455"
                  + "\n"
                  + "Попробуйте еще раз!");
          logger.info("Контакт " + name + " ввел некорректные данные в заявке на КИБЕРканикулы!");
        }
      }
      try {
        switch (textUpdate) {
          case START_CMD:
            sendMessageParse(chatId, name + GREETING_TEXT);
            keyBoard.mainMenu(chatId);
            break;
          case "Записаться на пробный урок (мастер-класс)":
            keyBoard.sendMenuGirlBoy(chatId);
            sendMessage(chatId, "Вы хотите записать мальчика или девочку?");
            break;
          case "Летние КИБЕРканикулы":
            keyBoard.kiberkanikulyMenu(chatId);
            sendMessage(chatId, KIBERKANIKULY_TEXT);
            break;
          case "5 причин побывать на КИБЕРКаникулах":
            keyBoard.kiberkanikulyMenu(chatId);
            sendMessage(chatId, KIBERKANIKULY_PRICHINY_TEXT);
            break;
          case "Смены летних КИБЕРканикул":
            keyBoard.smenyKiberkanikulMenu(chatId);
            sendMessageParse(chatId, KIBERKANIKULY_SMENY);
            break;
          case "Оставить заявку":
            sendMessage(chatId, "Введите номер смены и номер телефона строго в формате: \n"
                + "1 89223334455");
            break;
          case "\uD83D\uDC66 Мальчика":
            sendMessage(chatId, "К сожаленнию, мы не можем записать вашего мальчика! \n"
                + "SISSTERS - девочковое направление популярного проекта KIBERone, поэтому на пробные уроки и мастер-классы мы записываем только девочек!");
            keyBoard.mainMenu(chatId);
            break;
          case "\uD83D\uDC67 Девочку":
            keyBoard.personData(chatId);
            sendMessage(chatId,
                " Укажите ваш номер телефона строго в таком формате: \n"
                    + reportExample);
            break;
          case "\uD83D\uDC49 Узнать больше о школе":
            sendMessage(chatId, infoAboutSchool);
            break;
          case "Вернуться в меню":
            keyBoard.mainMenu(chatId);
            break;
          default:
            sendMessage(chatId, "Если вам нужно вернуться в главное меню нажмите " + START_CMD);
            break;
        }
      } catch (NullPointerException e) {
        System.out.println("Ошибка");
      }
    });
    return UpdatesListener.CONFIRMED_UPDATES_ALL;
  }

  public void sendMessage(long chatId, String text) {
    SendMessage message = new SendMessage(chatId, text);
    telegramBot.execute(message);
  }

  public void sendMessageParse(long chatId, String text) {
    SendMessage message = new SendMessage(chatId, text).parseMode(ParseMode.HTML);
    telegramBot.execute(message);
  }
}

