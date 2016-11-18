package com.lwf.retrofitdemo.net;

import android.text.TextUtils;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author wenqiurong
 * @version [版本号, 2014-9-26]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public class StringUtil {
  
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * 
	 * 判断是否是电话
	 * 
	 * @param phone
	 * 
	 * @return
	 */
	public static boolean isPhone(String phone) {
		// Pattern phonePattern = Pattern.compile("^1\\d{10}$");
		Pattern phonePattern = Pattern
				.compile("(^1\\d{10})$|(^(0[0-9]{2,3}-)([2-9][0-9]{6,7})+(/-[0-9]{1,4})?$)");
		Matcher matcher = phonePattern.matcher(phone);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
  
	/**
	 * 半角转换为全角
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {

		if (input == null) {
			return input;
		}

		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	 
	/**
	 * 检测是否有emoji字符
	 * 
	 * @param source
	 * @return 一旦含有就抛出
	 */
	public static boolean containsEmoji(String source) {

		if (TextUtils.isEmpty(source)) {
			return false;
		}

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (!isNormalCharacter(codePoint)) {
				// do nothing，判断到了这里表明，确认有表情字符

				Log.e("Emoji", "container emoji[" + codePoint + "]");

				return true;
			} else {
				Log.e("Emoji", "normal character[" + codePoint + "]");
			}
		}

		return false;
	}

	private static boolean isNormalCharacter(char codePoint) {

		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
				|| (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));

	}

	 
}
