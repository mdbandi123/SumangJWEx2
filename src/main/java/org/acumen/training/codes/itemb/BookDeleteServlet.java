package org.acumen.training.codes.itemb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.acumen.training.codes.itemb.exceptions.DoubleParsingException;
import org.acumen.training.codes.itemb.exceptions.IntegerParsingException;
import org.acumen.training.codes.itemb.model.Book;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "BookDeleteServlet", urlPatterns = "/book/delete")
public class BookDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private List<String> titles = new ArrayList<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		titles = new ArrayList<>();
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/book/delete_book.html");
		String path = "/Users/michaeldavesumang/Training/Demo/Track7/sumangjwex2/src/main/resources/bookdb.txt";
		File file = new File(path);

		Charset utf8 = Charset.forName("UTF-8");
		Path filePath = Paths.get(path);

		try (Scanner sc = new Scanner(file);) {
			while (sc.hasNext()) {
				String line = sc.nextLine();
				String[] lineArr = line.split(" ");
				String title = lineArr[1];
				titles.add(title);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		req.setAttribute("titles", titles);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String titleToDel = req.getParameter("titles");

//		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/book/list_books.html");
		String path = "/Users/michaeldavesumang/Training/Demo/Track7/sumangjwex2/src/main/resources/bookdb.txt";
		File file = new File(path);
		List<Book> books = new ArrayList<>();
		PrintWriter out = resp.getWriter();
		Charset utf8 = Charset.forName("UTF-8");
		Path filePath = Paths.get(path);

		try (Scanner sc = new Scanner(file);) {
			while (sc.hasNext()) {
				String line = sc.nextLine();
				out.write(line);
				String[] lineArr = line.split(" ");
				String isbn = lineArr[0];
				String title = lineArr[1];
				String author = lineArr[2];
				Double price = parseDouble(lineArr[3]);
				Integer qty = parseInteger(lineArr[4]);

				books.add(new Book(isbn, title, author, price, qty));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		overwriteFileWithBook(books, titleToDel, path);

		resp.sendRedirect("/sumangweb2/book/handler/save");

	}

	private Double parseDouble(String param) throws DoubleParsingException, Exception {
		try {
			return Double.parseDouble(param);
		} catch (NumberFormatException e) {
			throw new DoubleParsingException("Inputted value is not of type double");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0.0;
	}

	private Integer parseInteger(String param) throws IntegerParsingException, Exception {
		try {
			return Integer.parseInt(param);
		} catch (NumberFormatException e) {
			throw new IntegerParsingException("Inputted value is not of type integer");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	private boolean overwriteFileWithBook(List<Book> books, String toDel, String path) {

		Charset utf8 = Charset.forName("UTF-8");
		Path filePath = Paths.get(path);

		try (BufferedWriter writer = Files.newBufferedWriter(filePath, utf8, StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING)) {
			books.forEach((book) -> {
				String payload = "%s %s %s %f %d\n".formatted(book.isbn(), book.title(), book.author(), book.price(),
						book.qty());
				if (!book.title().equals(toDel)) {
					try {
						writer.write(payload);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
