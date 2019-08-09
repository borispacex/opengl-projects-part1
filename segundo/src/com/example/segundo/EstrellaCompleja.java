package com.example.segundo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class EstrellaCompleja {

	private float vertices[] = {
			0,      1,      // 0
	        0,      -0.34f,   // 5
	        -0.65f,   -0.87f,  // 4
	        0.2f,    0.2f,    // 9
	        1,      0.28f,    // 8
	        -0.3f,  -0.08f,  // 3
	        -1,     0.28f,    // 2
	        0.3f,   -0.08f,  // 7
	        0.65f,   -0.87f,  // 6
	        -0.2f,   0.2f,    // 1

	        -1,     0.28f,    // 2
	        -0.3f,  -0.08f,  // 3
	        -0.65f,   -0.87f,  // 4
	        0,      -0.34f,   // 5
	        0.65f,   -0.87f,  // 6
	        0.3f,   -0.08f,  // 7
	        1,      0.28f,    // 8
	        0.2f,    0.2f,    // 9
	        0,      1,      // 0
	        -0.2f,   0.2f,    // 1
	        
	};
	FloatBuffer bufVertices;
	public EstrellaCompleja(){
		//en aca se multiplica por 4 porque es float
		ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVertices = bufByte.asFloatBuffer();
		bufVertices.put(vertices);
		bufVertices.rewind();
	}
	
	public void dibuja(GL10 gl){
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glColor4f(1, 0, 0, 1);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 20);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}
