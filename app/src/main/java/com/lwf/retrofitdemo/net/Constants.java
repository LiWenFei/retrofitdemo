package com.lwf.retrofitdemo.net;

/**
 * @Description: 全局常量类
 * @author luoch
 * @date 2014-11-8 下午4:36:11
 * 
 */
public class Constants {

	public static final int HTTP_TIME_OUT = 15000;

	public static final int SID_INVALID = 20012;

	/**
	 * MD5签名key
	 */
	public static final String SIGN_MD5_KEY_STRING = "SFPD0afjaLN?SFl0ad$?*&+=(*#21#$K";

	public static final String KEY_SID = "sid";
	public static final String KEY_UID = "uid";
	public static final String KEY_LOGIS_ID = "logis_id";

	/**
	 * 1、柜体
	 */
	public static final int SHELF_TYPE_CELL = 1;

	/**
	 * 2、货架
	 */
	public static final int SHELF_TYPE_LAYER = 2;

	/**
	 * 3、地堆
	 */
	public static final int SHELF_TYPE_HEAP = 3;

	/**
	 * 暂存区
	 */
	public static final int SHELF_TYPE_TEMP = 0;

	/**
	 * 格口类型：大箱
	 */
	public static final int CELL_TYPE_BIG = 10901;
	/**
	 * 格口类型：中箱
	 */
	public static final int CELL_TYPE_MIDDLE = 10902;
	/**
	 * 格口类型：小箱
	 */
	public static final int CELL_TYPE_SMALL = 10903;
	/**
	 * 格口类型：超小箱
	 */

	public static final int CELL_TYPE_SUPER = 10904;

	/**
	 * 入库功能activity跳转requestCode
	 */

	public static final int STORAGE_SUCCESS_REQUEST_CODE = 10000;
	/**
	 * 入库成功requestCode
	 */
	public static final int STORAGE_SUCCESS_RESULT_CODE = 10001;

	/**
	 * 入库成功且批次入库完成requestCode
	 */
	public static final int STORAGE_SUCCESS_AND_BATCHS_END_RESULT_CODE = 10008;
	/**
	 * 密码修改activity跳转requestCode
	 */
	public static final int PASSWORD_UPDATE_REQUEST_CODE = 10002;

	/**
	 * 密码修改requestCode
	 */
	public static final int PASSWORD_UPDATE_RESULT_CODE = 10003;

	/**
	 * 登录activity跳转requestCode
	 */
	public static final int LOGIN_REQUEST_CODE = 10004;

	/**
	 * 登录requestCode
	 */
	public static final int LOGIN_RESULT_CODE = 10005;

	/**
	 * 临时取件功能activity跳转requestCode
	 */

	public static final int PICKUP_TEMP_SUCCESS_REQUEST_CODE = 10006;
	/**
	 * 临时取件成功requestCode
	 */
	public static final int PICKUP_TEMP_SUCCESS_RESULT_CODE = 10007;

	/**
	 * 取件功能activity跳转requestCode
	 */

	public static final int PICKUP_SUCCESS_REQUEST_CODE = 10008;
	/**
	 * 取件成功requestCode
	 */
	public static final int PICKUP_SUCCESS_RESULT_CODE = 10009;
	/*
	 * 查询页面修改手机跳转requestCode
	 */
	public static final int QUERY_CHANGE_PHONE_CONFIRM_Q_CODE = 10006;

	/*
	 * 查询页面修改手机成功requestCode
	 */
	public static final int QUERY_CHANGE_PHONE_CONFIRM_R_CODE = 10007;

	/*
	 * 查询页面修改手机确认跳转requestCode
	 */
	public static final int QUERY_CHANGE_PHONE_Q_CODE = 10008;

	/*
	 * 查询页面修改手机确认成功requestCode
	 */
	public static final int QUERY_CHANGE_PHONE_R_CODE = 10009;

	/*
	 * 查询页面修改批次跳转requestCode
	 */
	public static final int QUERY_CHANGE_BATCH_Q_CODE = 10010;

	/*
	 * 查询页面修改批次成功requestCode
	 */
	public static final int QUERY_CHANGE_BATCH_R_CODE = 10011;

	/*
	 * 查询页面修改手机返回主菜单requestCode
	 */
	public static final int QUERY_CHANGE_PHONE_BACK_R_CODE = 10012;

	/*
	 * 查询运单号详情跳转questCode
	 */
	public static final int QUERY_ORDER_INFOS_Q_CODE = 10013;

	/*
	 * 查询，通过对应快递的快递单questCode
	 */
	public static final int QUERY_BY_ORDER_Q_CODE = 10014;

	/*
	 * 暂存区取件输入运单号请求questCode
	 */
	public static final int PICKUP_TEMP_BY_ORDER_Q_CODE = 10015;

	/*
	 * 暂存区取件输入收件人手机号请求questCode
	 */
	public static final int PICKUP_TEMP_RECEIVER_Q_CODE = 10016;

	/*
	 * 暂存区取件questCode
	 */
	public static final int PICKUP_TEMP_ORDER_INFOS_Q_CODE = 10017;

	/*
	 * 暂存区取件返回resultCode
	 */
	public static final int PICKUP_TEMP_BACK_R_CODE = 10018;

	/*
	 * 退回取件requestCode
	 */
	public static final int RETURN_PICKUP_Q_CODE = 10019;

	/**
	 * 退回取件完成resultCode
	 */
	public static final int RETURN_PICKUP_R_CODE = 10020;

	/*
	 * 退回原因：手机号错误
	 */
	public static final int RETURN_REASON_TYPE_PHONE_WRONG = 1;
	/*
	 * 退回原因：无人接听
	 */
	public static final int RETURN_REASON_TYPE_PHONE_NO_ANSWER = 2;

}
