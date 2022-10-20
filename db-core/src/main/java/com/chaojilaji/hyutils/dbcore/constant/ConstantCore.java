package com.chaojilaji.hyutils.dbcore.constant;

import java.util.ArrayList;
import java.util.List;

public class ConstantCore {

    public static String globalSplite = "qwe123QWE";
    /**
     * 数据权限
     */
    public enum DataPermissions {
        ALL_OWN_ORG(0, "本组织及下级组织"),
        OWN_ORG(1, "本组织"),
        MY(2, "仅自己");

        private Integer code;
        private String info;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        DataPermissions(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public static List<String> getAllInfos() {
            List<String> ans = new ArrayList<>();
            for (DataPermissions dataPermissions : DataPermissions.values()) {
                ans.add(dataPermissions.info);
            }
            return ans;
        }

        public static String findInfoByCode(Integer code) {
            for (DataPermissions dataPermissions : DataPermissions.values()) {
                if (dataPermissions.code.equals(code)) {
                    return dataPermissions.info;
                }
            }
            return "未找到";
        }

        public static DataPermissions findInfoByCode1(Integer code) {
            for (DataPermissions dataPermissions : DataPermissions.values()) {
                if (dataPermissions.code.equals(code)) {
                    return dataPermissions;
                }
            }
            return null;
        }

        public static Integer findCodeByInfo(String info) {
            for (DataPermissions dataPermissions : DataPermissions.values()) {
                if (dataPermissions.info.equals(info)) {
                    return dataPermissions.code;
                }
            }
            return -1;
        }

    }
}
