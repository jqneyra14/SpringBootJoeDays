package pe.joedayz.restapis.domains;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
@Entity
//@Table(name = "T_TODOS")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // IDENTITY, SEQUENCE, TABLE
    private long id;

    @NotNull
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

}