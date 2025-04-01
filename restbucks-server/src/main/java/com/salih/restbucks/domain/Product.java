package com.salih.restbucks.domain;

import java.util.List;

public record Product(String name, ProductType type, List<PropertyKey> properties) {
}
