package az.com.newazhong.xingzhengwork.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 */

public class SignInHistory implements Serializable {

    /**
     * result : true
     * message : null
     * data : [{"id":1286,"no":"70104","workNo":"70104","name":"计生科","year":2018,"month":9,"day":12,"address":"山东省济南市历下区","inRange":false,"memo":"%E6%B5%8B%E8%AF%95","terminalType":"APP","attendanceType":null,"recordTime":"2018-09-12 14:16:33"}]
     */

    private boolean result;
    private Object message;
    private List<DataBean> data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1286
         * no : 70104
         * workNo : 70104
         * name : 计生科
         * year : 2018
         * month : 9
         * day : 12
         * address : 山东省济南市历下区
         * inRange : false
         * memo : %E6%B5%8B%E8%AF%95
         * terminalType : APP
         * attendanceType : null
         * recordTime : 2018-09-12 14:16:33
         */

        private int id;
        private String no;
        private String workNo;
        private String name;
        private int year;
        private int month;
        private int day;
        private String address;
        private boolean inRange;
        private String memo;
        private String terminalType;
        private Object attendanceType;
        private String recordTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getWorkNo() {
            return workNo;
        }

        public void setWorkNo(String workNo) {
            this.workNo = workNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isInRange() {
            return inRange;
        }

        public void setInRange(boolean inRange) {
            this.inRange = inRange;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getTerminalType() {
            return terminalType;
        }

        public void setTerminalType(String terminalType) {
            this.terminalType = terminalType;
        }

        public Object getAttendanceType() {
            return attendanceType;
        }

        public void setAttendanceType(Object attendanceType) {
            this.attendanceType = attendanceType;
        }

        public String getRecordTime() {
            return recordTime;
        }

        public void setRecordTime(String recordTime) {
            this.recordTime = recordTime;
        }
    }
}
