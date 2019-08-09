package com.example.cuarto;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class Renderiza1 implements Renderer{
	
	private Circulo circulo;
	private Circulo circulo2;
	private Circulo circulo3;
	private Circulo circulo4;
	private Circulo circulo5;
	private Circulo circulo6;
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		circulo = new Circulo(0.5f, 360, false);	
		circulo2 = new Circulo(1.0f, 360, false);
		circulo3 = new Circulo(1.5f, 360, false);
		circulo4 = new Circulo(2.0f, 360, false);
		circulo5 = new Circulo(2.5f, 360, false);
		circulo6 = new Circulo(3.0f, 360, false);
		/* Color de fondo */
		gl.glClearColor(0, 0, 0, 0);
		
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		/* Inicializa el buffer de color */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLineWidth(5);	// para mas grosor
		gl.glEnable(GL10.GL_LINE_SMOOTH);	// la calidad de la linea sera suavisada
		/*
		 * void glColor4f(	GLfloat red,GLfloat green,GLfloat blue,GLfloat black);
		 */
		// 1
		gl.glLoadIdentity();
		gl.glColor4f(0, 1, 1, 1);	//light blue
		circulo.dibuja(gl);
		// 2
		gl.glLoadIdentity();
		gl.glColor4f(0, 0, 1, 0);	// azul
		circulo2.dibuja(gl);
		// 3
		gl.glLoadIdentity();
		gl.glColor4f(1, 0, 0, 0);	// rojo
		circulo3.dibuja(gl);
		// 4
		gl.glLoadIdentity();
		gl.glColor4f(1, 1, 0, 0);	//amarillo
		circulo4.dibuja(gl);
		// 5
		gl.glLoadIdentity();
		gl.glColor4f(0, 1, 0, 0);	// verde
		circulo5.dibuja(gl);
		// 6
		gl.glLoadIdentity();
		gl.glColor4f(0, 1, 1, 1);	//light blue
		circulo6.dibuja(gl);
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
