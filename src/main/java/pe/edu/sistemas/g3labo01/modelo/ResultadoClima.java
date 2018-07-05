package pe.edu.sistemas.g3labo01.modelo;

public class ResultadoClima {
	
	private String year;
	private String mes;
	private String temp;
	private String precip;
	
	public ResultadoClima(String year, String mes, String temp, String precip) {
		this.year = year;
		this.mes = mes;
		this.temp = temp;
		this.precip = precip;
	}
	
	public ResultadoClima(){}

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

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getPrecip() {
		return precip;
	}

	public void setPrecip(String precip) {
		this.precip = precip;
	}

	@Override
	public String toString() {
		return "ResultadoClima [year=" + year + ", mes=" + mes + ", temp=" + temp + ", precip=" + precip + "]";
	}
	
	
	
}
