package co.unicauca.domicilios.access;

/**
 * @author Luis Romero, Jose Nicolas Santander
 */
public class Factory {
	
	private static Factory instance;
	
	private Factory() {
	}
	
	/**
	 * Clase Singleton
	 *
	 * @return
	 */
	public static Factory getInstance() {
		
		if (instance == null) {
			instance = new Factory();
		}
		return instance;
		
	}
	
}
