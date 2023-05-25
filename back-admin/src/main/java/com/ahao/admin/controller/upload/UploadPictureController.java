package com.ahao.admin.controller.upload;

import com.ahao.admin.utils.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Description: UploadPictureController
 * @Author: ahao
 * @Date: 2023/5/9 18:49
 **/

@RestController
@CrossOrigin
@RequestMapping("upload")
public class UploadPictureController {

    @PostMapping("courtRegionPic")
    public R courtRegionPic (MultipartFile file) throws IOException {
        file.transferTo(new File("D:/interllij idea/4+/basSystem/bas-front/src/assets/court/"+file.getOriginalFilename()));
        return null;
    }

    @PostMapping("equipmentPic")
    public R equipmentPic (MultipartFile file) throws IOException {
        file.transferTo(new File("D:/interllij idea/4+/basSystem/bas-front/src/assets/equipment/"+file.getOriginalFilename()));
        return null;
    }

    @PostMapping("newsPic")
    public R newsPic (MultipartFile file) throws IOException {
        file.transferTo(new File("D:/interllij idea/4+/basSystem/bas-front/src/assets/news/"+file.getOriginalFilename()));
        return null;
    }


}
