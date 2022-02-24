package com.sheryian.major.global;

import java.util.ArrayList;
import java.util.List;

import com.sheryian.major.model.Product;

public class GlobalData {

	
	public static List<Product> cart;
	static {
		cart =new ArrayList<Product>();
	}
}
