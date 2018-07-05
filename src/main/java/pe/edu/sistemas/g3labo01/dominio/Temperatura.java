package pe.edu.sistemas.g3labo01.dominio;

public class Temperatura {
	
	private String wbhuc;
	private String year;
	private String jan_temp;
	private String feb_temp;
	private String mar_temp;
	private String apr_temp;
	private String may_temp;
	private String jun_temp;
	private String july_temp;
	private String aug_temp;
	private String sept_temp;
	private String oct_temp;
	private String nov_temp;
	private String dec_temp;
	private String annual_temp;
	public Temperatura(String wbhuc, String year, String jan_temp, String feb_temp, String mar_temp, String apr_temp,
			String may_temp, String jun_temp, String july_temp, String aug_temp, String sept_temp, String oct_temp,
			String nov_temp, String dec_temp, String annual_temp) {
		this.wbhuc = wbhuc;
		this.year = year;
		this.jan_temp = jan_temp;
		this.feb_temp = feb_temp;
		this.mar_temp = mar_temp;
		this.apr_temp = apr_temp;
		this.may_temp = may_temp;
		this.jun_temp = jun_temp;
		this.july_temp = july_temp;
		this.aug_temp = aug_temp;
		this.sept_temp = sept_temp;
		this.oct_temp = oct_temp;
		this.nov_temp = nov_temp;
		this.dec_temp = dec_temp;
		this.annual_temp = annual_temp;
	}
	
	public Temperatura() {}

	public String getWbhuc() {
		return wbhuc;
	}

	public void setWbhuc(String wbhuc) {
		this.wbhuc = wbhuc;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getJan_temp() {
		return jan_temp;
	}

	public void setJan_temp(String jan_temp) {
		this.jan_temp = jan_temp;
	}

	public String getFeb_temp() {
		return feb_temp;
	}

	public void setFeb_temp(String feb_temp) {
		this.feb_temp = feb_temp;
	}

	public String getMar_temp() {
		return mar_temp;
	}

	public void setMar_temp(String mar_temp) {
		this.mar_temp = mar_temp;
	}

	public String getApr_temp() {
		return apr_temp;
	}

	public void setApr_temp(String apr_temp) {
		this.apr_temp = apr_temp;
	}

	public String getMay_temp() {
		return may_temp;
	}

	public void setMay_temp(String may_temp) {
		this.may_temp = may_temp;
	}

	public String getJun_temp() {
		return jun_temp;
	}

	public void setJun_temp(String jun_temp) {
		this.jun_temp = jun_temp;
	}

	public String getJuly_temp() {
		return july_temp;
	}

	public void setJuly_temp(String july_temp) {
		this.july_temp = july_temp;
	}

	public String getAug_temp() {
		return aug_temp;
	}

	public void setAug_temp(String aug_temp) {
		this.aug_temp = aug_temp;
	}

	public String getSept_temp() {
		return sept_temp;
	}

	public void setSept_temp(String sept_temp) {
		this.sept_temp = sept_temp;
	}

	public String getOct_temp() {
		return oct_temp;
	}

	public void setOct_temp(String oct_temp) {
		this.oct_temp = oct_temp;
	}

	public String getNov_temp() {
		return nov_temp;
	}

	public void setNov_temp(String nov_temp) {
		this.nov_temp = nov_temp;
	}

	public String getDec_temp() {
		return dec_temp;
	}

	public void setDec_temp(String dec_temp) {
		this.dec_temp = dec_temp;
	}

	public String getAnnual_temp() {
		return annual_temp;
	}

	public void setAnnual_temp(String annual_temp) {
		this.annual_temp = annual_temp;
	}

	@Override
	public String toString() {
		return "Temperatura [wbhuc=" + wbhuc + ", year=" + year + ", jan_temp=" + jan_temp + ", feb_temp=" + feb_temp
				+ ", mar_temp=" + mar_temp + ", apr_temp=" + apr_temp + ", may_temp=" + may_temp + ", jun_temp="
				+ jun_temp + ", july_temp=" + july_temp + ", aug_temp=" + aug_temp + ", sept_temp=" + sept_temp
				+ ", oct_temp=" + oct_temp + ", nov_temp=" + nov_temp + ", dec_temp=" + dec_temp + ", annual_temp="
				+ annual_temp + "]";
	}
	
	
}
