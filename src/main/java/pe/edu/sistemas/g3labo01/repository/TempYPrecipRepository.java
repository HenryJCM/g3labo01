package pe.edu.sistemas.g3labo01.repository;

import java.util.List;
import java.util.Random;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

import pe.edu.sistemas.g3labo01.dominio.Precipitacion;
import pe.edu.sistemas.g3labo01.dominio.Temperatura;
import pe.edu.sistemas.g3labo01.modelo.ResultadoClima;

public class TempYPrecipRepository {
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	public Key agregarEntidadPrecipitacion(Precipitacion pr) {
		
		Entity precipitacion = new Entity("Precipitacion");
		
		precipitacion.setProperty("wbhuc",pr.getWbhuc());
		precipitacion.setProperty("year", pr.getYear());
		precipitacion.setProperty("Jan_Precip", pr.getJan_precip());
		precipitacion.setProperty("Feb_Precip", pr.getFeb_precip());
		precipitacion.setProperty("Mar_Precip", pr.getMar_precip());
		precipitacion.setProperty("Apr_Precip", pr.getApr_precip());
		precipitacion.setProperty("May_Precip", pr.getMay_precip());
		precipitacion.setProperty("Jun_Precip", pr.getJun_precip());
		precipitacion.setProperty("July_Precip", pr.getJuly_precip());
		precipitacion.setProperty("Aug_Precip", pr.getAug_precip());
		precipitacion.setProperty("Sept_Precip", pr.getSept_precip());
		precipitacion.setProperty("Oct_Precip", pr.getOct_precip());
		precipitacion.setProperty("Nov_Precip", pr.getNov_precip());
		precipitacion.setProperty("Dec_Precip", pr.getDec_precip());
		precipitacion.setProperty("Annual_Precip", pr.getAnnual_precip());
	
		Key key_temp = datastore.put(precipitacion);
		return key_temp;
		
	}

	public Key agregarEntidadTemperatura(Temperatura tm) {
		
		Entity temperatura = new Entity("Temperatura");
		
		temperatura.setProperty("wbhuc",tm.getWbhuc());
		temperatura.setProperty("year", tm.getYear());
		temperatura.setProperty("Jan_Temp", tm.getJan_temp());
		temperatura.setProperty("Feb_Temp", tm.getFeb_temp());
		temperatura.setProperty("Mar_Temp", tm.getMar_temp());
		temperatura.setProperty("Apr_Temp", tm.getApr_temp());
		temperatura.setProperty("May_Temp", tm.getMay_temp());
		temperatura.setProperty("Jun_Temp", tm.getJun_temp());
		temperatura.setProperty("July_Temp", tm.getJuly_temp());
		temperatura.setProperty("Aug_Temp", tm.getAug_temp());
		temperatura.setProperty("Sept_Temp", tm.getSept_temp());
		temperatura.setProperty("Oct_Temp", tm.getOct_temp());
		temperatura.setProperty("Nov_Temp", tm.getNov_temp());
		temperatura.setProperty("Dec_Temp", tm.getDec_temp());
		temperatura.setProperty("Annual_Temp", tm.getAnnual_temp());
	
		Key key_temp = datastore.put(temperatura);
		return key_temp;
		
	}

	public boolean existenDatosTemperatura() {
		Query qt = new Query("Temperatura");
		
		PreparedQuery pqt = datastore.prepare(qt);
		List<Entity> resultTemperatura = pqt.asList(FetchOptions.Builder.withLimit(1));
		if(!resultTemperatura.isEmpty())
			return true;
		else
			return false;
	}
	
	public boolean existenDatosPrecipitacion() {
		Query qp = new Query("Precipitacion");
		
		PreparedQuery pqp = datastore.prepare(qp);
		List<Entity> resultPrecipitacion = pqp.asList(FetchOptions.Builder.withLimit(1));
		if(!resultPrecipitacion.isEmpty())
			return true;
		else
			return false;
	}

	public ResultadoClima obtenerTemperaturaYPrecipitacion(String year, String mes) {
		String mesTemp = convertirMesTemp(mes);
		String mesPrecip = convertirMesPrecip(mes);
		
		Query qt = new Query("Temperatura").addFilter("year", FilterOperator.EQUAL, year);
		PreparedQuery pqt = datastore.prepare(qt);
		List<Entity> resultTemperatura = pqt.asList(FetchOptions.Builder.withLimit(26));
		
		Random rand = new Random();
		Entity randomTemperatura = resultTemperatura.get(rand.nextInt(resultTemperatura.size())); 
	
		Query qp = new Query("Precipitacion").addFilter("wbhuc", FilterOperator.EQUAL, randomTemperatura.getProperty("wbhuc"));
		PreparedQuery pqp = datastore.prepare(qp);
		List<Entity> resultPrecipitacion = pqp.asList(FetchOptions.Builder.withLimit(1));
		Entity randomPrecipitacion = resultPrecipitacion.get(0);
		
		ResultadoClima resultClima = new ResultadoClima();
		resultClima.setYear(year);
		resultClima.setMes(mes);
		resultClima.setTemp(Double.valueOf((String) randomTemperatura.getProperty(mesTemp)));
		resultClima.setPrecip(Double.valueOf((String) randomPrecipitacion.getProperty(mesPrecip)));
		
		return resultClima;
	}

	private String convertirMesPrecip(String mes) {
		switch(mes){
		case "Enero": return "Jan_Precip"; 
		case "Febrero": return "Feb_Precip";
		case "Marzo": return "Mar_Precip";
		case "Abril": return "Apr_Precip"; 
		case "Mayo": return "May_Precip"; 
		case "Junio": return "Jun_Precip"; 
		case "Julio": return "July_Precip"; 
		case "Agosto": return "Aug_Precip"; 
		case "Setiembre": return "Sept_Precip"; 
		case "Octubre": return "Oct_Precip"; 
		case "Noviembre": return "Nov_Precip"; 
		case "Diciembre": return "Dec_Precip"; 
		}
		
		return "Jan_Precip";
	}

	private String convertirMesTemp(String mes) {
		switch(mes){
		case "Enero": return "Jan_Temp"; 
		case "Febrero": return "Feb_Temp";
		case "Marzo": return "Mar_Temp";
		case "Abril": return "Apr_Temp"; 
		case "Mayo": return "May_Temp"; 
		case "Junio": return "Jun_Temp"; 
		case "Julio": return "July_Temp"; 
		case "Agosto": return "Aug_Temp"; 
		case "Setiembre": return "Sept_Temp"; 
		case "Octubre": return "Oct_Temp";
		case "Noviembre": return "Nov_Temp"; 
		case "Diciembre": return "Dec_Temp"; 
		}
		
		return "Jan_Temp";
	}

}
