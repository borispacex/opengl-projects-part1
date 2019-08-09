package com.example.vigesimoquinto3d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;

/**
 * Clase Renderiza (OpenGL 1.x)
 * 
 * @author Jhonny Felipez
 * @version 1.0 02/04/2014
 *
 */
public class Renderiza extends GLSurfaceView implements Renderer {
	
	/* Objeto */
	private Cubo cubo;
	private Rectangulo plano;
	
	/* Para la rotación */
	private float rotX;
	private float rotY;
	private float antX;
	private float antY;

	public Renderiza(Context contexto) {
		super(contexto);
		
		/* Se inicia el renderizado */
		this.setRenderer(this);
		
		/* La ventana solicita recibir una entrada */
		this.requestFocus();
        
		/* Se establece que la ventana detecte el modo táctil */
		this.setFocusableInTouchMode(true);
		
		/* Se renderizará al inicio o cuando se llame a requestRender() */
		this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
	
	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
	 
		cubo = new Cubo();
		plano = new Rectangulo();
        
		/* Definición del color de la luz - Valores por defecto */
		float luz_ambiente[]  = { 0.2f, 0.2f, 0.2f, 1}; // I 
		float luz_difusa[]    = { 1, 1, 1, 1 }; 
		float luz_especular[] = { 1, 1, 1, 1 }; 
		float luz_posicion[]  = { 2, 2, 2, 0}; // L 	w == 0 (direccional) w == 1 (posicional)
		
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, luz_ambiente, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, luz_difusa, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, luz_especular, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, luz_posicion, 0);
        
		/* Se habilita la interpolación del sombreado */
		gl.glShadeModel(GL10.GL_SMOOTH); 
		
		/* Se habilita el ocultamiento de superficies */
		gl.glEnable(GL10.GL_DEPTH_TEST);
		
		/* Se habilita la iluminación */
		gl.glEnable(GL10.GL_LIGHTING); 
		
		/* Se habilita la luz 0 */
		gl.glEnable(GL10.GL_LIGHT0); 
		
		/* Se habilita la normalización */
		gl.glEnable(GL10.GL_NORMALIZE); 
		
		/* Color de fondo */
		gl.glClearColor(0, 0, 0, 1);
		
	}
	
	// @Override
	public void onDrawFrame(GL10 gl) {

		/* Inicializa el buffer de color y de profundidad */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		/* Se inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
		
		/* Rota el cubo */
		gl.glRotatef(rotX, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(rotY, 1.0f, 0.0f, 0.0f);
		
		/* Definición del color del material */
		float mat_ambiente []  = {0.0f, 0.0f , 0.0f, 1}; // K
		float mat_difuso []    = {0.1f, 0.95f, 0.1f, 1};
		float mat_especular [] = {0   , 0.95f, 0, 1};
		float mat_brillo       = 255f;
		
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambiente, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_difuso, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, mat_especular, 0);
		gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, mat_brillo);

		// Plano
		plano.dibuja(gl);

		/* Definición del color del material */
		float mat_ambiente1 []  = {0f   , 0f, 0f, 1}; // K
		float mat_difuso1 []    = {0.95f, 0f, 0f, 1};
		float mat_especular1 [] = {0.95f, 0f, 0f, 1};
		float mat_brillo1       = 255f;
		
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambiente1, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_difuso1, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, mat_especular1, 0);
		gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, mat_brillo1);

		// Cubo Rojo
		gl.glPushMatrix();
		gl.glTranslatef(-1.7f, 0, 0);
		cubo.dibuja(gl);
		gl.glPopMatrix();
		
		/* Definición del color del material - Valores por defecto */
		float mat_ambiente2 []  = {0f, 0f,    0f, 1}; // K
		float mat_difuso2 []    = {0f, 0f, 0.95f, 1};
		float mat_especular2 [] = {0f, 0f, 0.95f, 1};
		float mat_brillo2       = 255f;
		
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambiente2, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_difuso2, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, mat_especular2, 0);
		gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, mat_brillo2);

		// Cubo Azul
		gl.glPushMatrix();
		gl.glTranslatef(1.7f, 0, 0);
		cubo.dibuja(gl);
		gl.glPopMatrix();
		
		/* Se asegura que se ejecute las anteriores instrucciones */
		gl.glFlush();
		
	}
	
	// @Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
	 
		/* Ventana de despliegue */
		gl.glViewport(0, 0, w, h);
	 
		/* Matriz de Proyección */
		gl.glMatrixMode(GL10.GL_PROJECTION);
	 
		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();
	 
		/* Proyección paralela */
		if (w <= h)
			gl.glOrthof(-4, 4, -4 * (float) h / (float) w, 4 * (float) h
					/ (float) w, -10, 10);
		else
			gl.glOrthof(-4 * (float) w / (float) h, 4 * (float) w / (float) h,
					-4, 4, -10, 10);
	 
		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL10.GL_MODELVIEW);
	 
		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
	}
	
	/**
	 * Maneja los eventos del movimiento en la pantalla táctil. 
	 */
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		float x = e.getX();
		float y = e.getY();
		switch (e.getAction()) {
			case MotionEvent.ACTION_MOVE:
				float dx = x - antX;
				float dy = y - antY;
				rotX = rotX + dx * 0.5625f; // 180 / 320 = 0.5625
				rotY = rotY + dy * 0.5625f;
				requestRender();
		}
		antX = x;
		antY = y;
		return true;
	}
}
