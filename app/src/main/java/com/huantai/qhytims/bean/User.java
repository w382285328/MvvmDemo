package com.huantai.qhytims.bean;


import com.huantai.qhytims.tools.TextUtil;

public class User {

    private String name,depart,code,psw,perName;
            private int departId,id,IFAvailable,IsCloseRight;
                                      private String BranchName;
                                      private int BranchID;

                                      public String getName() {
        return TextUtil.getNotNullString(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepart() {
        return TextUtil.getNotNullString(depart);
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = TextUtil.getNotNullString(code);
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = TextUtil.getNotNullString(psw);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIFAvailable() {
        return IFAvailable;
    }

    public void setIFAvailable(int IFAvailable) {
        this.IFAvailable = IFAvailable;
    }

    public int getIsCloseRight() {
        return IsCloseRight;
    }

    public void setIsCloseRight(int isCloseRight) {
        IsCloseRight = isCloseRight;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        BranchID = branchID;
    }
}
