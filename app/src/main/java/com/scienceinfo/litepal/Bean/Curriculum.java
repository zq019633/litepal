package com.scienceinfo.litepal.Bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * 课程类
 */
public class Curriculum  extends DataSupport {
    //课程名  课程编号   课程信息

    private int C_id;

    private String C_name;
    private String C_info;


    public long getBaseObjId() {
        return super.getBaseObjId();
    }

    public String getC_name() {
        return C_name;
    }

    public void setC_name(String c_name) {
        C_name = c_name;
    }

    public int getC_id() {
        return C_id;
    }

    public void setC_id(int c_id) {
        C_id = c_id;
    }

    public String getC_info() {
        return C_info;
    }

    public void setC_info(String c_info) {
        C_info = c_info;
    }


}
