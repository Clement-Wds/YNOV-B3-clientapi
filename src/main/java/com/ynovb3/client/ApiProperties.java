package com.ynovb3.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.ynovb3.productapi")
public class ApiProperties {
	
	//sera associé au prefix + nom de l'attribut donc com.ynovb3.productapi.url
	private String url;
	
	//sera associé au prefix + nom de l'attribut donc com.ynovb3.productApi.publicurl
	private String publicurl;
	// je n'utilise pas cette variable avec le fichier application.properties
	private String token;
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPublicurl() {
		return publicurl;
	}

	public void setPublicurl(String publicurl) {
		this.publicurl = publicurl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
