package jumpei.matsuo.DiaryServer.service;

import jumpei.matsuo.DiaryServer.domain.Study;
import jumpei.matsuo.DiaryServer.domain.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyService {

  @Autowired
  private StudyRepository studyRepository;

  public void persist(Study study){
    studyRepository.save(study);
}
}
