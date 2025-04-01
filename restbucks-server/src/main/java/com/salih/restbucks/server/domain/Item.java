package com.salih.restbucks.server.domain;

import java.util.List;

public record Item(Product product, int quantity, List<Attribute> attributes) {
}
