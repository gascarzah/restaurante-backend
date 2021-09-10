package com.gafahtec.util;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {

	private String from;
	private String to;
	private String subject;
	private Map<String, Object> model;

	

}
