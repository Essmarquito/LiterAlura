package com.alura.LiterAlura.model;

import com.alura.LiterAlura.record.DatosAutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "autores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;

    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeMuerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros;

    // Constructor que toma un objeto DatosAutor
    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeMuerte = datosAutor.fechaDeMuerte();
    }

    @Override
    public String toString() {
        return "\nNombre: " + nombre +
                "\nFecha de nacimiento: " + fechaDeNacimiento +
                "\nFecha de muerte: " + fechaDeMuerte;
    }
}
