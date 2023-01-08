package jumpei.matsuo.DiaryServer.controller.dto;

import jumpei.matsuo.DiaryServer.domain.Study;

public class CreateStudyDto {


  private String subject;
  private double studyHour;
  private String subjectDetail;
  private String tag;
  // FIXME: LocalDateTimeを使う
//  @JsonFormat(pattern = "YYYY-MM-dd HH:mm:SS")
  private String inputDateTime;

  public String getSubject() {
    return subject;
  }

  public double getStudyHour() {
    return studyHour;
  }

  public String getSubjectDetail() {
    return subjectDetail;
  }

  public String getTag() {
    return tag;
  }

  public String getInputDateTime() {
    return inputDateTime;
  }

  private CreateStudyDto(String subject, double studyHour, String subjectDetail, String tag,
      String inputDateTime) {
    this.subject = subject;
    this.studyHour = studyHour;
    this.subjectDetail = subjectDetail;
    this.tag = tag;
    this.inputDateTime = inputDateTime;
  }



  public Study createStudy() {
    return new Study();
  }
}
