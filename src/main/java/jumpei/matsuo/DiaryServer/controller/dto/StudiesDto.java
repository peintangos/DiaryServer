package jumpei.matsuo.DiaryServer.controller.dto;

public class StudiesDto {
  private int userId;

  public StudiesDto(int userId) {
    this.userId = userId;
  }

  public StudiesDto(){

  }

  public int getUserId() {
    return userId;
  }

}
