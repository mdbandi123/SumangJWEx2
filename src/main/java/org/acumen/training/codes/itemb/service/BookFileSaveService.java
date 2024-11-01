package org.acumen.training.codes.itemb.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.acumen.training.codes.itemb.model.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "BookFileSaveService", urlPatterns = "/book/service/filesave")
public class BookFileSaveService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Book book = (Book) req.getAttribute("book");
		String path = "/Users/michaeldavesumang/Training/Demo/Track7/sumangjwex2/src/main/resources/bookdb.txt";
//		String path = "./src/main/resources/bookdb.txt";
		appendToFile(book, path);
		resp.sendRedirect("/sumangweb2/book/handler/save");
	}

	private boolean appendToFile(Book book, String path) {
		String payload = "%s %s %s %f %d\n".formatted(book.isbn(), book.title(), book.author(), book.price(), book.qty());
		Charset utf8 = Charset.forName("UTF-8");
		Path filePath = Paths.get(path);
		try (BufferedWriter writer = Files.newBufferedWriter(filePath, utf8, 
				StandardOpenOption.APPEND, 
				StandardOpenOption.CREATE);) {
			writer.write(payload);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
