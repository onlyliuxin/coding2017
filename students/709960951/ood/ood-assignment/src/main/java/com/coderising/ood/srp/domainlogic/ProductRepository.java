package com.coderising.ood.srp.domainlogic;

import java.io.IOException;
import java.util.List;

public abstract class ProductRepository {
	public abstract List<Product> getPromotionProducts() throws IOException;
}
