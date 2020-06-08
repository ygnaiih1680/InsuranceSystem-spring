package system.insurance.backend.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.insurance.backend.announcement.Announcement;
import system.insurance.backend.employee.Employee;
import system.insurance.backend.exception.NoEmployeeException;
import system.insurance.backend.resource.reponse.ResponseAnnouncementContent;
import system.insurance.backend.resource.reponse.ResponseAnnouncementInfo;
import system.insurance.backend.resource.repository.AnnouncementRepository;
import system.insurance.backend.resource.repository.EmployeeRepository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AnnouncementServiceImpl(EmployeeRepository employeeRepository, AnnouncementRepository announcementRepository) {
        this.employeeRepository = employeeRepository;
        this.announcementRepository = announcementRepository;
    }

    @Override
    public List<ResponseAnnouncementInfo> findAll() {
        List<Announcement> announcements = this.announcementRepository.findAll();
        return this.createResponseData(announcements);
    }

    @Override
    public List<ResponseAnnouncementInfo> findAllByAuthor(int id) throws NoEmployeeException {
        List<Announcement> announcements = this.announcementRepository.findAllByAuthor(this.employeeRepository.findById(id).orElseThrow(NoEmployeeException::new));
        return this.createResponseData(announcements);
    }

    @Override
    public List<ResponseAnnouncementInfo> findAllByDate(String date) {
        List<Announcement> announcements = this.announcementRepository.findAllByDate(Date.valueOf(date));
        return this.createResponseData(announcements);
    }

    @Override
    public List<ResponseAnnouncementInfo> findAllByTitle(String title) {
        List<Announcement> announcements = this.announcementRepository.findAllByTitle(title);
        return this.createResponseData(announcements);
    }

    @Override
    public ResponseAnnouncementContent getContent(int id) {
        return ResponseAnnouncementContent.builder()
                .content(this.announcementRepository.findById(id).get().getContent())
                .build();
    }

    private List<ResponseAnnouncementInfo> createResponseData(List<Announcement> announcements){
        List<ResponseAnnouncementInfo> responseAnnouncementInfos = new ArrayList<>();
        for (Announcement announcement : announcements) {
            Employee author = announcement.getAuthor();
            responseAnnouncementInfos.add(ResponseAnnouncementInfo.builder()
                    .id(announcement.getId())
                    .title(announcement.getTitle())
                    .authId(author.getId())
                    .authName(author.getAuthority().getAuth())
                    .date(new SimpleDateFormat("yyyy-MM-dd").format(announcement.getDate()))
                    .priority(announcement.isPriority())
                    .build());

        }
        return responseAnnouncementInfos;
    }
}