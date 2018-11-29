package az.com.newazhong.utilsclass.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * 此工具类用来检测输入密码以及用户名。输入则显示清除按钮，空白则不显示
 * */
public class EditTextClearTools {
	public static void addclerListener(final EditText e1, final ImageView m1) {

		e1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
										  int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				// 监听如果输入串长度大于0那么就显示clear按钮。
				String s1 = s + "";
				if (s.length() > 0) {
					m1.setVisibility(View.VISIBLE);
				} else {

					m1.setVisibility(View.INVISIBLE);
				}

			}
		});

		m1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 清空输入框
				e1.setText("");

			}
		});

	}

}
