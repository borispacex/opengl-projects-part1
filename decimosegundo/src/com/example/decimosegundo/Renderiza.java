package com.example.decimosegundo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class Renderiza extends GLSurfaceView implements Renderer {
	/* Texturas */
	Textura textura;
	/* Contexto */
	private Context contexto;
	/* Para la traslación de la textura */
	private float despTexturaX;
	private float despTexturaY;
	/* Variables para obtener el tiempo */
	private long inicio, fin, duracion;
	private float tiempo_real;
	private float tiempoMovimiento;
	private final float PERIODO_MOVIMIENTO = 0.0167f; // 1/60 = 0.0167f (60
														// cuadros por seg.)

	public Renderiza(Context contexto) {
		super(contexto);
		this.contexto = contexto;
		/* Inicia el renderizado */
		this.setRenderer(this);
		/* El renderizado se llama continuamente para renderizar la escena. */
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

	// @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		/* Lee las texturas */
		textura = new Textura(gl, contexto, "woody.png");
		textura.setVertices(-1, -1, 2, 2);
		textura.setCoord_Textura(0, 0, 1f / 8, 1f / 5); // 1 cuadro de u (o s) y
														// v (o t)
		/* Inicializa las variables */
		despTexturaX = 0;
		despTexturaY = 0;
		inicio = System.currentTimeMillis();
		tiempoMovimiento = PERIODO_MOVIMIENTO; // Este valor de restará en
												// c/renderización
		/* Habilita la textura */
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		/* Color de fondo */
		gl.glClearColor(0, 0, 0, 0);
	}

	// @Override
	public void onDrawFrame(GL10 gl) {
		/* Inicializa el buffer de color */
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		/* Matriz de Textura */
		gl.glMatrixMode(GL10.GL_TEXTURE);
		/* Inicializa la Matriz de Textura */
		gl.glLoadIdentity();
		/* Traslada la textura */
		/* Avanza al siguiente cuadro */
		gl.glTranslatef(despTexturaX, despTexturaY, 0);
		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
		textura.muestra(gl);
		/* Se obtiene el tiempo real */
		fin = System.currentTimeMillis();
		duracion = fin - inicio;
		tiempo_real = duracion / 1000f;
		inicio = fin;
		/* Incremento y limite */
		tiempoMovimiento = tiempoMovimiento - tiempo_real;
		if (tiempoMovimiento < 0.001) {
			tiempoMovimiento = PERIODO_MOVIMIENTO;
			/* Incrementa los desplazamientos en la textura */
			despTexturaX = despTexturaX + 1f / 8; // en u (o s)
			if (despTexturaX > 7f / 8) {
				despTexturaX = 0;
				despTexturaY = despTexturaY + 1f / 5; // en v (o t)
				if (despTexturaY > 4f / 5)
					despTexturaY = 0;
			}
		}
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
			GLU.gluOrtho2D(gl, -4, 4, -4 * h / (float) w, 4 * h / (float) w);
		else
			GLU.gluOrtho2D(gl, -4 * w / (float) h, 4 * w / (float) h, -4, 4);
		/* Matriz del Modelo-Vista */
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		/* Inicializa la Matriz del Modelo-Vista */
		gl.glLoadIdentity();
	}
}
