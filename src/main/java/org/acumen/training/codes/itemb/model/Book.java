package org.acumen.training.codes.itemb.model;

public record Book(String isbn, String title, String author, Double price, Integer qty) {

	@Override
	public final String toString() {
		return "%s %s %s %f %d".formatted(isbn, title, author, price, qty);
	}
}
