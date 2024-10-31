package org.acumen.training.codes.itema;

import java.io.IOException;

import org.acumen.training.codes.itema.exceptions.DecimalPrecisionException;
import org.acumen.training.codes.itema.exceptions.EmptyOperandException;
import org.acumen.training.codes.itema.exceptions.LimitException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SimpleCalcuServlet", urlPatterns = "/math/calc")
public class SimpleCalcuServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/sumangweb2/jsp/calculator.html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/calc_result.html");
		String operOne = req.getParameter("operOne");
		String operTwo = req.getParameter("operTwo");
		String operator = req.getParameter("operator");

		Double compVal = 0.0;
		try {
			compVal = computeValue(operOne, operTwo, operator);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(e.getMessage());
		} catch (DecimalPrecisionException e) {
			throw new DecimalPrecisionException(e.getMessage());
		} catch (EmptyOperandException e) {
			throw new EmptyOperandException(e.getMessage());
		} catch (LimitException e) {
			throw new LimitException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		req.setAttribute("operOne", operOne);
		req.setAttribute("operTwo", operTwo);
		req.setAttribute("operator", operator);
		req.setAttribute("compVal", compVal);

		dispatcher.forward(req, resp);
	}

	private Double parseOperand(String operand) throws NumberFormatException, EmptyOperandException, Exception {
		if (operand.isBlank() || operand.isEmpty()) {
			throw new EmptyOperandException("Operand cannot be empty");
		}
		try {
			return Double.parseDouble(operand);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Unable to parse inputted operand");
		}
	}

	private boolean validatePrecision(Double operand) throws DecimalPrecisionException, LimitException ,Exception {
		boolean isWithinDecRange = String.valueOf(operand).trim().matches("^(-)?[0-9]+(\\.[0-9]{1,3})?$");
		
		if(operand < -10000000 || operand > 1000000) {
			throw new LimitException("Operand exceeds number limit");
		} else if (!isWithinDecRange) {
			throw new DecimalPrecisionException("Operand exceeds 3 decimal places");
		}
		return true;
	}

	private Double computeValue(String operOne, String operTwo, String operation)
			throws NumberFormatException, DecimalPrecisionException, EmptyOperandException, Exception {
		Double valOne = 0.0;
		Double valTwo = 0.0;
		Double computedVal = 0.0;
		try {
			valOne = parseOperand(operOne);
			valTwo = parseOperand(operTwo);

			validatePrecision(valOne);
			validatePrecision(valTwo);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(e.getMessage());
		} catch (DecimalPrecisionException e) {
			throw new DecimalPrecisionException(e.getMessage());
		} catch (EmptyOperandException e) {
			throw new EmptyOperandException(e.getMessage());
		} catch (LimitException e) {
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

		return computedVal;
	}
}
