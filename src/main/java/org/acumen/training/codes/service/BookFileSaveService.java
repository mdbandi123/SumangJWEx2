package org.acumen.training.codes.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.acumen.training.codes.model.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "BookFileSaveService", urlPatterns = "/book/service/filesave")
public class BookFileSaveService extends HttpServlet {
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
										throws ServletException, IOException {
		LOGGER.info("getPost() called");
		Book book = (Book) req.getAttribute("book");
		String path = "/Users/michaeldavesumang/"
				+ "Training/Demo/Track7/sumangjwex2/"
				+ "src/main/resources/bookdb.txt";
		
		appendToFile(book, path);
		resp.sendRedirect("/sumangweb2/book/handler/save");
		LOGGER.info("getPost() executed");
	}

	private boolean appendToFile(Book book, String path) {
		LOGGER.info("appendToFile() called");
		String payload = "%s %s %s %f %d\n"
						.formatted(book.isbn(), book.title(), 
									book.author(), book.price(),
									book.qty());
		Charset utf8 = Charset.forName("UTF-8");
		Path filePath = Paths.get(path);
		
		try (BufferedWriter writer = Files.newBufferedWriter(filePath, utf8, 
														StandardOpenOption.APPEND, 
														StandardOpenOption.CREATE);) {
			writer.write(payload);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			e.printStackTrace();
		}

		LOGGER.info("appendToFile() called");
		return false;
	}
}
