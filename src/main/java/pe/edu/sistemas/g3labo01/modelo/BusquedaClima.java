package pe.edu.sistemas.g3labo01.modelo;

public class BusquedaClima {
	
	private String year;
	private String mes;
	
	public BusquedaClima(String year, String mes) {
		this.year = year;
		this.mes = mes;
	}
	
	public BusquedaClima(){}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	@Override
	public String toString() {
		return "BusquedaClima [year=" + year + ", mes=" + mes + "]";
	}
	
	
	
}
