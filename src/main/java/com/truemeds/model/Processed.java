package com.truemeds.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tejas_bhangale_java_output")
public class Processed {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="id")
	 private int id;
	 
	 @Column(name="final_output")
	 private String finalOutput;
	 
	 @Column(name="count")
	 private Integer count;
	 
	 @Column(name="creation_date")
	 private Date creationDate;
	 
	 @Column(name="creation_user")
	 private String creationUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFinalOutput() {
		return finalOutput;
	}

	public void setFinalOutput(String finalOutput) {
		this.finalOutput = finalOutput;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	@Override
	public String toString() {
		return "Processed [id=" + id + ", finalOutput=" + finalOutput + ", count=" + count + ", creationDate="
				+ creationDate + ", creationUser=" + creationUser + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(count, creationDate, creationUser, finalOutput, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processed other = (Processed) obj;
		return Objects.equals(count, other.count) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(creationUser, other.creationUser) && Objects.equals(finalOutput, other.finalOutput)
				&& id == other.id;
	}
	 
	 
}
