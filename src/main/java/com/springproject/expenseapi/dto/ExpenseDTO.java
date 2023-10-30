package com.springproject.expenseapi.dto;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Date;

public class ExpenseDTO {

	private long id;
	private String name;
	private String description;
	private BigDecimal amount;
	private String category;
	private Date date;
	private Timestamp createAt;
	private Timestamp updateAt;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Timestamp getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
	public Timestamp getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}
	@Override
	public String toString() {
		return "ExpenseDTO [id=" + id + ", name=" + name + ", description=" + description + ", amount=" + amount
				+ ", category=" + category + ", date=" + date + ", createAt=" + createAt + ", updateAt=" + updateAt
				+ "]";
	}
	
	
}
