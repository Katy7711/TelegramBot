package pro.sissters.telegrambot.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "persons_data")
public class Person {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String userName;
  private String phone;
  private Long chatId;
  @CreationTimestamp
  private LocalDateTime localDateTime;

  public Person(Long id, String name, String userName, String phone, Long chatId,
      LocalDateTime localDateTime) {
    this.id = id;
    this.name = name;
    this.userName = userName;
    this.phone = phone;
    this.chatId = chatId;
    this.localDateTime = localDateTime;
  }
  public Person(String name, String userName, String phone, Long chatId) {
    this.name = name;
    this.userName = userName;
    this.phone = phone;
    this.chatId = chatId;
  }
}
