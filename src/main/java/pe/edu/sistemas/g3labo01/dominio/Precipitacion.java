package pe.edu.sistemas.g3labo01.dominio;

public class Precipitacion {

	private String wbhuc;
	private String year;
	private String jan_precip;
	private String feb_precip;
	private String mar_precip;
	private String apr_precip;
	private String may_precip;
	private String jun_precip;
	private String july_precip;
	private String aug_precip;
	private String sept_precip;
	private String oct_precip;
	private String nov_precip;
	private String dec_precip;
	private String annual_precip;
	
	
	public Precipitacion(String wbhuc, String year, String jan_precip, String feb_precip, String mar_precip,
			String apr_precip, String may_precip, String jun_precip, String july_precip, String aug_precip,
			String sept_precip, String oct_precip, String nov_precip, String dec_precip, String annual_precip) {
		
		this.wbhuc = wbhuc;
		this.year = year;
		this.jan_precip = jan_precip;
		this.feb_precip = feb_precip;
		this.mar_precip = mar_precip;
		this.apr_precip = apr_precip;
		this.may_precip = may_precip;
		this.jun_precip = jun_precip;
		this.july_precip = july_precip;
		this.aug_precip = aug_precip;
		this.sept_precip = sept_precip;
		this.oct_precip = oct_precip;
		this.nov_precip = nov_precip;
		this.dec_precip = dec_precip;
		this.annual_precip = annual_precip;
	}
	
	public Precipitacion(){}

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

	public String getJan_precip() {
		return jan_precip;
	}

	public void setJan_precip(String jan_precip) {
		this.jan_precip = jan_precip;
	}

	public String getFeb_precip() {
		return feb_precip;
	}

	public void setFeb_precip(String feb_precip) {
		this.feb_precip = feb_precip;
	}

	public String getMar_precip() {
		return mar_precip;
	}

	public void setMar_precip(String mar_precip) {
		this.mar_precip = mar_precip;
	}

	public String getApr_precip() {
		return apr_precip;
	}

	public void setApr_precip(String apr_precip) {
		this.apr_precip = apr_precip;
	}

	public String getMay_precip() {
		return may_precip;
	}

	public void setMay_precip(String may_precip) {
		this.may_precip = may_precip;
	}

	public String getJun_precip() {
		return jun_precip;
	}

	public void setJun_precip(String jun_precip) {
		this.jun_precip = jun_precip;
	}

	public String getJuly_precip() {
		return july_precip;
	}

	public void setJuly_precip(String july_precip) {
		this.july_precip = july_precip;
	}

	public String getAug_precip() {
		return aug_precip;
	}

	public void setAug_precip(String aug_precip) {
		this.aug_precip = aug_precip;
	}

	public String getSept_precip() {
		return sept_precip;
	}

	public void setSept_precip(String sept_precip) {
		this.sept_precip = sept_precip;
	}

	public String getOct_precip() {
		return oct_precip;
	}

	public void setOct_precip(String oct_precip) {
		this.oct_precip = oct_precip;
	}

	public String getNov_precip() {
		return nov_precip;
	}

	public void setNov_precip(String nov_precip) {
		this.nov_precip = nov_precip;
	}

	public String getDec_precip() {
		return dec_precip;
	}

	public void setDec_precip(String dec_precip) {
		this.dec_precip = dec_precip;
	}

	public String getAnnual_precip() {
		return annual_precip;
	}

	public void setAnnual_precip(String annual_precip) {
		this.annual_precip = annual_precip;
	}

	@Override
	public String toString() {
		return "Precipitacion [wbhuc=" + wbhuc + ", year=" + year + ", jan_precip=" + jan_precip + ", feb_precip="
				+ feb_precip + ", mar_precip=" + mar_precip + ", apr_precip=" + apr_precip + ", may_precip="
				+ may_precip + ", jun_precip=" + jun_precip + ", july_precip=" + july_precip + ", aug_precip="
				+ aug_precip + ", sept_precip=" + sept_precip + ", oct_precip=" + oct_precip + ", nov_precip="
				+ nov_precip + ", dec_precip=" + dec_precip + ", annual_precip=" + annual_precip + "]";
	}
	
	
	
	
}
