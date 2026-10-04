package net.sofbt.products.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Le nom est obligatoire")
    @Size(min = 3, max = 50, message = "Le nom doit contenir entre 3 et 50 caractères")
    @Column(length = 50, nullable = false)
    private String name;

    @DecimalMin(value = "0.1", message = "Le prix doit être supérieur à 0")
    private double price;

    @Min(value = 0, message = "La quantité ne peut pas être négative")
    private int quantity;
}
