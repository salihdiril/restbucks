package com.salih.restbucks.server.domain;

import java.util.List;

public record Product(String name, ProductType type, List<PropertyKey> properties) {
}
