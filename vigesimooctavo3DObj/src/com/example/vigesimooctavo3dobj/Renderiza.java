package com.example.vigesimooctavo3dobj;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;

/**
 * Clase Renderiza (OpenGL 1.x)
 * 
 * @author Jhonny Felipez
 * @version 1.0 02/04/2016
 *
 */
public class Renderiza extends GLSurfaceView implements Renderer {
	
	/* Objeto */
	private Terreno terreno;
	
	/* Para la rotación */
	private float rotX;
	private float rotY;
	private float antX;
	private float antY;
	
	/* Tamaño de la ventana en pixeles */
	private int alto;
	private int ancho;
	
	/* Contexto */
	private Context contexto;
	
	public Renderiza(Context contexto) {
		super(contexto);
		
		this.contexto = contexto;
		
		/* Inicia el renderizado */
		this.setRenderer(this);
		
		/* La ventana solicita recibir una entrada */
		this.requestFocus();
        
		/* Establece que la ventana detecte el modo táctil */
		this.setFocusableInTouchMode(true);
		
		/* Se renderizará al inicio o cuando se llame a requestRender() */
		this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}	
	
	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
	 
		terreno = new Terreno(contexto, "hm3_65.raw");
		//terreno = new Terreno(contexto, "heightmap1_60.raw");
		//terreno = new Terreno(contexto, "heightmap2_65.raw");
		//terreno = new Terreno(contexto, "heightmap3_65.raw");
		//terreno = new Terreno(contexto, "island_65.raw");
		//terreno = new Terreno(contexto, "heightmap4_128.raw");

		/* Definición del color de la luz - Valores por defecto */
		float luz_ambiente[]  = { 0, 0, 0, 1}; // I 
		float luz_difusa[]    = { 1, 1, 1, 1 }; 
		float luz_especular[] = { 1, 1, 1, 1 }; 
		float luz_posicion[]  = { 0, 0, 5, 0}; // L 	w == 0 (direccional) w == 1 (posicional)
		
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, luz_ambiente, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, luz_difusa, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, luz_especular, 0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, luz_posicion, 0);
		
		/* Definición del color del material - Valores por defecto */
		float mat_ambiente []  = {0.2f, 0.2f, 0.2f, 1}; // K
		float mat_difuso []    = {0.8f, 0.8f, 0.8f, 1};
		float mat_especular [] = {0f, 0f, 0f, 1};
		float mat_brillo       = 0f;
		
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat_ambiente, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat_difuso, 0);
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, mat_especular, 0);
		gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, mat_brillo);
        
		/* Habilita la interpolación del sombreado */
		gl.glShadeModel(GL10.GL_SMOOTH); 
		
		/* Habilita el ocultamiento de superficies */
		gl.glEnable(GL10.GL_DEPTH_TEST);
		
		/* Habilita la iluminación */
		gl.glEnable(GL10.GL_LIGHTING); 
		
		/* Habilita la luz 0 */
		gl.glEnable(GL10.GL_LIGHT0); 
		
		/* Habilita la normalización */
		gl.glEnable(GL10.GL_NORMALIZE); 
		
		gl.glClearColor(0, 1, 1, 1);
	}
	
	// @Override
	public void onDrawFrame(GL10 gl) {

		/* Inicializa el buffer de color y de profundidad */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		float escala = 5f / terreno.getAncho();
		
		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
		
		// Traslada para su visualización
		gl.glTranslatef(0, 0, -10);
		
		// Rota el terreno
		gl.glRotatef(rotX, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(rotY, 1.0f, 0.0f, 0.0f);
		
		// Escala
		gl.glScalef(escala, escala, escala);
		
		// Traslada el centro del terreno al origen
		gl.glTranslatef(-terreno.getAncho()/2, 0, -terreno.getAncho()/2);
		
		terreno.dibuja(gl);
		
		/* Se asegura que se ejecute las anteriores instrucciones */
		gl.glFlush();

	}
	
	// @Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		
		ancho = w;
		alto = h;
	 
		/* Ventana de despliegue */
		gl.glViewport(0, 0, w, h);
	 
		/* Matriz de Proyección */
		gl.glMatrixMode(GL10.GL_PROJECTION);
	 
		/* Inicializa la Matriz de Proyección */
		gl.glLoadIdentity();
	 
		/* Proyección paralela */
		GLU.gluPerspective(gl, 45, (float)w / h, 1f, 200f);
	 
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
				rotX = rotX + dx * 0.5625f;
				rotY = rotY + dy * 0.5625f;
				requestRender();
		}
		antX = x;
		antY = y;
		return true;
	}
}
