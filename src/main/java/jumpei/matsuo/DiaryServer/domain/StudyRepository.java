package jumpei.matsuo.DiaryServer.domain;

import java.util.List;

public interface StudyRepository {

  void save(Study study);

  List<Study> findAll();

  List<Study> findByUserId(int userId);
}
