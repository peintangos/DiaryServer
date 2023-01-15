package jumpei.matsuo.DiaryServer.controller;

import java.util.List;
import jumpei.matsuo.DiaryServer.controller.dto.CreateStudyDto;
import jumpei.matsuo.DiaryServer.controller.dto.Studies;
import jumpei.matsuo.DiaryServer.controller.dto.StudiesDto;
import jumpei.matsuo.DiaryServer.domain.Study;
import jumpei.matsuo.DiaryServer.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("study")
public class StudyController {

  @Autowired
  private StudyService studyService;

  @PostMapping
  public void addStudy(@RequestBody CreateStudyDto createStudyDto){
    studyService.persist(createStudyDto.createStudy());
  }

  @PostMapping("user")
  public Studies studies(@RequestBody StudiesDto studiesDto){
    return new Studies(studyService.findBy(studiesDto.getUserId()));
  }
}
