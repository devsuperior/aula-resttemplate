package com.devsuperior.tutresttemplate.services;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devsuperior.tutresttemplate.dto.PageDTO;
import com.devsuperior.tutresttemplate.dto.ProductDTO;

@Service
public class ReportService {

	@Autowired
	private RestTemplate restTemplate;
	
	private String host = "http://localhost:8080";
	
	private String path = host + "/products";

	public ProductDTO findById(Long id) {
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", id.toString());
		
		System.out.println(path + "/{id}");	
		
		ResponseEntity<ProductDTO> result = restTemplate.getForEntity(path + "/{id}", ProductDTO.class, uriVariables);
		return result.getBody();
	}
	
	//https://stackoverflow.com/questions/34647303/spring-resttemplate-with-paginated-api
	public List<ProductDTO> findAll() {
		ParameterizedTypeReference<PageDTO<ProductDTO>> responseType = new ParameterizedTypeReference<PageDTO<ProductDTO>>() { };
		ResponseEntity<PageDTO<ProductDTO>> result = restTemplate.exchange(path, HttpMethod.GET, null, responseType);
		return result.getBody().getContent();
	}
	
	public ProductDTO insert(ProductDTO dto, String bearerToken) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", bearerToken);
		
		HttpEntity<ProductDTO> httpEntity = new HttpEntity<>(dto, headers);
		
		ResponseEntity<ProductDTO> result = restTemplate.exchange(path, HttpMethod.POST, httpEntity, ProductDTO.class);
		return result.getBody();
	}
	
	public ProductDTO update(Long id, ProductDTO dto, String bearerToken) {
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", id.toString());

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", bearerToken);
		
		HttpEntity<ProductDTO> httpEntity = new HttpEntity<>(dto, headers);
		
		ResponseEntity<ProductDTO> result = restTemplate.exchange(path + "/{id}", HttpMethod.PUT, httpEntity, ProductDTO.class, uriVariables);
		return result.getBody();
	}
	
	public Integer delete(Long id, String bearerToken) {
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", id.toString());

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", bearerToken);
		
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);

		ResponseEntity<Void> result = restTemplate.exchange(path + "/{id}", HttpMethod.DELETE, httpEntity, Void.class, uriVariables);
		
		return result.getStatusCodeValue();
	}
}
