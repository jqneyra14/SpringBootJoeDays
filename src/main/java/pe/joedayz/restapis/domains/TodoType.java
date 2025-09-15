package pe.joedayz.restapis.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import java.util.Date;
import lombok.Data;


@Data
@Entity
@XmlRootElement// cambiamos el root element
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "code", "dateCreated", "lastUpdated" })
@JsonPropertyOrder({"description", "code"})
@JsonInclude(Include.NON_NULL) // or NON_EMPTY, ALWAYS, CUSTOM
public class TodoType {
  @Id
  @NotBlank
  @Size(min = 4, max = 10)
  private String code;

  @XmlTransient
  private String description;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private Date dateCreated;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private Date lastUpdated;

}
