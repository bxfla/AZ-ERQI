package az.com.newazhong.workguide.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2018/8/27.
 */

public  class DataBean {
    /**
     * id : 1
     * name : 1
     * content : 1
     * auditMaterials : [{"name":"name测试1","required":true,"memo":"memo测试1","sort":0},{"name":"name测试","required":true,"memo":"memo测试","sort":0}]
     * guideCategoryId : 1
     * departmentId : 5
     * departmentName : 城管科
     * faceType : MASSES
     * guideCategoryName : 社会保障
     */

    private int id;
    private String name;
    private String content;
    private int guideCategoryId;
    private int departmentId;
    private String departmentName;
    private String faceType;
    private String guideCategoryName;
    private List<AuditMaterialsBean> auditMaterials;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGuideCategoryId() {
        return guideCategoryId;
    }

    public void setGuideCategoryId(int guideCategoryId) {
        this.guideCategoryId = guideCategoryId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFaceType() {
        return faceType;
    }

    public void setFaceType(String faceType) {
        this.faceType = faceType;
    }

    public String getGuideCategoryName() {
        return guideCategoryName;
    }

    public void setGuideCategoryName(String guideCategoryName) {
        this.guideCategoryName = guideCategoryName;
    }

    public List<AuditMaterialsBean> getAuditMaterials() {
        return auditMaterials;
    }

    public void setAuditMaterials(List<AuditMaterialsBean> auditMaterials) {
        this.auditMaterials = auditMaterials;
    }

    public static class AuditMaterialsBean implements Serializable{
        /**
         * name : name测试1
         * required : true
         * memo : memo测试1
         * sort : 0
         */

        private String name;
        private boolean required;
        private String memo;
        private int sort;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
