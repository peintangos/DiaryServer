package jumpei.matsuo.DiaryServer.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.springframework.cglib.core.Local;

public class Study {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private String subject;
  private double studyHour;
  private String subjectDetail;
  private String tag;
  private LocalDateTime inputDateTime;


  public Study(String subject, double studyHour, String subjectDetail, String tag,
      String inputDateTime) {
    this.subject = subject;
    this.studyHour = studyHour;
    this.subjectDetail = subjectDetail;
    this.tag = tag;
    try {
      this.inputDateTime = LocalDateTime.parse(inputDateTime,FORMATTER);
    }catch (DateTimeParseException e){
      this.inputDateTime = LocalDateTime.now();
    }
  }
  public Study(String subject, double studyHour, String subjectDetail, String tag,
      LocalDateTime inputDateTime) {
    this.subject = subject;
    this.studyHour = studyHour;
    this.subjectDetail = subjectDetail;
    this.tag = tag;
    this.inputDateTime = inputDateTime;
  }

  public String getSubject() {
    return subject;
  }

  public String getSubjectDetail() {
    return subjectDetail;
  }

  public String getTag() {
    return tag;
  }

  public LocalDateTime getInputDateTime() {
    return inputDateTime;
  }

  public double getStudyHour() {
    return studyHour;
  }
}
