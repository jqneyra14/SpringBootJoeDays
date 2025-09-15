package pe.joedayz.restapis.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Data;
import org.springframework.data.domain.AbstractAggregateRoot;
import pe.joedayz.restapis.events.TodoCreationEvent;
import pe.joedayz.restapis.utils.validators.TitleConstraint;

@Data
@Entity
@NamedQuery(name = "Todo.fetchAllDone", query = "SELECT t FROM Todo t WHERE t.done = true")
@NamedQuery(name="Todo.fetchAllByName", query = "SELECT t FROM Todo t WHERE t.title = ?1")
public class Todo extends AbstractAggregateRoot<Todo> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) // IDENTITY, SEQUENCE, TABLE
  private long id;

  @NotNull
  @TitleConstraint
  private String title;

  @JsonIgnore
  private String description;

  private boolean done; //1 = true, 0 = false

  @NotNull
  @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
  private Date dateCreated;

  @NotNull
  @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
  private Date dueDate;

  @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
  private Date dateDone;

  @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
  private Date lastUpdated;

  @ManyToOne
  @JsonProperty("type")
  private TodoType todoType;

  public void afterSave() {
    registerEvent(new TodoCreationEvent());
  }

}
