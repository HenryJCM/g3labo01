package pe.edu.sistemas.g3labo01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation. *;

import com.csvreader.CsvReader;
import com.google.appengine.api.datastore.Entity;

import pe.edu.sistemas.g3labo01.dominio.Consulta;
import pe.edu.sistemas.g3labo01.dominio.Search;
import pe.edu.sistemas.g3labo01.repository.ConsultaRepository;

@Configuration
@SpringBootApplication 
@Controller
public class G3labo01Application {
	private String consulta = "";
	ConsultaRepository consRep = new ConsultaRepository();
	boolean resultado = false;
	public static void main(String[] args) {
		SpringApplication.run(G3labo01Application.class, args); 
	}
	
	@GetMapping (value = {"/", "/inicio"}) 
    public String inicioConsulta(Model model) { 
		model.addAttribute("search", new Search());
		
		List<Consulta> consultas = new ArrayList<>();
		/**Leer CSV**/
		try {
			resultado = consRep.existenDatos();
			
			if(!resultado){
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
    
	
	
	
	
	
}
