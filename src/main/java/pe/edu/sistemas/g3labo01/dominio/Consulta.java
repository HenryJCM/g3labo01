package pe.edu.sistemas.g3labo01.dominio;

import java.util.List;

public class Consulta {
	
	private String seriesName;
	private String seriesCode;
	private String countryName;
	private String countryCode;
	List<String> listEstadistica;
	
	public Consulta(){}
	
	public Consulta(String seriesName, String seriesCode, String countryName, String countryCode,
			List<String> listEstadistica) {
		this.seriesName = seriesName;
		this.seriesCode = seriesCode;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.listEstadistica = listEstadistica;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getSeriesCode() {
		return seriesCode;
	}

	public void setSeriesCode(String seriesCode) {
		this.seriesCode = seriesCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public List<String> getListEstadistica() {
		return listEstadistica;
	}

	public void setListEstadistica(List<String> listEstadistica) {
		this.listEstadistica = listEstadistica;
	}

	@Override
	public String toString() {
		return "Consultas [seriesName=" + seriesName + ", seriesCode=" + seriesCode + ", countryName=" + countryName
				+ ", countryCode=" + countryCode + ", listEstadistica=" + listEstadistica + "]";
	}
	
	
	
	
	
}
