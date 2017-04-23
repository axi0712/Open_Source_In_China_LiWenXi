package com.example.administrator.open_source_in_china_liwenxi.model.http;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class ParsingFactory {
    private static final int OKHTPP = 1;
    private static final int RETROFIT = 2;
    private static final int TYPE = RETROFIT;
    public static IHTPP initParsing(){
        IHTPP ihttp = null;
        switch(TYPE){
            case OKHTPP:
                break;
            case RETROFIT:
                ihttp = new Activity_Retrofit();

                break;
        }
        return ihttp;
    }
}
