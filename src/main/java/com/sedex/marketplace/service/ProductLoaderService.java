package com.sedex.marketplace.service;

import java.util.List;

import org.json.simple.JSONObject;

public interface ProductLoaderService {
	public List<JSONObject> load();
	
}
