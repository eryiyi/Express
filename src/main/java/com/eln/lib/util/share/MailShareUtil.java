/*
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.eln.lib.util.share;

import android.content.Context;
import android.content.Intent;

/**@projectName XinXinLib
 * @PackageName com.eln.lib.share
 * @Title MailShareUtil
 * @Description  邮件分享的类
 * @Author XinXinTeam(xiaofeng.Lu)
 * @Date 2014-4-8
 * @Version V1.0
 */
public class MailShareUtil
{
	/**
	 * 邮件分享
	 * 
	 * @param mContext
	 * @param title
	 *            邮件的标题
	 * @param text
	 *            邮件的内容
	 * @return
	 */
	public static Boolean sendMail(Context mContext, String title, String text)
	{
		// 调用系统发邮件
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		// 设置文本格式
		emailIntent.setType("text/plain");
		// 设置对方邮件地址
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, "");
		// 设置标题内容
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
		// 设置邮件文本内容
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
		mContext.startActivity(Intent.createChooser(emailIntent,
				"Choose Email Client"));
		return null;
	}
}
