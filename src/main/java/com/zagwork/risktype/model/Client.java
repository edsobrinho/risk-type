package com.zagwork.risktype.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.zagwork.risktype.enums.RiskType;

@Entity
@Table(name="CLIENT")
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;

	@Column(name="CREDIT_LIMIT", nullable=false)
	private BigDecimal creditLimit;

	@Column(name="RISCK_TYPE", nullable=false)
	@Enumerated
	private RiskType risckType;
	
	@Column(name="PERCENT_RISK", nullable=false)
	private Integer percentRisk;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public RiskType getRisckType() {
		return risckType;
	}

	public void setRisckType(RiskType risckType) {
		this.risckType = risckType;
	}
	
	public Integer getPercentRisk() {
		return percentRisk;
	}

	public void setPercentRisk(Integer percentRisk) {
		this.percentRisk = percentRisk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}