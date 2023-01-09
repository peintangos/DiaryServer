package jumpei.matsuo.DiaryServer.domain;

import java.util.List;
import jumpei.matsuo.DiaryServer.domain.Study;
import org.springframework.stereotype.Repository;

public interface StudyRepository {

  void save(Study study);

  List<Study> findAll();
}
