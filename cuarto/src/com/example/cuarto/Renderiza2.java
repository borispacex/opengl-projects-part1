package com.example.cuarto;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class Renderiza2 implements Renderer{
	
	private Circulo circulo;
	private Circulo circulo2;
	private Circulo circulo3;
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		circulo = new Circulo(1.0f, 360, true);
		circulo2 = new Circulo(1.0f, 360, true);
		circulo3 = new Circulo(1.0f, 360, true);
		/* Color de fondo */
		gl.glClearColor(0, 0, 0, 0);
		
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		/* Inicializa el buffer de color */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLineWidth(3);	// para mas grosor
		gl.glEnable(GL10.GL_LINE_SMOOTH);	// la calidad de la linea sera suavisada
		// 1
		gl.glLoadIdentity();
		gl.glTranslatef(0, 1.75f, 0);
		gl.glColor4f(0, 0, 1, 0);	// azul
		circulo.dibuja(gl);
		// 2
		gl.glLoadIdentity();
		gl.glTranslatef(1, 0, 0);
		gl.glColor4f(0, 1, 0, 0);	// verde
		circulo2.dibuja(gl);
		// 3
		gl.glLoadIdentity();
		gl.glTranslatef(-1, 0, 0);
		gl.glColor4f(1, 0, 0, 0);	// rojo
		circulo2.dibuja(gl);
		
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		/* Ventana de despliegue */
		gl.glViewport(0, 0, w, h);
		/* Matriz de Proyección */
		gl.glMatrixMode(GL10.GL_PROJECTION);
		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();
		/* Proyección paralela */
		GLU.gluOrtho2D(gl, -4, 4, -6, 6);
		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
		
	}	
}
