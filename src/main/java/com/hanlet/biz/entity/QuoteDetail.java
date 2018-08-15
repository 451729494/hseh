package com.hanlet.biz.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hanlet.util.MoneySerializer;

@Entity
@Table(name="t_quote_detail")
public class QuoteDetail {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String quoteDetailId;
	
	@OneToOne
	@JoinColumn(name = "quoteId")
	private Quote quote;
	
	@OneToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	@Column(precision = 12, scale = 2)
	@NumberFormat(pattern="####.##")
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal price;
	
	@Column(precision = 12, scale = 2)
	@NumberFormat(pattern="####.##")
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal reportPrice;
	
	private Integer productCount;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDateTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateDateTime;
	public String getQuoteDetailId() {
		return quoteDetailId;
	}
	public void setQuoteDetailId(String quoteDetailId) {
		this.quoteDetailId = quoteDetailId;
	}
	public Quote getQuote() {
		return quote;
	}
	public void setQuote(Quote quote) {
		this.quote = quote;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getReportPrice() {
		return reportPrice;
	}
	public void setReportPrice(BigDecimal reportPrice) {
		this.reportPrice = reportPrice;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
}
