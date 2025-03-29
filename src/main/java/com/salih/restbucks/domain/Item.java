package com.salih.restbucks.domain;

public record Item(Product product, int quantity, Milk milk, Size size, boolean whippedCream) {
}
