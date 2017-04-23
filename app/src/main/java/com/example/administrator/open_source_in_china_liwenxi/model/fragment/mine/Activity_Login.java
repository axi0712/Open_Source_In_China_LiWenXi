package com.example.administrator.open_source_in_china_liwenxi.model.fragment.mine;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.Mine_LoginJavaBean;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.news.search.MyManager;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class Activity_Login extends BaseActivity {
    private EditText mLoginName;
    private EditText mLoginMima;
    private Button mLoginLogin;
    private String name;
    private String pwd;
    private INewModel model = null;
    private MyManager mDB;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void getID() {
        assignViews();
    }

    @Override
    protected int getZujian() {
        return 0;
    }

    @Override
    protected void getOnClick() {

    }

    @Override
    protected void initDatas() {

    }
    private void assignViews() {
        mShared = getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mShared.edit();
        mLoginName = (EditText) findViewById(R.id.login_name);
        mLoginMima = (EditText) findViewById(R.id.login_mima);
        mLoginLogin = (Button) findViewById(R.id.login_login);
        model = new NewsModelImple();
        mDB = new MyManager(Activity_Login.this);
        mLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChecked()) {
                    getRetrofit();
                }
            }
        });
    }

    private void getRetrofit() {
        model.mine_login(name, pwd, "1", new MyCallBack() {
            @Override
            public void onErro(String strErro) {

            }

            @Override
            public void onSuccess(String strSuccess) {
                XStream xs = new XStream();
                xs.alias("oschina", Mine_LoginJavaBean.class);
                xs.alias("result", Mine_LoginJavaBean.ResultBean.class);
                Mine_LoginJavaBean loginBean = (Mine_LoginJavaBean) xs.fromXML(strSuccess);
                Log.i("login", loginBean.getResult().getErrorCode() + loginBean.getResult().getErrorMessage());

                if (Integer.parseInt(loginBean.getResult().getErrorCode()) == 1) {
                    Toast.makeText(Activity_Login.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    Log.i("登陆成功", loginBean.getResult().getErrorMessage());
                    Log.i("image++++",loginBean.getUser().getPortrait());
                    Log.i("shji_______",loginBean.getUser().getUid());
                    mEditor.putString("image",loginBean.getUser().getPortrait());
                    mEditor.putString("name",loginBean.getUser().getName());
                    mEditor.putString("uid",loginBean.getUser().getUid());
                    mEditor.commit();
                    finish();
                } else {
                    Toast.makeText(Activity_Login.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }





    public Boolean isChecked() {
        name = mLoginName.getText().toString();
        pwd = mLoginMima.getText().toString();
        if (name.isEmpty() || pwd.isEmpty()) {
            return false;
        }
        return true;
    }
}
