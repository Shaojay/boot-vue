package com.jay.boot.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Kryo序列化工具类
 */
public class KryoUtils {

	private KryoUtils() {
	}

	private static final Logger logger = LoggerFactory.getLogger(KryoUtils.class);

	public static final String CHARSET_NAME = "ISO-8859-1";

	/**
	 * 序列化
	 *
	 * @param <T>
	 * @param object
	 * @return
	 */
	public static <T> byte[] toBytes(T object) {
		if (object == null) {
			return null;
		}

		Kryo kryo = new Kryo();
		ByteArrayOutputStream out = null;
		Output output = null;
		try {
			out = new ByteArrayOutputStream();
			output = new Output(out);

			kryo.writeClassAndObject(output, object);
			output.close();
			return out.toByteArray();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e2) {
					logger.error(e2.getMessage(), e2);
				}
			}

			if (output != null) {
				output.close();
			}
		}

		return null;
	}

	/**
	 * 反序列化
	 *
	 * @param <T>
	 * @param bytes
	 * @return
	 */
	public static <T> T fromBytes(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		Kryo kryo = new Kryo();
		InputStream in = null;
		Input input = null;

		try {
			in = new ByteArrayInputStream(bytes);
			input = new Input(in);

			return (T) kryo.readClassAndObject(input);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e2) {
					logger.error(e2.getMessage(), e2);
				}
			}

			if (input != null) {
				input.close();
			}
		}

		return null;
	}

	/**
	 * 序列化对象为字符串
	 *
	 * @param <T>
	 * @param object
	 * @return
	 */
	public static <T> String toString(T object) {
		return toString(object, CHARSET_NAME);
	}

	/**
	 * 序列化对象为字符串
	 *
	 * @param <T>
	 * @param object
	 * @param charset
	 * @return
	 */
	public static <T> String toString(T object, String charset) {
		if (object == null) {
			return null;
		}

		Kryo kryo = new Kryo();
		ByteArrayOutputStream out = null;
		Output output = null;
		try {
			out = new ByteArrayOutputStream();
			output = new Output(out);

			kryo.writeClassAndObject(output, object);
			output.close();
			return out.toString(charset);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e2) {
					logger.error(e2.getMessage(), e2);
				}
			}

			if (output != null) {
				output.close();
			}
		}
		return null;
	}

	/**
	 * 反序列化字符串为对象
	 *
	 * @param <T>
	 * @param object
	 * @return
	 */
	public static <T> T fromString(String object) {
		return fromString(object, CHARSET_NAME);
	}

	/**
	 * 反序列化字符串为对象
	 *
	 * @param <T>
	 * @param object
	 * @param charset
	 * @return
	 */
	public static <T> T fromString(String object, String charset) {
		byte[] bystes = new byte[0];
		try {
			bystes = object.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return fromBytes(bystes);
	}

}
