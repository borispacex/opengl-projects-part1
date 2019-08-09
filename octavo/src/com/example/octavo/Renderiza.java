package com.example.octavo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.MotionEvent;

public class Renderiza extends GLSurfaceView implements Renderer {
	/* Objeto */
	private Triangulo triangulo;	//	Incremento del ángulo de la animación
	private Rectangulo rectangulo;
	private float angulo = 0;		//	Se almacena el desplazamiento del triángulo
	private float angulo1 = 360;	// cuadrado
	private float tx, ty; 			//¿ Se realiza la animación ?
	private boolean animacion = true;
	public Renderiza(Context contexto) {
		super(contexto);
		this.setRenderer(this);	//	inicia el renderizado
		this.requestFocus();	// La ventana solicita recibir una entrada
		this.setFocusableInTouchMode(true);	//	Establece que la ventana pueda detectar el modo táctil .
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);	// El renderizado se llama continuamente para renderizar la escena .
	}

	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		triangulo = new Triangulo();
		rectangulo = new Rectangulo();
		gl.glClearColor(0, 0, 0, 0);	// Color de fondo
	}

	// @Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	// Inicializa el buffer de color y de profundidad 
		gl.glMatrixMode(GL10.GL_MODELVIEW);	// Inicializa la Matriz del Modelo - Vista
		/* 
		// ---- OBJETO 1
		// gl.glLoadIdentity();
		gl.glPushMatrix();			// guardamos la matriz identidad
		gl.glTranslatef(0, 1, 0);	// t=1
		//gl.glRotatef(angulo, 0, 0, 1);
		triangulo.dibuja(gl);
		gl.glPopMatrix();
		
		// ---- OBJETO 2
		//gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glTranslatef(0, -1, 0);	// T=T*T(tx,ty)
		//gl.glRotatef(angulo1, 0, 0, 1);
		rectangulo.dibuja(gl);
		gl.glPopMatrix();
		
		angulo = angulo + 5;
		angulo1 = angulo1 - 5;
		*/		
		/* GIRO DE DOS OBJETOS ALREDEDOR DE EL CUADRADO*/
		// ---- OBJETO 2
		gl.glPushMatrix();
		gl.glTranslatef(0, -1, 0);	// T=T*T(tx,ty)
		gl.glRotatef(angulo, 0, 0, 1);
		rectangulo.dibuja(gl);
			// ---- OBJETO 1
			gl.glPushMatrix();			// guardamos la matriz identidad
			gl.glTranslatef(0, 1, 0);
			gl.glTranslatef(0, 1, 0);	
			triangulo.dibuja(gl);
			gl.glPopMatrix();
		gl.glPopMatrix();
				
		angulo = angulo + 5;
	}

	// @Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		gl.glViewport(0, 0, w, h);	// Ventana de despliegue
		gl.glMatrixMode(GL10.GL_PROJECTION);	// Matriz de Proyección
		gl.glLoadIdentity();	// Inicializa la Matriz de Proyección
		GLU.gluOrtho2D(gl, -4, 4, -6, 6);	// Proyección paralela
		gl.glMatrixMode(GL10.GL_MODELVIEW);	// Matriz del Modelo - Vista
		gl.glLoadIdentity();	// Inicializa la Matriz del Modelo - Vista
	}

	/*Maneja los eventos de la pantalla táctil. */
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		/* Se considera cuando el dedo toca la pantalla. */
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			animacion = !animacion;
		}
		return true;
	}
}