package pe.edu.sistemas.g3labo01.repository;

import java.util.List;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

import pe.edu.sistemas.g3labo01.dominio.Consulta;

public class ConsultaRepository {
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	public Key agregarEntidad(Consulta cons){
		
		Entity consulta = new Entity("Consulta");
		
		int fecha = 2005;
		for(String s: cons.getListEstadistica()){
			consulta.setProperty(String.valueOf(fecha), s);
			fecha++;
		}
		consulta.setProperty("CountryCode",cons.getCountryCode());
		consulta.setProperty("CountryName", cons.getCountryName());
		consulta.setProperty("SeriesCode", cons.getSeriesCode());
		consulta.setProperty("SeriesName", cons.getSeriesName());
	
		Key key_temp = datastore.put(consulta);
		return key_temp;
	}
	
	
	public List<Entity> realizarConsulta(String idIndicador){
		
		Query q = new Query("Consulta").addFilter("SeriesCode", FilterOperator.EQUAL, idIndicador);
		
			PreparedQuery pq = datastore.prepare(q);
			List<Entity> resultConsulta = pq.asList(FetchOptions.Builder.withLimit(10));
			return resultConsulta;
	}
	
	
	public boolean existenDatos(){
		Query q = new Query("Consulta");
		
			PreparedQuery pq = datastore.prepare(q);
			List<Entity> resultConsulta = pq.asList(FetchOptions.Builder.withLimit(1));
			if(!resultConsulta.isEmpty())
				return true;
			else
				return false;
	}
	
}
