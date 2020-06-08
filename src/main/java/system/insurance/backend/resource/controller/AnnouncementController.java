package system.insurance.backend.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.insurance.backend.resource.reponse.ResponseAnnouncementContent;
import system.insurance.backend.resource.reponse.ResponseAnnouncementInfo;
import system.insurance.backend.resource.service.AnnouncementService;

import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }


    @GetMapping("/info")
    @ResponseBody
    public List<ResponseAnnouncementInfo> getAllAnnouncement() {
        return this.announcementService.findAll();
    }

    @GetMapping("/content{id}")
    public ResponseAnnouncementContent getContent(@PathVariable String id){
        return this.announcementService.getContent(Integer.parseInt(id));
    }
}