package academy.arima.store.customer.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tbl_customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El número de documento no puede ser vacío")
    @Size(min = 8, max = 8, message = "El tamaño del número de documento es 8")
    private String numberId;
    @NotEmpty(message = "El nombre no puede ser vacío")
    @Column(nullable = false)
    private String firstName;
    @NotEmpty(message = "El apellido no puede ser vacío")
    @Column(nullable = false)
    private String lastName;
    @NotEmpty(message = "el correo no puede estar vacío")
    @Email(message = "no es un dirección de correo bien formada")
    @Column(unique=true, nullable=false)
    private String email;
    private String photoUrl;
    @NotNull(message = "La región no puede ser vacía")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "region_id")
    private Region region;
    private String status;
}
