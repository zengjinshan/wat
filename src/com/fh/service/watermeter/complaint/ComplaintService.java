package com.fh.service.watermeter.complaint;

import com.fh.entity.watermeter.Attachment;
import com.fh.entity.watermeter.Complaint;
import com.fh.entity.watermeter.WatUser;
import com.fh.framewrok.service.BaseService;
import com.fh.framewrok.util.Const;
import com.fh.framewrok.util.FileUpload;
import com.fh.framewrok.util.UuidUtil;
import com.fh.framewrok.util.WeChatProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/6.
 */
@Service
public class ComplaintService extends BaseService {

    public void complaint(WatUser user, String type, MultipartFile[] files,String content,String link,String infoId) throws Exception{
        Complaint complaint=new Complaint();
        complaint.setId(UuidUtil.get32UUID());
        complaint.setComplaintContent(content);
        complaint.setComplaintType(type);
        complaint.setComplaintLink(link);
        complaint.setComplaintUserId(user.getId());
        complaint.setTag(user.getTag());
        complaint.setComplaintUserImg(user.getImg());
        complaint.setInfoId(infoId);
        this.save(complaint);
        String originalFilename="";
        String fileType="";
        for(int i=0;i<files.length;i++){
            originalFilename = files[i].getOriginalFilename();
            fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filepath = WeChatProperty.getPropertyValue("filepath");
            String fileName = FileUpload.fileUp(files[i], filepath, UuidUtil.get32UUID());
            Attachment atta = new Attachment();
            atta.setCreateDate(new Date());
            atta.setId(UuidUtil.get32UUID());
            atta.setServiceId(complaint.getId());
            atta.setServiceType("2");
            atta.setFilePath(Const.DEFAULT_PATH + fileName);
            atta.setType(fileType);
            this.save(atta);
        }
    }
}
