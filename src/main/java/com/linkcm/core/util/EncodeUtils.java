package com.linkcm.core.util;

import java.security.MessageDigest;

public final class EncodeUtils {
	
	private EncodeUtils() {
		
	}
	
	/**
	 * md5加密
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public static String toMD5(String pwd ) {
		try {
			byte [] byties=pwd.getBytes();
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(byties);
			byte [] newByte=md.digest();
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<newByte.length;i++){
				if((newByte[i] & 0xff)<0x10){
					sb.append(0);
				}
				sb.append(Long.toString(newByte[i] & 0xff,16));
			}
			return sb.toString();
		} catch (Exception e) {
			return "";
		}
	}
	
	 /*
	  * 对应javascript的unescape()函数, 可对javascript的escape()进行解码
	  */
	 public static String unescape(String src) {
		 StringBuffer tmp = new StringBuffer();
		 tmp.ensureCapacity(src.length());
		 int lastPos = 0, pos = 0;
		 char ch;
		 while (lastPos < src.length()) {
			 pos = src.indexOf('%', lastPos);
			 if (pos == lastPos) {
				 if (src.charAt(pos + 1) == 'u') {
					 ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					 tmp.append(ch);
					 lastPos = pos + 6;
				 }else{
					 ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					 tmp.append(ch);
					 lastPos = pos + 3;
				 }
			 } else {
				 if (pos == -1) {
					 tmp.append(src.substring(lastPos));
					 lastPos = src.length();
				 } else {
					 tmp.append(src.substring(lastPos, pos));
					 lastPos = pos;
				 }
			 }
		}
		 return tmp.toString();
	 }
}
