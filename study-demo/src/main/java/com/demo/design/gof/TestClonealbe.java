package com.demo.design.gof;

/**
 * 原型模式（ProtoType）
 * 通过一个原型对象来创建一个新对象（克隆）。Java中要给出Clonable接口的实现，具体类要实现这个接口，并给出clone
 * ()方法的实现细节，这就是简单原型模式的应用。  浅拷贝：只拷贝简单属性的值和对象属性的地址 
 * 深拷贝：拷贝本对象引用的对象，有可能会出现循环引用的情况。可以用串行化解决深拷贝。写到流里再读出来，这时会是一个对象的深拷贝结果。
 * 
 * @author Administrator
 * 
 */
import java.io.*;

public class TestClonealbe {

	public static void main(String[] args) throws Exception {

		Father f = new Father();

		User u1 = new User("123456", f);

		User u2 = (User) u1.clone();

		System.out.println(u1 == u2);

		System.out.println(u1.f == u2.f);

	}

}

class User implements Cloneable, Serializable {

	String password;

	Father f;

	public User(String password, Father f) {

		this.password = password;

		this.f = f;

	}

	public Object clone() throws CloneNotSupportedException {

		// return super.clone();

		ObjectOutputStream out = null;

		ObjectInputStream in = null;

		try {

			ByteArrayOutputStream bo = new ByteArrayOutputStream();

			out = new ObjectOutputStream(bo);

			out.writeObject(this);

			out.flush();

			byte[] bs = bo.toByteArray();

			ByteArrayInputStream bi = new ByteArrayInputStream(bs);

			in = new ObjectInputStream(bi);

			Object o = in.readObject();

			return o;

		} catch (IOException e) {

			e.printStackTrace();

			return null;

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

			return null;

		}

		finally {

			try {

				out.close();

				in.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

}

class Father implements Serializable {
}
