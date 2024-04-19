<p align="center"><a href="https://laravel.com" target="_blank"><img src="https://mastertigr.ru/wp-content/uploads/2023/06/telegram-bot.png" width="400" alt="Laravel Logo"></a></p>

# (RU) Телеграм бот для IT школы

Задачей было написать телеграм бота для IT школы для детей и реализовать следующий функционал:

Пользователь может ознакомиться с информацией о школе, в том числе получить ссылки на социальные сети.
Пользователь может оставить заявку на пробный урок или занятия в летние каникулы.
Все заявки должны приходить менеджеру школы в телеграм с указанием номера телефона и информацией, куда именно пользователь хочет записать ребенка.
Все контакты сохраняются в базу данных.


## Разработчик:

 - [Дениева Екатерина Юрьевна](https://github.com/Katy7711)
 
## Стек технологий:
**В проекте используются**:
 
* Backend:
    - Java 17
    - Maven
    - Spring Boot
    - Spring Web
    - Spring Data
    - Spring JPA
    - GIT
    - Lombok
* SQL:
    - PostgreSQL


## Запуск:
**Для запуска нужно:**
- Клонировать проект в среду разработки
- Дополнить нужными данными properties в файле **[application1.properties](src/main/resources/application1.properties)** изменить его имя на [application.properties]
- Создать и добавить в проект базу данных PostgreSQL, прописать настройки в [application.properties]
- Прописать токен своего бота, предварительно создав его через официального бота телеграм BotFather.
- Запустить метод **main** в файле **[TelegramBotApplication.java](src/main/java/pro/sissters/telegrambot/TelegramBotApplication.java)**

После выполнения всех действий, бот должен корректно работать в телеграм.

------
 
# (EN) Telegram bot of IT school

The task is to write a telegram bot for IT school for children and implement the following functionality:

The user can view information about the school, including links to social networks.
The user can submit a request for a trial lesson or classes during the summer holidays.
All applications must be sent to the school manager via telegram, indicating the phone number and information where exactly the user wants to enroll the child.
All contacts are saved to the database.

## The developer:

 - [Denieva Ekaterina Yurievna](https://github.com/Katy7711)

 
## Technology stack:
**The project uses**:

* Backend:
  - Java 17
  - Maven
  - Spring Boot
  - Spring Web
  - Spring Data
  - Spring JPA
  - GIT
  - Lombok
* SQL:
  - PostgreSQL

## Launch:
**To start, you need:**
- Clone a project into a development environment
- Add the required data to the properties in the file **[application1.properties](src/main/resources/application1.properties)** change this name to [application.properties]
- Enter your bot token by first creating it using the official telegram bot - BotFather.
- To run method **main** in the file **[HomeworkApplication.java](src/main/java/pro/sissters/telegrambot/TelegramBotApplication.java)**

After completing all the actions, the bot will work correctly in telegram

 ------
