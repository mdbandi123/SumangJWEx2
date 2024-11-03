package org.acumen.training.codes.service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.acumen.training.codes.exceptions.DecimalPrecisionException;
import org.acumen.training.codes.exceptions.EmptyOperandException;
import org.acumen.training.codes.exceptions.LimitException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SimpleCalcuServlet", urlPatterns = "/math/calc")
public class SimpleCalcuServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
										throws ServletException, IOException {
		LOGGER.info("doGet() executed");
		resp.sendRedirect("/sumangweb2/jsp/calculator.html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
										throws ServletException, IOException {
		LOGGER.info("doPost() called");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/calc_result.html");
		String operOne = req.getParameter("operOne");
		String operTwo = req.getParameter("operTwo");
		String operator = req.getParameter("operator");

		Double compVal = 0.0;
		try {
			compVal = computeValue(operOne, operTwo, operator);
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new NumberFormatException(e.getMessage());
		} catch (DecimalPrecisionException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new DecimalPrecisionException(e.getMessage());
		} catch (EmptyOperandException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new EmptyOperandException(e.getMessage());
		} catch (LimitException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new LimitException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		req.setAttribute("operOne", operOne);
		req.setAttribute("operTwo", operTwo);
		req.setAttribute("operator", operator);
		req.setAttribute("compVal", compVal);

		dispatcher.forward(req, resp);
		LOGGER.info("doPost() executed");
	}

	private Double parseOperand(String operand) 
				throws NumberFormatException, EmptyOperandException, Exception {
		LOGGER.info("parseOperand() called");
		if (operand.isBlank() || operand.isEmpty()) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new EmptyOperandException("Operand cannot be empty");
		}
		
		try {
			LOGGER.info("parseOperand() executed");
			return Double.parseDouble(operand);
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new NumberFormatException("Unable to parse inputted operand");
		}
	}

	private boolean validatePrecision(Double operand) 
				throws DecimalPrecisionException, LimitException ,Exception {
		LOGGER.info("validatePrecision() called");
		boolean isWithinDecRange = 
				String.valueOf(operand).trim().matches("^(-)?[0-9]+(\\.[0-9]{1,3})?$");
		
		if(operand < -10000000 || operand > 1000000) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new LimitException("Operand exceeds number limit");
		} else if (!isWithinDecRange) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new DecimalPrecisionException("Operand exceeds 3 decimal places");
		}
		
		LOGGER.info("validatePrecision() executed");
		return true;
	}

	private Double computeValue(String operOne, String operTwo, String operation)
								throws NumberFormatException, DecimalPrecisionException, 
														EmptyOperandException, Exception {
		LOGGER.info("computeValue() called");
		Double valOne = 0.0;
		Double valTwo = 0.0;
		Double computedVal = 0.0;
		try {
			valOne = parseOperand(operOne);
			valTwo = parseOperand(operTwo);

			validatePrecision(valOne);
			validatePrecision(valTwo);
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new NumberFormatException(e.getMessage());
		} catch (DecimalPrecisionException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new DecimalPrecisionException(e.getMessage());
		} catch (EmptyOperandException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new EmptyOperandException(e.getMessage());
		} catch (LimitException e) {
			LOGGER.log(Level.SEVERE, "exception encountered");
			throw new LimitException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		switch (operation) {
		case "+":
			computedVal = valOne + valTwo;
			break;
		case "-":
			computedVal = valOne - valTwo;
			break;
		case "*":
			computedVal = valOne * valTwo;
			break;
		case "/":
			computedVal = valOne / valTwo;
			break;
		}
		
		LOGGER.info("computeValue() executed");
		return computedVal;
	}
}
