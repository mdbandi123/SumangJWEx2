package org.acumen.training.codes.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "BookEntryServlet", urlPatterns = "/book/entry")
public class BookEntryServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
											throws ServletException, IOException {
		LOGGER.info("doPost() executed");
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/book/handler/save");
		dispatcher.forward(req, resp);
	}
}
