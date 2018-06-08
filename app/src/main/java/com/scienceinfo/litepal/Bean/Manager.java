package com.scienceinfo.litepal.Bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * 用户类
 */
public class Manager extends DataSupport {
    //用户 id  用户名 用户密码

    private  int M_id;
    private String M_name;
    private String M_password;


    public int getM_id() {
        return M_id;
    }

    public void setM_id(int m_id) {
        M_id = m_id;
    }

    public String getM_name() {
        return M_name;
    }

    public void setM_name(String m_name) {
        M_name = m_name;
    }

    public String getM_password() {
        return M_password;
    }

    public void setM_password(String m_password) {
        M_password = m_password;
    }


}
