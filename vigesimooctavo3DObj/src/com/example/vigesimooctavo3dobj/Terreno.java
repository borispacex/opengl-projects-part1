package com.example.vigesimooctavo3dobj;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.util.Log;

/**
 * Clase Terreno (OpenGL 1.x)
 * 
 * @author Jhonny Felipez
 * @version 1.0 02/04/2016
 * 
 */
public class Terreno {
	
	/* Ancho y alto del archivo RAW */
	private int ancho;
	private int alto;
	
	private FloatBuffer bufVertices;
	private FloatBuffer bufNormales;
	private ByteBuffer bufByte;

	public Terreno(Context contexto, String nombreDeArchivo) {
		
		/* Lee el archivo .RAW */

		ArrayList<Integer> datoByte = new ArrayList<Integer>();
		
		int t = 0;
		try {
			byte[] buffer = new byte[1000];
			
			// Abre el archivo
			InputStream in = contexto.getAssets().open(nombreDeArchivo);

			// Lee los datos
			int numeroDeBytes = 0;
			while ((numeroDeBytes = in.read(buffer)) != -1) {
				for (int i = 0; i < numeroDeBytes; i++) {
					datoByte.add((int) (buffer[i] & 0xFF));
					t++;
				}
			}
			
			// Cierra el archivo
			in.close();

			in = null;

		} catch (IOException e) {
			Log.d("El archivo RAW", "No se puede cargar " + nombreDeArchivo);
			throw new RuntimeException("No se puede cargar " + nombreDeArchivo);
		}
			
		ancho = (int) Math.sqrt(t);
		alto = (int) Math.sqrt(t);
		
		/* Lee las altitudes */

		float altitud[][] = new float[alto][ancho];
		Vector3 normales2[][] = new Vector3[alto][ancho];
		Vector3 normales1[][] = new Vector3[alto][ancho];

		for (int z = 0; z < alto; z++) {
			for (int x = 0; x < ancho; x++) {
				int color = datoByte.get(z * alto + x);
				float a = 20 * ((color / 255.0f) - 0.5f);
				altitud[z][x] = a;
			}
		}
		
		/* Obtiene la normal de cada vértice */
			
		Vector3 suma; 					// vector suma
		Vector3 norte = new Vector3(); 	// vector norte
		Vector3 sur = new Vector3(); 	// vector sur
		Vector3 este = new Vector3(); 	// vector este
		Vector3 oeste = new Vector3(); 	// vector oeste
		
		/**
		 *   Dirección de los vectores
		 *
		 *          ^ N    
		 *          |      
		 *     <----+---->
		 *     O    |    E
		 *          v S
		 *             
		 */
		
		for (int z = 0; z < alto; z++) {
			for (int x = 0; x < ancho; x++) {
				suma = new Vector3();
				// Obtiene el vector norte
				if (0 < z) {
					norte = new Vector3(0.0f,
							altitud[z - 1][x] - altitud[z][x], -1.0f);
				}
				// Obtiene el vector sur
				if (z < alto - 1) {
					sur = new Vector3(0.0f, altitud[z + 1][x] - altitud[z][x],
							1.0f);
				}
				// Obtiene el vector este
				if (x < ancho - 1) {
					este = new Vector3(1.0f, altitud[z][x + 1] - altitud[z][x],
							0.0f);
				}
				// Obtiene el vector oeste
				if (0 < x) {
					oeste = new Vector3(-1.0f, altitud[z][x - 1]
							- altitud[z][x], 0.0f);
				}
				// Suma la normal entre el vector norte y oeste
				if (0 < x && 0 < z) {
					suma = suma
							.mas(norte.producto_vectorial(oeste).normaliza());
				}
				// Suma la normal entre el vector oeste y sur
				if (0 < x && z < alto - 1) {
					suma = suma.mas(oeste.producto_vectorial(sur).normaliza());
				}
				// Suma la normal entre el vector sur y este
				if (x < ancho - 1 && z < alto - 1) {
					suma = suma.mas(sur.producto_vectorial(este).normaliza());
				}
				// Suma la normal entre el vector este y norte
				if (x < ancho - 1 && 0 < z) {
					suma = suma.mas(este.producto_vectorial(norte).normaliza());
				}
				// Finalmente se normaliza
				normales2[z][x] = suma.normaliza();
			}
		}
		
		/*
		 * Suaviza las normales.
		 * Sumando las normales de los cuatro vértices adyacentes.
		 */
			
		/**
		 *     N = Normal de f
		 *     
		 *           c
		 *           | 
		 *           | 
		 *     a-----f-----b
		 *           |
		 *           |
		 *           d
		 *           
		 *     N = a + b + c + d
		 *     del vértice f  
		 */

		for(int z = 0; z < alto; z++) {
			for(int x = 0; x < ancho; x++) {
				
				suma = normales2[z][x];
				
				// Suma la normal del vertice a
				if (0 < x) {
					suma = suma.mas(normales2[z][x - 1]);
				}
				
				// Suma la normal del vertice b
				if (x < ancho - 1) {
					suma = suma.mas(normales2[z][x + 1]);
				}
				
				// Suma la normal del vertice c
				if (0 < z) {
					suma = suma.mas(normales2[z - 1][x]);
				}
				
				// Suma la normal del vertice d
				if (z < alto - 1) {
					suma = suma.mas(normales2[z + 1][x]);
				}
				
				// Finalmente se vuelve a normalizar
				// para encontrar la normal del vértice f
				if (suma.longitud() == 0)
					normales1[z][x] = new Vector3(0.0f, 1.0f, 0.0f);
				else {
					normales1[z][x] = suma.normaliza(); 
				}
			}
		}
		
		/* Lee los vértices y las normales para renderizar */

		/**
		 *    V0    V2
		 *     |    /|       
		 *     |  /  |  ...
		 *     |/    |
		 *    V1    V3
		 */
		
		bufByte = ByteBuffer.allocateDirect(t * 6 * 4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVertices = bufByte.asFloatBuffer();
		
		bufByte = ByteBuffer.allocateDirect(t * 6 * 4);
		bufByte.order(ByteOrder.nativeOrder());
		bufNormales = bufByte.asFloatBuffer();
		
		for (int z = 0; z < alto - 1; z++) {
			for (int x = 0; x < ancho; x++) {
				bufNormales.put(normales1[z][x].x);
				bufNormales.put(normales1[z][x].y);
				bufNormales.put(normales1[z][x].z);
				bufVertices.put((float) x);
				bufVertices.put(altitud[z][x]);
				bufVertices.put((float) z);

				bufNormales.put(normales1[z + 1][x].x);
				bufNormales.put(normales1[z + 1][x].y);
				bufNormales.put(normales1[z + 1][x].z);
				bufVertices.put((float) x);
				bufVertices.put(altitud[z + 1][x]);
				bufVertices.put((float) (z + 1));
			}
		}
		bufVertices.rewind();
		bufNormales.rewind();
	}
	
	/* Retorna el ancho del archivo */
	public int getAncho() {
		return ancho;
	}

	/* Retorna el alto del archivo */
	public int getAlto() {
		return alto;
	}
	
	public void dibuja(GL10 gl) {

		/* Se habilita el acceso al arreglo de vértices */
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		/* Se habilita el acceso al arreglo de las normales */
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		
		/* Se especifica los datos del arreglo de vértices */
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufVertices);
		
		/* Se especifica los datos del arreglo de las normales */
		gl.glNormalPointer(GL10.GL_FLOAT, 0, bufNormales);

		/* Renderiza por cada fila en 'x' */
		int x = 0;
		for (int z = 0; z < alto - 1; z++) {
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, x, 2 * ancho);
			x = x + 2 * ancho;
		}

		/* Se deshabilita el acceso al arreglo de vértices */
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

		/* Se deshabilita el acceso al arreglo de las normales */
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
	}
}
