package com.alura.LiterAlura.principal;

import com.alura.LiterAlura.model.Libro;
import com.alura.LiterAlura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Principal implements CommandLineRunner {

	@Autowired
	private LibroService libroService;

	public static void main(String[] args) {
		SpringApplication.run(Principal.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try (Scanner scanner = new Scanner(System.in)) {
			boolean exit = false;
			while (!exit) {
				showMenu();
				try {
					int option = scanner.nextInt();
					scanner.nextLine(); // Consumir el salto de línea después del entero

					switch (option) {
						case 1 -> searchBookByTitle(scanner);
						case 2 -> listAllBooks();
						case 3 -> listAllAuthors();
						case 4 -> searchAuthorsByYear(scanner);
						case 5 -> searchBooksByLanguage(scanner);
						case 6 -> {
							exit = true;
							System.out.println("¡Hasta luego!");
						}
						default -> System.out.println("Opción no válida, por favor intente de nuevo.");
					}
				} catch (InputMismatchException e) {
					System.out.println("Entrada no válida. Por favor, ingresa un número.");
					scanner.nextLine(); // Limpiar el buffer del scanner
				}
			}
		}
	}


	private void showMenu() {
		System.out.println("\n===== Menú =====");
		System.out.println("1. Buscar libro por título");
		System.out.println("2. Listar libros registrados");
		System.out.println("3. Listar autores registrados");
		System.out.println("4. Buscar autores por año");
		System.out.println("5. Buscar libros por idioma");
		System.out.println("6. Salir");
		System.out.print("Selecciona una opción: ");
	}

	private void searchBookByTitle(Scanner scanner) {
		System.out.print("Ingresa el título del libro: ");
		String title = scanner.nextLine();
		List<Libro> booksByTitle = libroService.searchBooksByTitle(title);
		if (booksByTitle != null && !booksByTitle.isEmpty()) {
			booksByTitle.forEach(System.out::println);
		} else {
			System.out.println("No se encontraron libros con ese título.");
		}
	}

	private void listAllBooks() {
		List<Libro> allLibros = libroService.getAllBooks(null, null);
		if (allLibros != null && !allLibros.isEmpty()) {
			allLibros.forEach(System.out::println);
		} else {
			System.out.println("No se encontraron libros registrados.");
		}
	}

	private void listAllAuthors() {
		List<String> authors = libroService.getAuthors();
		if (authors != null && !authors.isEmpty()) {
			authors.forEach(System.out::println);
		} else {
			System.out.println("No se encontraron autores registrados.");
		}
	}

	private void searchAuthorsByYear(Scanner scanner) {
		System.out.print("Ingresa el año: ");
		try {
			int year = scanner.nextInt();
			scanner.nextLine(); // Consumir el salto de línea
			List<String> authorsByYear = libroService.getAuthorsByYear(year);
			if (authorsByYear != null && !authorsByYear.isEmpty()) {
				authorsByYear.forEach(System.out::println);
			} else {
				System.out.println("No se encontraron autores en ese año.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Entrada no válida. Por favor, ingresa un número.");
			scanner.nextLine(); // Limpiar el buffer del scanner
		}
	}

	private void searchBooksByLanguage(Scanner scanner) {
		System.out.print("Ingresa el idioma (por ejemplo, 'en' para inglés): ");
		String language = scanner.nextLine();
		List<Libro> booksByLanguage = libroService.getAllBooks(null, language);
		if (booksByLanguage != null && !booksByLanguage.isEmpty()) {
			booksByLanguage.forEach(System.out::println);
		} else {
			System.out.println("No se encontraron libros en ese idioma.");
		}
	}
}
