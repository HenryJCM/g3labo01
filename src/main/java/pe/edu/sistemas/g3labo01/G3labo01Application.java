package pe.edu.sistemas.g3labo01;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csvreader.CsvReader;
import com.google.appengine.api.datastore.Entity;

import pe.edu.sistemas.g3labo01.dominio.Consulta;
import pe.edu.sistemas.g3labo01.dominio.Precipitacion;
import pe.edu.sistemas.g3labo01.dominio.Search;
import pe.edu.sistemas.g3labo01.dominio.Temperatura;
import pe.edu.sistemas.g3labo01.modelo.BusquedaClima;
import pe.edu.sistemas.g3labo01.modelo.ResultadoClima;
import pe.edu.sistemas.g3labo01.repository.ConsultaRepository;
import pe.edu.sistemas.g3labo01.repository.TempYPrecipRepository;

@Configuration
@SpringBootApplication 
@Controller
public class G3labo01Application {
	private static final Log LOG =LogFactory.getLog(G3labo01Application.class);
	private String consulta = "";
	ConsultaRepository consRep = new ConsultaRepository();
	boolean resultadoConsulta = false;
	
	
	//Websockets
	TempYPrecipRepository typRep = new TempYPrecipRepository();
	boolean resultadoTemperatura = false;
	boolean resultadoPrecipitacion = false;
	
	public static void main(String[] args) {
		SpringApplication.run(G3labo01Application.class, args); 
	}
	
	@GetMapping (value = {"/", "/inicio"}) 
    public String inicioConsulta(Model model) { 
		
		model.addAttribute("search", new Search());
		
		List<Consulta> consultas = new ArrayList<>();
		
		/**Listas para websockets**/
		List<Precipitacion> precipitaciones = new ArrayList<>();
		List<Temperatura> temperaturas = new ArrayList<>();
		
		/**Leer CSV Consulta**/
		try {
			resultadoConsulta = consRep.existenDatos();
			
			if(!resultadoConsulta){
				consultas = new ArrayList<Consulta>();
	      
		        CsvReader consultas_import = new CsvReader("data.csv");
		        consultas_import.readHeaders();
		        while (consultas_import.readRecord())
		        {
		            String seriesName = consultas_import.get(0);
		            String seriesCode = consultas_import.get(1);
		            String countryName = consultas_import.get(2);
		            String countryCode = consultas_import.get(3);
		            
		            List<String> listEstadisticaTemp = new ArrayList<String>();
		            
		            listEstadisticaTemp.add(  consultas_import.get(4));
		            listEstadisticaTemp.add(  consultas_import.get(5));
		            listEstadisticaTemp.add( consultas_import.get(6));
		            listEstadisticaTemp.add( consultas_import.get(7));
		            listEstadisticaTemp.add(consultas_import.get(8));
		            listEstadisticaTemp.add( consultas_import.get(9));
		            listEstadisticaTemp.add( consultas_import.get(10));
		            listEstadisticaTemp.add(  consultas_import.get(11));
		            listEstadisticaTemp.add(   consultas_import.get(12));
			        
		            Consulta consultaTemp = new Consulta(seriesName, seriesCode, countryName, countryCode, listEstadisticaTemp);
		            
		            consultas.add(consultaTemp);    
		        }
		      
		        consultas_import.close();
		       /**Agregar Consultas al DataStore**/
		        for(Consulta us : consultas){
		        	consRep.agregarEntidad(us);
		        }
			}
			
			/**Leer CSV's Precipitacion y Temperatura**/
			resultadoPrecipitacion = typRep.existenDatosPrecipitacion();
			
			if(!resultadoPrecipitacion){
				precipitaciones = new ArrayList<Precipitacion>();
		        CsvReader precipitaciones_import = new CsvReader("precip.csv");
		    
		        //Convertir Precipitaciones
		        precipitaciones_import.readHeaders();
		        while (precipitaciones_import.readRecord())
		        {
		            String wbhucPrecip = precipitaciones_import.get(0);
		            String yearPrecip = precipitaciones_import.get(1);
		            String jan_precip = precipitaciones_import.get(2);
		        	String feb_precip = precipitaciones_import.get(3);
		        	String mar_precip = precipitaciones_import.get(4);
		        	String apr_precip = precipitaciones_import.get(5);
		        	String may_precip = precipitaciones_import.get(6);
		        	String jun_precip = precipitaciones_import.get(7);
		        	String july_precip = precipitaciones_import.get(8);
		        	String aug_precip = precipitaciones_import.get(9);
		        	String sept_precip = precipitaciones_import.get(10);
		        	String oct_precip = precipitaciones_import.get(11);
		        	String nov_precip = precipitaciones_import.get(12);
		        	String dec_precip = precipitaciones_import.get(13);
		        	String annual_precip = precipitaciones_import.get(14);
		           
			        Precipitacion precipitacionTemp = new Precipitacion(wbhucPrecip, yearPrecip, jan_precip, feb_precip, mar_precip,
		            		apr_precip, may_precip, jun_precip, july_precip, aug_precip, sept_precip, oct_precip, nov_precip, dec_precip,
		            		annual_precip);
		            
		            precipitaciones.add(precipitacionTemp);    
		        }
		      
		        precipitaciones_import.close();
		       /**Agregar Precipitaciones al DataStore**/
		        for(Precipitacion pr : precipitaciones){
		        	typRep.agregarEntidadPrecipitacion(pr);
		        }
			}
			
			resultadoTemperatura = typRep.existenDatosTemperatura();
			
			if(!resultadoTemperatura){
		        temperaturas = new ArrayList<Temperatura>();
		        CsvReader temperaturas_import = new CsvReader("temp.csv");
		        
		      //Convertir Temperaturas
		        temperaturas_import.readHeaders();
		        while (temperaturas_import.readRecord())
		        {
		            String wbhucTemp = temperaturas_import.get(0);
		            String yearTemp = temperaturas_import.get(1);
		            String jan_temp = temperaturas_import.get(2);
		        	String feb_temp = temperaturas_import.get(3);
		        	String mar_temp = temperaturas_import.get(4);
		        	String apr_temp = temperaturas_import.get(5);
		        	String may_temp = temperaturas_import.get(6);
		        	String jun_temp = temperaturas_import.get(7);
		        	String july_temp = temperaturas_import.get(8);
		        	String aug_temp = temperaturas_import.get(9);
		        	String sept_temp = temperaturas_import.get(10);
		        	String oct_temp = temperaturas_import.get(11);
		        	String nov_temp = temperaturas_import.get(12);
		        	String dec_temp = temperaturas_import.get(13);
		        	String annual_temp = temperaturas_import.get(14);
			        
		            Temperatura temperaturasTemp = new Temperatura(wbhucTemp, yearTemp, jan_temp, feb_temp, mar_temp,
		            		apr_temp, may_temp, jun_temp, july_temp, aug_temp, sept_temp, oct_temp, nov_temp, dec_temp,
		            		annual_temp);
		            
		            temperaturas.add(temperaturasTemp);    
		        }
		      
		        temperaturas_import.close();
		       /**Agregar Temperaturas al DataStore**/
		        for(Temperatura tm : temperaturas){
		        	typRep.agregarEntidadTemperatura(tm);
		        }
			}
			
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
            return "inicio" ; 
    } 
	
	
	@RequestMapping(value = "/chartSearch", method=RequestMethod.POST)
	 public String chartSearch(Model model, @ModelAttribute("search") Search search ) {
		consulta = search.getIndicador();
		return "redirect:/chart";
	}
	
	@RequestMapping(value = "/chart", method=RequestMethod.GET)
    public String chart(Model model ) {
		model.addAttribute("search", new Search());
		List<Double> Argentina = null;
		List<Double> Bolivia = null;
		List<Double> Brazil = null;
		List<Double> Chile = null;
		List<Double> Colombia = null;
		List<Double> Ecuador = null;
		List<Double> Peru = null;
		List<Double>Paraguay = null;
		List<Double> Uruguay = null;
		List<Double> Venezuela = null;
		
		List<Entity> consultas;
		if(consulta.equals(""))
			consultas = consRep.realizarConsulta("SH.DYN.AIDS");
		else
			consultas = consRep.realizarConsulta(consulta);
		
        for(int i=0;i<consultas.size();i++) {
        	Entity e = consultas.get(i);
        	List<Double> valores = new ArrayList<>();
    		valores.add(Double.valueOf((String) e.getProperty("2005")));
    		valores.add(Double.valueOf((String) e.getProperty("2006")));
    		valores.add(Double.valueOf((String) e.getProperty("2007")));
    		valores.add(Double.valueOf((String) e.getProperty("2008")));
    		valores.add(Double.valueOf((String) e.getProperty("2009")));
    		valores.add(Double.valueOf((String) e.getProperty("2010")));
    		valores.add(Double.valueOf((String) e.getProperty("2011")));
    		valores.add(Double.valueOf((String) e.getProperty("2012")));
    		valores.add(Double.valueOf((String) e.getProperty("2013")));
        	
        	switch((String) consultas.get(i).getProperty("CountryCode")) {
        		
        		case "ARG":	Argentina = valores;break;
        		case "BOL":	Bolivia= valores;break;
        		case "BRA":	Brazil= valores;break;
        		case "CHL":	Chile = valores;break;
        		case "COL":	Colombia= valores;break;
        		case "ECU":	Ecuador = valores;break;
        		case "PER":	Peru= valores;break;
        		case "PRY":	Paraguay= valores;break;
        		case "URY":	Uruguay= valores;break;
        		case "VEN":	Venezuela= valores;break;
        	}
        }
        String indicador = (String) consultas.get(0).getProperty("SeriesName");
    	//String Indicador="Adults (ages 15+) living with HIV";
  
        model.addAttribute("Indicador",indicador);
   
        model.addAttribute("Argentina", Argentina);
        model.addAttribute("Bolivia", Bolivia);
        model.addAttribute("Brazil", Brazil);
        model.addAttribute("Chile", Chile);
        model.addAttribute("Colombia", Colombia);
        model.addAttribute("Ecuador", Ecuador);
        model.addAttribute("Peru", Peru);
        model.addAttribute("Paraguay", Paraguay);
        model.addAttribute("Uruguay", Uruguay);
        model.addAttribute("Venezuela", Venezuela);
        
        return "chart";
    }
	
	@RequestMapping(value = "/clima", method=RequestMethod.GET)
    public String clima(Model model) {
    	
    	
    	String Año = "1981";//año que se elige
    	String mes = "Mayo";//mes que se elige
    	String Indicador = "Año: "+Año+" Mes: "+mes;
    	Random r=new Random();
    	List<Double> DatosTemperatura = Arrays.asList(14.63,21.4,21.66,22.15,12.7,22.2,28.88,28.82,24.18,28.17
    			,24.78,25.85,13.6,30.01,29.65,30.6,10.5,30.45,28.06,28.3,20.53,24.5,21.92,16.42,29.39,24.71);
    	int aleatorioTemperatura=r.nextInt(26);
    	Double valortemp=DatosTemperatura.get(aleatorioTemperatura);
    	Color colorTemp=Color.GREEN;
    	    	
    	if(valortemp > 30) {
    		colorTemp = Color.RED;
    	}
    	else if(valortemp <= 30 && valortemp >= 15) {
    		colorTemp = Color.YELLOW;
    	}
    	else if(valortemp < 15) {
    		colorTemp = Color.BLUE;
    	}
    	    	
    	String colorTemperatura = String.format("#%02x%02x%02x", colorTemp.getRed(),colorTemp.getGreen(),colorTemp.getBlue());
    	LOG.info(aleatorioTemperatura+"  "+valortemp+"  "+colorTemperatura);
    	
        model.addAttribute("Titulo",Indicador);
        model.addAttribute("year",Año);
        model.addAttribute("mes",mes);
        model.addAttribute("valorTemperatura",valortemp);
        model.addAttribute("colorTemperatura",colorTemperatura);
        
        List<Double> DatosPrecipitacion = Arrays.asList(124.63,221.4,221.66,222.15,512.7,522.2,528.88,728.82,124.18,228.17
    			,324.78,425.85,513.6,630.01,729.65,530.6,610.5,230.45,28.06,28.3,20.53,24.5,321.92,116.42,529.39,224.71);
    	int aleatorioPrecipitacion=r.nextInt(26);
    	Double valorPrecipitacion=DatosPrecipitacion.get(aleatorioPrecipitacion);
    	Color colorPrecipitacion=Color.GREEN;
    	    	
    	if(valorPrecipitacion > 500) {
    		colorPrecipitacion = Color.BLACK;
    	}
    	else if(valorPrecipitacion <= 500 && valorPrecipitacion >= 200) {
    		colorPrecipitacion = Color.GRAY;
    	}
    	else if(valorPrecipitacion < 200) {
    		colorPrecipitacion = Color.YELLOW;
    	}
    	    	
    	String colorPreci = String.format("#%02x%02x%02x", colorPrecipitacion.getRed(),colorPrecipitacion.getGreen(),colorPrecipitacion.getBlue());
    	LOG.info(aleatorioTemperatura+"  "+valorPrecipitacion+"  "+colorPreci);
    	
    	 model.addAttribute("valorPrecipitacion",valorPrecipitacion);
         model.addAttribute("colorPrecipitacion",colorPreci);
        
        return "clima";
    }
	
	/*
	@MessageMapping("/busquedaClima")
    @SendTo("/tema/resultadoClima")
    public ResultadoClima resultadoClima(BusquedaClima busquedaClima) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ResultadoClima("1999", "Junio", "12", "12");
    }
    */
	
	@RequestMapping(value = "/busquedaClima", method=RequestMethod.POST)
	 public @ResponseBody ResultadoClima busquedaClima(@RequestBody BusquedaClima busquedaClima ) {
		ResultadoClima resultClima = typRep.obtenerTemperaturaYPrecipitacion(busquedaClima.getYear(), busquedaClima.getMes());
		return resultClima;
	}
    
	
	
	
	
	
}
