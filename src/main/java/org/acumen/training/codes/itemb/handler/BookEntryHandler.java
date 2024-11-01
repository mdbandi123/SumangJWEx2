package org.acumen.training.codes.itemb.handler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.acumen.training.codes.itemb.exceptions.DoubleParsingException;
import org.acumen.training.codes.itemb.exceptions.EmptyFieldException;
import org.acumen.training.codes.itemb.exceptions.ISBNLengthException;
import org.acumen.training.codes.itemb.exceptions.IntegerParsingException;
import org.acumen.training.codes.itemb.model.Book;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "BookEntryHandler", urlPatterns = "/book/handler/save")
public class BookEntryHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/book/list_books.html");
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
		
		req.setAttribute("books", books);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/book/service/filesave");
		Book book = null;
		String isbn = req.getParameter("isbn");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String price = req.getParameter("price");
		String qty = req.getParameter("qty");

		Double parsedPrice = 0.0;
		Integer parsedQty = 0;

		try {
			validateIsEmpty(isbn);
			validateIsEmpty(title);
			validateIsEmpty(author);
			validateIsEmpty(price);
			validateIsEmpty(qty);
		} catch (EmptyFieldException e) {
			throw new EmptyFieldException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			parsedPrice = parseDouble(price);
		} catch (DoubleParsingException e) {
			throw new DoubleParsingException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			parsedQty = parseInteger(qty);
		} catch (IntegerParsingException e) {
			throw new IntegerParsingException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			validateISBNLen(isbn);
		} catch (ISBNLengthException e) {
			throw new ISBNLengthException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		book = getBookInstance(isbn, title, author, parsedPrice, parsedQty);
		req.setAttribute("book", book);
		dispatcher.forward(req, resp);
	}

	private boolean validateIsEmpty(String param) throws EmptyFieldException, Exception {
		if (param.isBlank() || param.isEmpty()) {
			throw new EmptyFieldException("You entered an empty field");
		}

		return true;
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

	private boolean validateISBNLen(String param) throws ISBNLengthException, Exception {
		if (param.length() > 20) {
			throw new ISBNLengthException("ISBN length exceeds 20 characters");
		}

		return true;
	}

	private Book getBookInstance(String isbn, String title, String author, Double price, Integer qty) {
		return new Book(isbn, title, author, price, qty);
	}

}
