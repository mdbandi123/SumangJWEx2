package org.acumen.training.codes.handler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.acumen.training.codes.exceptions.DoubleParsingException;
import org.acumen.training.codes.exceptions.EmptyFieldException;
import org.acumen.training.codes.exceptions.ISBNLengthException;
import org.acumen.training.codes.exceptions.IntegerParsingException;
import org.acumen.training.codes.model.Book;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "BookEntryHandler", urlPatterns = "/book/handler/save")
public class BookEntryHandler extends HttpServlet {
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
										throws ServletException, IOException {
		LOGGER.info("doGet() called");
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/jsp/book/list_books.html");
		String path = "/Users/michaeldavesumang/"
				+ "Training/Demo/Track7/sumangjwex2"
				+ "/src/main/resources/bookdb.txt";
		File file = new File(path);
		List<Book> books = new ArrayList<>();

		try (Scanner sc = new Scanner(file);) {
			while (sc.hasNext()) {
				String line = sc.nextLine();
				String[] lineArr = line.split(" ");
				String isbn = lineArr[0];
				String title = lineArr[1];
				String author = lineArr[2];
				Double price = parseDouble(lineArr[3]);
				Integer qty = parseInteger(lineArr[4]);

				books.add(new Book(isbn, title, author, price, qty));
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		req.setAttribute("books", books);
		dispatcher.forward(req, resp);
		LOGGER.info("doGet() executed");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
										throws ServletException, IOException {
		LOGGER.info("doPost() called");
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/book/service/filesave");
		String isbn = req.getParameter("isbn");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String price = req.getParameter("price");
		String qty = req.getParameter("qty");
		Book book = null;

		try {
			book = createBookInstance(isbn, title, author, price, qty);
		} catch (EmptyFieldException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new EmptyFieldException(e.getMessage());
		} catch (DoubleParsingException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new DoubleParsingException(e.getMessage());
		} catch (IntegerParsingException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new IntegerParsingException(e.getMessage());
		} catch (ISBNLengthException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new ISBNLengthException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("book", book);
		dispatcher.forward(req, resp);
		LOGGER.info("doPost() executed");
	}

	private boolean validateIsEmpty(String param) 
				throws EmptyFieldException, Exception {
		LOGGER.info("validateIsEmpty() called");
		if (param.isBlank() || param.isEmpty()) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new EmptyFieldException("You entered an empty field");
		}
		
		LOGGER.info("validateIsEmpty() executed");
		return true;
	}

	private Double parseDouble(String param) 
				throws DoubleParsingException, Exception {
		LOGGER.info("parseDouble() called");
		try {
			LOGGER.info("parseDouble() executed");
			return Double.parseDouble(param);
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new DoubleParsingException("Inputted value is not of type double");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LOGGER.info("parseDouble() executed");
		return 0.0;
	}

	private Integer parseInteger(String param) 
				throws IntegerParsingException, Exception {
		LOGGER.info("parseInteger() called");
		try {
			LOGGER.info("parseInteger() executed");
			return Integer.parseInt(param);
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new IntegerParsingException("Inputted value is not of type integer");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LOGGER.info("parseInteger() executed");
		return 0;
	}

	private boolean validateISBNLen(String param) 
				throws ISBNLengthException, Exception {
		LOGGER.info("validateISBNLen() called");

		if (param.length() > 20) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new ISBNLengthException("ISBN length exceeds 20 characters");
		}

		LOGGER.info("validateISBNLen() executed");
		return true;
	}

	private Book createBookInstance(String isbn, String title, String author, String price, String qty) 
				throws EmptyFieldException, DoubleParsingException, IntegerParsingException, ISBNLengthException, Exception {
		LOGGER.info("createBookInstance() called");
		Double parsedPrice = 0.0;
		Integer parsedQty = 0;
		
		try {
			validateIsEmpty(isbn);
			validateIsEmpty(title);
			validateIsEmpty(author);
			validateIsEmpty(price);
			validateIsEmpty(qty);
		} catch (EmptyFieldException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new EmptyFieldException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			parsedPrice = parseDouble(price);
		} catch (DoubleParsingException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new DoubleParsingException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			parsedQty = parseInteger(qty);
		} catch (IntegerParsingException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new IntegerParsingException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			validateISBNLen(isbn);
		} catch (ISBNLengthException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new ISBNLengthException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LOGGER.info("createBookInstance() executed");
		return new Book(isbn, title, author, parsedPrice, parsedQty);
	}

}
