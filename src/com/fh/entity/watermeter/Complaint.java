package com.fh.entity.watermeter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/6.
 */
@Entity
@Table(name = "T_W_COMPLAINT")
public class Complaint implements Serializable {

    @Id
    @Column(name = "ID",length = 32)
    private String id;

    @Column(name = "COMPLAINT_TYPE",length = 1)
    private String complaintType;//投诉类型  1：发布骚扰内容  2：存在欺诈行为 3：存在侵权行为 4：发布仿冒品犀利 5：违反犯罪

    @Column(name = "COMPLAINT_USER_IMG",length = 200)
    private String complaintUserImg;

    @Column(name = "COMPLAINT_USER_ID",length = 32)
    private String complaintUserId;

    @Column(name = "COMPLAINT_CONTENT",length = 1000)
    private String complaintContent;

    @Column(name = "tag",length = 300)
    private String tag;

    @Column(name = "COMPLAINT_LINK",length = 200)
    private String complaintLink;

    @Column(name = "INFO_ID",length = 32)
    private String infoId;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getComplaintUserImg() {
        return complaintUserImg;
    }

    public void setComplaintUserImg(String complaintUserImg) {
        this.complaintUserImg = complaintUserImg;
    }

    public String getComplaintUserId() {
        return complaintUserId;
    }

    public void setComplaintUserId(String complaintUserId) {
        this.complaintUserId = complaintUserId;
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getComplaintLink() {
        return complaintLink;
    }

    public void setComplaintLink(String complaintLink) {
        this.complaintLink = complaintLink;
    }
}
