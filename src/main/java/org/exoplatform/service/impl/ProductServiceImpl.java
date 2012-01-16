package org.exoplatform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.exoplatform.model.Product;
import org.exoplatform.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(1, "Ironman", "Ironman", 12));
        products.add(new Product(2, "Wolverine", "Wolverine", 15.5));
        products.add(new Product(3, "Spiderman", "Spiderman", 13));
        products.add(new Product(4, "Thor", "Thor", 10));
        products.add(new Product(5, "Hulk", "Hulk", 11));
        products.add(new Product(6, "Captain America", "Captain America", 15));
        products.add(new Product(7, "Human Torch", "Human Torch", 11));
        products.add(new Product(8, "Magneto", "Magneto", 17));
        products.add(new Product(9, "Dardevil", "Dardevil", 16.5));
		return products;
	}

}
