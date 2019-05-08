package controller;

import model.Fruit;
import model.GameObject;

public class ObjectFactory {
	
	public GameObject getObject(String object) {
		if(object == null){
	         return null;
	      }		
	      if(object.equalsIgnoreCase("STRAWBERRY")){
	         return new Fruit(1);
	         
	      }
	      else if(object.equalsIgnoreCase("BANANA")){
	         return new Fruit(2);
	         
	      }
	      else if(object.equalsIgnoreCase("APPLE")){
	         return new Fruit(3);
	      }
	      else if(object.equalsIgnoreCase("PEACH")) {
	    	return new Fruit(4);  
	      }
	      else if(object.equalsIgnoreCase("WATERMELON")) {
	    	return new Fruit(5);
	      }
	      else if(object.equalsIgnoreCase("FATALBOMB")) {
	    	  
	      }
	      else if(object.equalsIgnoreCase("LIVEBOMB")) {
	    	  
	      }
	      
	      return null;
	   }
}
