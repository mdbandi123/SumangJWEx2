package org.acumen.training.codes.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RetrieveBookService", urlPatterns = "/book/service/fileread")
public class RetrieveBookService extends HttpServlet {
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
											throws ServletException, IOException {
		LOGGER.info("doGet() called");
		List<String> titles = new ArrayList<>();
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/book/delete");
		String path = "/Users/michaeldavesumang/"
				+ "Training/Demo/Track7/"
				+ "sumangjwex2/src/main/"
				+ "resources/bookdb.txt";
		File file = new File(path);

		try (Scanner sc = new Scanner(file);) {
			while (sc.hasNext()) {
				String line = sc.nextLine();
				String[] lineArr = line.split(" ");
				String title = lineArr[1];
				titles.add(title);
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("titles", titles);
		dispatcher.forward(req, resp);
		LOGGER.info("doGet() executed");
	}

}
