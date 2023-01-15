package jumpei.matsuo.DiaryServer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Study {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private String subject;
  private double studyHour;
  private String subjectDetail;
  private String tag;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime inputDateTime;
  private final int userId;


  public Study(String subject, double studyHour, String subjectDetail, String tag,
      String inputDateTime, int userId) {
    this.subject = subject;
    this.studyHour = studyHour;
    this.subjectDetail = subjectDetail;
    this.tag = tag;
    this.userId = userId;
    try {
      this.inputDateTime = LocalDateTime.parse(inputDateTime,FORMATTER);
    }catch (DateTimeParseException e){
      this.inputDateTime = LocalDateTime.now();
    }
  }
  public Study(String subject, double studyHour, String subjectDetail, String tag,
      LocalDateTime inputDateTime, int userId) {
    this.subject = subject;
    this.studyHour = studyHour;
    this.subjectDetail = subjectDetail;
    this.tag = tag;
    this.inputDateTime = inputDateTime;
    this.userId = userId;
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

  public int getUserId() {
    return userId;
  }
}
