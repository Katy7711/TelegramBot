package pro.sissters.telegrambot.service;

import javax.transaction.Transactional;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sissters.telegrambot.model.Person;
import pro.sissters.telegrambot.repository.PersonRepository;

@Service
@Transactional
@Data
public class PersonService {

  private final PersonRepository personRepository;

  private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

  public void savePersonData(Long personId, String name, String userName, String phone)  {
    logger.info("Вызван метод сохранения в базе данных");
    Person person = new Person();
    person.setName(name);
    person.setUserName(userName);
    person.setPhone(phone);
    person.setChatId(personId);
    this.personRepository.save(person);
  }
}
