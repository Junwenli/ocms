package com.linkcm.core.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;

/**
 * GZIP压缩工具包
 * 
 * @author antking
 * 
 */
public final class GzipUtils {
	
	private static Logger logger = LoggerUtils.getLogger(GzipUtils.class);
	
	private GzipUtils() {
		
	}
	
	/**
	 * GZIP压缩 压缩前的文件，压缩后的文件
	 * 
	 * @param file
	 * @param zipFile
	 */
	public static void zipFile(File file, File zipFile) {
		InputStream is = null;
		OutputStream os = null;
		GZIPOutputStream zos = null;
		try {
			os = new FileOutputStream(zipFile);
			zos = new GZIPOutputStream(os);
			is = new FileInputStream(file);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = is.read(buf)) != -1) {
				zos.write(buf, 0, bytesRead);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStream(zos);
			closeStream(is);
			closeStream(os);
		}
	}

	/**
	 * 解压
	 * 
	 * @param areaId
	 * @param basicCellNet
	 */
	public static void unZipFile(InputStream is, File zipFile) {
		OutputStream os = null;
		GZIPInputStream gzipis = null;
		try {
			os = new FileOutputStream(zipFile);
			gzipis = new GZIPInputStream(is);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = gzipis.read(buf)) != -1) {
				os.write(buf, 0, bytesRead);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStream(os);
			closeStream(is);
			closeStream(gzipis);
		}
	}

	private static void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
