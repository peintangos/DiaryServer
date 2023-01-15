package jumpei.matsuo.DiaryServer.controller.dto;

import java.util.List;
import jumpei.matsuo.DiaryServer.domain.Study;

public class Studies {

  public List<Study> getList() {
    return list;
  }

  public Studies(List<Study> list) {
    this.list = list;
  }

  private List<Study> list;
}
