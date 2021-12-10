package com.ubayKyu.accountingSystem.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CategoryModel {
	private String categoryid;
	private String body;
	private String caption;
	private LocalDateTime create_date;
	private String userid;
	private int count;

	public CategoryModel() {
	}

	public CategoryModel(String categoryid, String body, String caption, LocalDateTime create_date, String userid,
			int count) {
		this.categoryid = categoryid;
		this.body = body;
		this.caption = caption;
		this.create_date = create_date;
		this.userid = userid;
		this.count = count;
	}

}
