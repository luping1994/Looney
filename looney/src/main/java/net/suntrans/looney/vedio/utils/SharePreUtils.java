package net.suntrans.looney.vedio.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * �����ļ�(ƫ�����ù�����)
 * @author creat by lt
 */
public class SharePreUtils {
	/**
	 * �洢�ַ����������ļ���
	 * @param preName
	 *            �ļ���
	 * @param context
	 * @param key
	 *            �洢�ļ�
	 * @param values
	 *            ��Ҫ��ŵ�ֵ
	 * @return ����ɹ��ı�־
	 */
	public static Boolean putString(String preName, Context context, String key, String values) {
		SharedPreferences shared = context.getSharedPreferences(preName, context.MODE_PRIVATE);
		Editor editor = shared.edit();
		editor.putString(key, values);
		return editor.commit();
	}

	/**
	 * �洢���ֵ������ļ���
	 * 
	 * @param preName
	 *            �ļ���
	 * @param context
	 * @param key
	 *            �洢�ļ�
	 * @param values
	 *            ��Ҫ��ŵ�ֵ
	 * @return ����ɹ��ı�־
	 */
	public static Boolean putInt(String preName, Context context, String key, int values) {
		SharedPreferences shared = context.getSharedPreferences(preName, context.MODE_PRIVATE);
		Editor editor = shared.edit();
		editor.putInt(key, values);
		return editor.commit();
	}

	/**
	 * �������ļ��ж�ȡ�ַ���
	 * 
	 * @param preName
	 *            �ļ���
	 * @param context
	 * @param key
	 *            ��ֵ
	 * @return ��ֵ��Ӧ���ַ�����Ĭ�Ϸ��� ""
	 */
	public static String getString(String preName, Context context, String key) {
		SharedPreferences shared = context.getSharedPreferences(preName, context.MODE_PRIVATE);
		return shared.getString(key, "");
	}
	
	/**
	 * �������ļ��ж�ȡFloat
	 * 
	 * @param preName
	 *            �ļ���
	 * @param context
	 * @param key
	 *            ��ֵ
	 * @return ��ֵ��Ӧ���ַ�����Ĭ�Ϸ��� ""
	 */
	public static float getFloat(String preName, Context context, String key) {
		SharedPreferences shared = context.getSharedPreferences(preName, context.MODE_PRIVATE);
		return shared.getFloat(key,0);
	}
	
	
	/**
	 * �洢Float�������ļ���
	 * @param preName
	 *            �ļ���
	 * @param context
	 * @param key
	 *            �洢�ļ�
	 * @param values
	 *            ��Ҫ��ŵ�ֵ
	 * @return ����ɹ��ı�־
	 */
	public static boolean putFloat(String preName, Context context, String key,float value) {
		SharedPreferences shared = context.getSharedPreferences(preName, context.MODE_PRIVATE);
		Editor editor = shared.edit();
		editor.putFloat(key, value);
		return editor.commit();
	}
	

	/**
	 * �������ļ��ж�ȡ int
	 * 
	 * @param preName
	 *            �ļ���
	 * @param context
	 * @param key
	 *            ��ֵ
	 * @return ��ֵ��Ӧ��int Ĭ�Ϸ���-1
	 */
	public static int getInt(String preName, Context context, String key) {
		SharedPreferences shared = context.getSharedPreferences(preName, context.MODE_PRIVATE);
		return shared.getInt(key, -1);

	}

	/**
	 * �洢Booleanֵ�������ļ�
	 * @param preName
	 *            �����ļ���
	 * @param key
	 *            ��ֵ
	 * @param value
	 *            ��Ҫ�洢��booleanֵ
	 */
	public static boolean putBoolean(String preName, Context context, String key, boolean value) {
		SharedPreferences pre = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
		Editor editor = pre.edit();
		editor.putBoolean(key, value);
		return editor.commit();
	}

	  /**
	   * �������ļ���ȡBooleanֵ
	   * @return ���û�У�Ĭ�Ϸ���false
	   */
	  public static boolean getBoolean(String preName, Context context, String key) {
	    SharedPreferences pre = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
	    return pre.getBoolean(key, false);
	  }


	/**
	 * ɾ����ֵ��
	 */
	public static void removeKey(String preName, Context context, String key) {
		SharedPreferences shared = context.getSharedPreferences(preName, context.MODE_PRIVATE);
		Editor editor = shared.edit();
		editor.remove(key);
		editor.commit();
	}
	
	
	/**
	 * �ж�preName �ļ����Ƿ���key
	 * @param preName
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean isHaveKey(String preName, Context context, String key){
		SharedPreferences shared = context.getSharedPreferences(preName, context.MODE_PRIVATE);
		return shared.contains(key);
	}

}
