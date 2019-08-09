package com.example.decimo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class Renderiza extends GLSurfaceView implements Renderer {

	/* Objetos */
	private CirculoGrafico circulografico;
	private Circulo c1, c2, c3;
	private RectanguloGrafico rectangulografico;
	private Rectangulo r1, r2, r3;

	/* Desplazamientos e Incrementos */
	private float despX_c1 = -2, despX_c2 =  2;
	private float despX_r1 = -2, despX_r2 =  2;
	private float despX_c3 = -5, despX_r3 =  5;
	private float incX_c1 = 0.1f, incX_c2 = -0.1f;
	private float incX_r1 = 0.05f, incX_r2 = -0.05f;
	private float incX_c3 = 0.03f, incX_r3 = -0.03f;
	
	private SonidoSoundPool sonido1, sonido2, sonido3;
	
	public Renderiza(Context contexto) {
		super(contexto);
		
		sonido1 = new SonidoSoundPool(contexto, "0437.ogg");
		sonido2 = new SonidoSoundPool(contexto, "0438.ogg");
		sonido3 = new SonidoSoundPool(contexto, "0564.ogg");
		
		this.setRenderer(this);
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
	
	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		
		circulografico = new CirculoGrafico(0.5f, 360, true);
		c1 = new Circulo(0, 0, 0.5f);
		c2 = new Circulo(0, 0, 0.5f);
		c3 = new Circulo(0, 0, 0.5f);
		
		rectangulografico = new RectanguloGrafico();
		r1 = new Rectangulo(0, 0, 2, 1);
		r2 = new Rectangulo(0, 0, 2, 1);
		r3 = new Rectangulo(0, 0, 2, 1);
	
		gl.glClearColor(0, 0, 0, 0);
	}
	
	public void dibujaCirculo1(GL10 gl){
		gl.glPushMatrix();
		gl.glTranslatef(despX_c1, 3, 0);
		c1.x = despX_c1;
		c1.y = 3;
		gl.glColor4f(0, 1, 0, 1);
		circulografico.dibuja(gl);
		gl.glPopMatrix();
	}
	
	public void dibujaCirculo2(GL10 gl){
		gl.glPushMatrix();
		gl.glTranslatef(despX_c2, 3, 0);
		c2.x = despX_c2;
		c2.y = 3;
		gl.glColor4f(1, 1, 0, 1);
		circulografico.dibuja(gl);
		gl.glPopMatrix();
	}
	
	public void dibujaRectangulo1(GL10 gl){
		gl.glPushMatrix();
		gl.glTranslatef(despX_r1, 0, 0);
		r1.x = despX_r1;
		r1.y = 0;
		gl.glColor4f(1, 0, 0, 1);
		rectangulografico.dibuja(gl);
		gl.glPopMatrix();
	}
	
	public void dibujaRectangulo2(GL10 gl){
		gl.glPushMatrix();
		gl.glTranslatef(despX_r2, 0, 0);
		r2.x = despX_r2;
		r2.y = 0;
		gl.glColor4f(0, 0, 1, 1);
		rectangulografico.dibuja(gl);
		gl.glPopMatrix();
	}
	
	public void dibujaCirculo3(GL10 gl){
		gl.glPushMatrix();
		gl.glTranslatef(despX_c3, -2.5f, 0);
		c3.x = despX_c3;
		c3.y = -2.5f;
		gl.glColor4f(1, 0, 1, 1);
		circulografico.dibuja(gl);
		gl.glPopMatrix();
	}
	
	public void dibujaRectangulo3(GL10 gl){
		gl.glPushMatrix();
		gl.glTranslatef(despX_r3, -3f, 0);
		r3.x = despX_r3;
		r3.y = -3f;
		gl.glColor4f(0, 1, 1, 1);
		rectangulografico.dibuja(gl);
		gl.glPopMatrix();
	}
	
	// @Override
	public void onDrawFrame(GL10 gl) {

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		/* Colisión de dos circulos */
		dibujaCirculo1(gl);
		dibujaCirculo2(gl);
		
		if (seSobreponen(c1, c2)) {
			incX_c1 = -incX_c1;
			incX_c2 = -incX_c2;
			sonido1.play();
		}
		
		despX_c1 = despX_c1 + incX_c1;
		if (despX_c1 < -3.5f || despX_c1 > 3.5f) // izq
			incX_c1 = -incX_c1;
		
		despX_c2 = despX_c2 + incX_c2;
		if (despX_c2 < -3.5f || despX_c2 > 3.5f)  // der
			incX_c2 = -incX_c2;
		
		/* Colisión de dos rectángulos */
		dibujaRectangulo1(gl);
		dibujaRectangulo2(gl);
		
		if (seSobreponen(r1, r2)) {
			incX_r1 = -incX_r1;
			incX_r2 = -incX_r2;
			sonido2.play();
		}
		
		despX_r1 = despX_r1 + incX_r1;
		if (despX_r1 < -7 || despX_r1 > 7) // limite
			incX_r1 = -incX_r1;
		
		despX_r2 = despX_r2 + incX_r2;
		if (despX_r2 < -5 || despX_r2 > 5) // limite
			incX_r2 = -incX_r2;
		
		/* Colisión circulo y rectángulo */
		dibujaCirculo3(gl);
		dibujaRectangulo3(gl);
		
		if (seSobreponen(c3, r3)) {
			incX_c3 = -incX_c3;
			incX_r3 = -incX_r3;
			sonido3.play();
		}
		
		despX_c3 = despX_c3 + incX_c3;
		if (despX_c3 < -5 || despX_c3 > 5) // limite
			incX_c3 = -incX_c3;
		
		despX_r3 = despX_r3 + incX_r3;
		if (despX_r3 < -5 || despX_r3 > 5) // limite
			incX_r3 = -incX_r3;
		
		gl.glFlush();
	}
	
	// @Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		gl.glViewport(0, 0, w, h);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluOrtho2D(gl, -4, 4, -6, 6);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	public boolean seSobreponen(Rectangulo r1, Rectangulo r2) {
		return (r1.x < r2.x + r2.ancho && r1.x + r1.ancho >  r2.x  &&
				r1.y < r2.y + r2.alto && r1.y	+ r1.alto > r2.y);
	}
	
	public float distancia2(float x1, float y1, float x2, float y2) {
		return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
	}

	public boolean seSobreponen(Circulo c1, Circulo c2) {
		float distanciaCentros2 = distancia2(c1.x, c1.y, c2.x, c2.y);
		float sumaRadios2 =(c1.radio + c2.radio) * (c1.radio + c2.radio);
		return distanciaCentros2 <= sumaRadios2;
	}

	public boolean seSobreponen(Circulo c, Rectangulo r) {
		float x0 = c.x;
		float y0 = c.y;

		if (c.x < r.x) {
			x0 = r.x;
		} else if (c.x > r.x + r.ancho) {
			x0 = r.x + r.ancho;
		}

		if (c.y < r.y) {
			y0 = r.y;
		} else if (c.y > r.y + r.alto) {
			y0 = r.y + r.alto;
		}

		float d2 = distancia2(c.x, c.y, x0, y0); 
		return  d2 < c.radio * c.radio;
	}
}
