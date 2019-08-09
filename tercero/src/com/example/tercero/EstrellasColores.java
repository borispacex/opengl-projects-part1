package com.example.tercero;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class EstrellasColores {
	
	private float vertices[]={
	//segunda estrella
	0,-3,		
	0,-1,
	0.5f,-2.5f,

	
	2,-2.5f,

	1,-3.5f,
	
	1.5f,-5,
	0,-4,
	
	-1.5f,-5,
	
	-1,-3.5f,
	
	-2,-2.5f,

	-0.5f,-2.5f,
	0,-1

			/*-2,3,
			2,3,
			2,-3,
			-2,-3
	*/
					
	};
	
	private float vertices2[]={
	
	//segunda estrella
	0.5f,-2.5f,	// 0
	0,-3,		// 1
	2,-2.5f,	// 2
			
	1,-3.5f,	// 3
	0,-3,		// 4
	1.5f,-5,	// 5
	
	0,-4,		// 6
	0,-3,		// 7
	-1.5f,-5,	// 8

	-1,-3.5f,	// 9
	0,-3,		// 10
	-2,-2.5f,	// 11
	
	-0.5f,-2.5f,
	0,-3,
	0,-1
	
	};
	private float vertices3[]={
			//primera estrella
	
			0,3,
			0,5,
			0.5f,3.5f,
			2,3.5f,
			1,2.5f,
			1.5f,1,
			0,2,
			-1.5f,1,
			-1,2.5f,
			-2,3.5f,
			-0.5f,3.5f,
			0,5
			
	};
	/*private short indices[]={
			0,1,3,
			1,2,3
			
	};*/
	
	FloatBuffer bufVertices;
	FloatBuffer bufVertices2;
	FloatBuffer bufVertices3;
	ShortBuffer bufindices;
	public EstrellasColores() {
		ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVertices = bufByte.asFloatBuffer();
		bufVertices.put(vertices);
		bufVertices.rewind();
		
		/*bufByte = ByteBuffer.allocateDirect(indices.length * 2);
		bufByte.order(ByteOrder.nativeOrder());
		bufindices = bufByte.asShortBuffer();
		bufindices.put(indices);
		bufindices.rewind();*/
		
		ByteBuffer bufByte2 = ByteBuffer.allocateDirect(vertices2.length * 4);
		bufByte2.order(ByteOrder.nativeOrder());
		bufVertices2 = bufByte2.asFloatBuffer();
		bufVertices2.put(vertices2);
		bufVertices2.rewind();
		
		ByteBuffer bufByte3 = ByteBuffer.allocateDirect(vertices3.length * 4);
		bufByte3.order(ByteOrder.nativeOrder());
		bufVertices3 = bufByte3.asFloatBuffer();
		bufVertices3.put(vertices3);
		bufVertices3.rewind();
	}
	public void dibuja(GL10 gl){
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);
		
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 12);
		
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices2);
		gl.glColor4f(1, 1, 0, 0); //cambiar color
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 15);
		
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices3);
		gl.glColor4f(1, 0, 0, 0); //cambiar color
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0,12);
	
		//gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT,bufindices);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
	}
	
}
