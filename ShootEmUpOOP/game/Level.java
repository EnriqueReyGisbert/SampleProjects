package tp.p1.game;

public enum Level {
	EASY(0.5, 0.1, 4, 2, 3), HARD(0.2, 0.3, 8, 2, 2), INSANE(0.1, 0.5, 8, 4, 1);
	
	private double frecuenciaOvni;
	private double frecuenciaDestroyer;
	private int numeroComunes;
	private int numeroDestroyer;
	private int velocidad;
	private double frecuenciaExplosion;
	
	private Level(double OF, double DF, int RN, int DN, int S) {		// Constructor Level.
		this.frecuenciaOvni = OF;
		this.frecuenciaDestroyer = DF;
		this.numeroComunes = RN;
		this.numeroDestroyer = DN;
		this.velocidad = S;
		this.frecuenciaExplosion = 0.05;
	}
	
	public double getOF() {								// Devuelve frecuenciaOvni;
		return this.frecuenciaOvni;
	}
	
	public double getDF() {								// Devuelve frecuenciaDestroyer.
		return this.frecuenciaDestroyer;
	}
	
	public int getRN() {								// Devuelve numeroComunes.
		return this.numeroComunes;
	}
	
	public int getDN() {								// Devuelve numeroDestroyer.
		return this.numeroDestroyer;
	}
	
	public int getSp() {									// Devuelve velocidad.
		return this.velocidad;
	}
	
	public double getEF() {									// Devuelve frecuenciaExplosion.
		return this.frecuenciaExplosion;
	}
	
	public static Level parse(String cadenaEntrada) {
		for (Level level : Level.values()) 
			if (level.name().equalsIgnoreCase(cadenaEntrada)) 
				return level; 
		return EASY;
	}
}
