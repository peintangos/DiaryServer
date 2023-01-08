package jumpei.matsuo.DiaryServer.controller;

import jumpei.matsuo.DiaryServer.controller.dto.CreateStudyDto;
import jumpei.matsuo.DiaryServer.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
