package com.huantai.qhytims.sharepfs;

import android.content.Context;
import android.content.SharedPreferences;

import com.huantai.qhytims.bean.User;


public class Sharepf {
    private static final String spfName = "qhytims";
    private SharedPreferences sp;

    public Sharepf(Context context) {
        super();
        sp = context.getSharedPreferences(spfName, Context.MODE_PRIVATE);
    }

    public void writeUser(String name, String password) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loginName", name);
        editor.putString("loginPwd", password);
        editor.commit();
    }


    /**
     * @param DepartName   部门名称
     * @param CompNum      工号
     * @param DepartmentID 部门ID
     * @param IFAvailable  是否离职
     * @param IsCloseRight 是否禁用权限
     */
    public void writeUserDetail(int ID, String DepartName, String CompNum, int DepartmentID,
                                int IFAvailable, int IsCloseRight, String perName,
                                int BranchID, String BranchName) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("DepartName", DepartName);
        editor.putString("CompNum", CompNum);
        editor.putString("perName", perName);
        editor.putInt("DepartmentID", DepartmentID);
        editor.putInt("IFAvailable", IFAvailable);
        editor.putInt("IsCloseRight", IsCloseRight);
        editor.putInt("UserId", ID);
        editor.putInt("BranchID", BranchID);
        editor.putString("BranchName", BranchName);
        editor.commit();
    }

    public void clearUser() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loginName", "");
        editor.putString("loginPwd", "");
        editor.putString("DepartName", "");
        editor.putString("CompNum", "");
        editor.putInt("DepartmentID", 0);
        editor.putInt("IFAvailable", 0);
        editor.putInt("IsCloseRight", 0);
        editor.putInt("BranchID", 0);
        editor.putString("BranchName", "");
        editor.commit();
    }

    /**
     * @return loginName loginPwd loginIsAuto loginIsRemember
     */
    public User readUser() {

        User user = new User();
        user.setCode(sp.getString("CompNum", ""));
        user.setName(sp.getString("loginName", ""));
        user.setPsw(sp.getString("loginPwd", ""));
        user.setDepart(sp.getString("DepartName", ""));
        user.setDepartId(sp.getInt("DepartmentID", 0));
        user.setIFAvailable(sp.getInt("IFAvailable", 0));
        user.setIsCloseRight(sp.getInt("IsCloseRight", 0));
        user.setId(sp.getInt("UserId", 0));
        user.setPerName(sp.getString("perName", ""));

        user.setBranchID(sp.getInt("BranchID",0));
        user.setBranchName(sp.getString("BranchName",""));

        return user;
    }

    public String readUserId() {
        return sp.getInt("UserId", 1) + "";
    }
    public int readUserIdInt() {
        return sp.getInt("UserId", 0);
    }

    public void setUserId(int ID) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("UserId", ID);
        editor.commit();
    }

    /**
     * 更新密码
     */
    public void updatePsw(String password) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loginPwd", password);
        editor.commit();
    }

    public void setIsLogin(boolean isLogin) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", isLogin);
        editor.commit();
    }

    public boolean getIsLogin() {
        return sp.getBoolean("isLogin", false);
    }

    public void setSavePsw(boolean isSave) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("savePsw", isSave);
        editor.commit();
    }

    public boolean getSavePsw() {
        return sp.getBoolean("savePsw", false);
    }

    /**
     * 打卡的ip地址
     * @param IP
     */
    public void setIp(String IP) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("IP", IP);
        editor.commit();
    }

    public String getIp() {
        return sp.getString("IP", "");
    }

    /**
     * APP的统一ip
     * @param IP
     */
    public void setBaseIp(String IP) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("BaseIP", IP);
        editor.commit();
    }

    public String getBaseIp() {
        return  sp.getString("BaseIP", "");  // "http://124.114.180.134:8900"
    }

    public int readServiceId() {
        return sp.getInt("ServiceId",1);
    }

    public void setServiceId(int ID) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("ServiceId", ID);
        editor.commit();
    }
    public void setIpJSON(String IP) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("IpJSON", IP);
        editor.commit();
    }

    public String getIpJSON() {
        return  sp.getString("IpJSON", "");  // "http://124.114.180.134:8900"
    }
}
