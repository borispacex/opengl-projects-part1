package com.example.segundo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class Renderiza implements Renderer{
	
	// private Linea linea;
	// private Triangulo triangulo;
	// private EstrellaSimple estrellaS;
	// private EstrellaCompleja estrellaC;
	// private Circulo circulo;
	//private TrianguloColor trianguloC;
	private CuadradosColores cuadradosC;
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		//linea = new Linea();
		//triangulo = new Triangulo();
		// estrellaS = new EstrellaSimple();
		//estrellaC = new EstrellaCompleja();
		//circulo = new Circulo(0.5f, 360, true);
		//trianguloC = new TrianguloColor();
		cuadradosC = new CuadradosColores();
		gl.glClearColor(0, 1, 0.5f , 1);
		
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);//con esto se pinta el color de fondo
		gl.glLineWidth(2);
		//linea.dibuja(gl);
		//triangulo.dibuja(gl);
		//estrellaS.dibuja(gl);
		//estrellaC.dibuja(gl);
		// circulo.dibuja(gl);
		//trianguloC.dibuja(gl);
		cuadradosC.dibuja(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		gl.glViewport(0, 0, w, h);
		
		// para darles COLOR trianguloColor
		gl.glMatrixMode(GL10.GL_PROJECTION);		// matriz de proyeccion
		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();
		/* Proyección paralela */
		GLU.gluOrtho2D(gl, -4, 4, -6, 6);			// (gl ,-x, x, -y, y) (izquierda, derecha, abajo, arriba)
		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
		
	}	
}
