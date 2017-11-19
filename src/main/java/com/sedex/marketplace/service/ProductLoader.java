package com.sedex.marketplace.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ProductLoader implements ProductLoaderService {

	public List<JSONObject> load() {

		JSONParser jsonParser = new JSONParser();
		List<JSONObject> productObjectList = new ArrayList<JSONObject>();
		try {
			FileReader reader = new FileReader("products.json");
			Object obj = jsonParser.parse(reader);

			JSONArray productList = (JSONArray) obj;

			for (int i = 0; i < productList.size(); i++) {
				productObjectList.add((JSONObject) productList.get(i));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return productObjectList;
	}

}
