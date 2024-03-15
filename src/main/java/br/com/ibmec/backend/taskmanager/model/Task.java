package br.com.ibmec.backend.taskmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    @Column
    @NotBlank(message = "Campo descrição é obrigatório")
    private String description;

    @Column
    private String status;

    @Column
    @NotBlank(message = "Campo responsável é obrigatório")
    private String owner;
}
