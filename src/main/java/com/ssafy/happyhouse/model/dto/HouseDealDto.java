package com.ssafy.happyhouse.model.dto;

public class HouseDealDto {

	private long no;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String floor;
	private String cancelDealType;
	private long aptCode;

	public HouseDealDto() {
	}

	public HouseDealDto(long no, String dealAmount, int dealYear, int dealMonth, int dealDay, String area, String floor,
			String cancelDealType, long aptCode) {
		this.no = no;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.cancelDealType = cancelDealType;
		this.aptCode = aptCode;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getCancelDealType() {
		return cancelDealType;
	}

	public void setCancelDealType(String cancelDealType) {
		this.cancelDealType = cancelDealType;
	}

	public long getAptCode() {
		return aptCode;
	}

	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (aptCode ^ (aptCode >>> 32));
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((cancelDealType == null) ? 0 : cancelDealType.hashCode());
		result = prime * result + ((dealAmount == null) ? 0 : dealAmount.hashCode());
		result = prime * result + dealDay;
		result = prime * result + dealMonth;
		result = prime * result + dealYear;
		result = prime * result + ((floor == null) ? 0 : floor.hashCode());
		result = prime * result + (int) (no ^ (no >>> 32));
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
		HouseDealDto other = (HouseDealDto) obj;
		if (aptCode != other.aptCode)
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (cancelDealType == null) {
			if (other.cancelDealType != null)
				return false;
		} else if (!cancelDealType.equals(other.cancelDealType))
			return false;
		if (dealAmount == null) {
			if (other.dealAmount != null)
				return false;
		} else if (!dealAmount.equals(other.dealAmount))
			return false;
		if (dealDay != other.dealDay)
			return false;
		if (dealMonth != other.dealMonth)
			return false;
		if (dealYear != other.dealYear)
			return false;
		if (floor == null) {
			if (other.floor != null)
				return false;
		} else if (!floor.equals(other.floor))
			return false;
		if (no != other.no)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HouseDealDto [no=" + no + ", dealAmount=" + dealAmount + ", dealYear=" + dealYear + ", dealMonth="
				+ dealMonth + ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor + ", cancelDealType="
				+ cancelDealType + ", aptCode=" + aptCode + "]";
	}

}
