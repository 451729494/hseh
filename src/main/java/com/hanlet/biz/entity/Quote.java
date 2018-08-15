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
@Table(name="t_quote")
public class Quote {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String quoteId;
	
	private String quoteName;
	
	@OneToOne
	@JoinColumn(name = "clienteleId")
	private Clientele clientele;
	
	@Column(precision = 12, scale = 2)
	@NumberFormat(pattern="####.##")
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal reportPrice;
	
	@Column(precision = 12, scale = 2)
	@NumberFormat(pattern="####.##")
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal productPrice;
	
	private String createUser;
	
	private String createUserPhone;
	
	private Integer status;//1待制作报价2报价生成3初步意向4报价方案提交5已签合同6执行合同7完成
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDateTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateDateTime;

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public String getQuoteName() {
		return quoteName;
	}

	public void setQuoteName(String quoteName) {
		this.quoteName = quoteName;
	}

	public Clientele getClientele() {
		return clientele;
	}

	public void setClientele(Clientele clientele) {
		this.clientele = clientele;
	}

	public BigDecimal getReportPrice() {
		return reportPrice;
	}

	public void setReportPrice(BigDecimal reportPrice) {
		this.reportPrice = reportPrice;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateUserPhone() {
		return createUserPhone;
	}

	public void setCreateUserPhone(String createUserPhone) {
		this.createUserPhone = createUserPhone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
