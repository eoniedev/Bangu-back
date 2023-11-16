package com.ssafy.happyhouse.model.dto;

public class HouseInfoDto {
	//Dto는 테이블 개수만큼 필요 
	private long aptCode;
	private int buildYear;
	private String roadName;
	private String dong;
	private String apartmentName;
	private String lng;
	private String lat;
	
	public HouseInfoDto() {
		super();
	}
	public HouseInfoDto(long aptCode, int buildYear, String roadName, String dong, String apartmentName, String lng,
			String lat) {
		super();
		this.aptCode = aptCode;
		this.buildYear = buildYear;
		this.roadName = roadName;
		this.dong = dong;
		this.apartmentName = apartmentName;
		this.lng = lng;
		this.lat = lat;
	}
	
	public long getAptCode() {
		return aptCode;
	}
	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartmentName == null) ? 0 : apartmentName.hashCode());
		result = prime * result + (int) (aptCode ^ (aptCode >>> 32));
		result = prime * result + buildYear;
		result = prime * result + ((dong == null) ? 0 : dong.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lng == null) ? 0 : lng.hashCode());
		result = prime * result + ((roadName == null) ? 0 : roadName.hashCode());
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
		HouseInfoDto other = (HouseInfoDto) obj;
		if (apartmentName == null) {
			if (other.apartmentName != null)
				return false;
		} else if (!apartmentName.equals(other.apartmentName))
			return false;
		if (aptCode != other.aptCode)
			return false;
		if (buildYear != other.buildYear)
			return false;
		if (dong == null) {
			if (other.dong != null)
				return false;
		} else if (!dong.equals(other.dong))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lng == null) {
			if (other.lng != null)
				return false;
		} else if (!lng.equals(other.lng))
			return false;
		if (roadName == null) {
			if (other.roadName != null)
				return false;
		} else if (!roadName.equals(other.roadName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "HouseInfoDto [aptCode=" + aptCode + ", buildYear=" + buildYear + ", roadName=" + roadName + ", dong="
				+ dong + ", apartmentName=" + apartmentName + ", lng=" + lng + ", lat=" + lat + "]";
	}

}
