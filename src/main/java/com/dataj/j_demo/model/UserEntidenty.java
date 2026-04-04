package com.dataj.j_demo.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Users")
public class UserEntidenty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 125)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String name;

    @Column(nullable = false, unique = true, length = 125)
    @NotNull(message = "{campo.email.obrigatorio}")
    private String email;

    @Column(nullable = true, length = 125)    
    private String password;

    @Column(nullable = false, unique = true, length = 11)
    @CPF(message = "{cpf.invalido}")
    @NotNull(message = "{campo.cpf.obrigatorio}")
    private String cpf;

    @Column(nullable = false, updatable = false)
   // @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDate data_born;

    @PrePersist
    private void setDateBorn(){
        this.data_born = LocalDate.of(1980, 
                                     1,
                                1);
    }
}
