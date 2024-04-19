package pro.sissters.telegrambot.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sissters.telegrambot.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

  Set<Person> findByChatId(Long chatId);

}
