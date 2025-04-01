package com.salih.restbucks.domain;

import java.util.List;

public record Item(Product product, int quantity, List<Attribute> attributes) {
}
