package jumpei.matsuo.DiaryServer.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jumpei.matsuo.DiaryServer.domain.Study;
import jumpei.matsuo.DiaryServer.domain.StudyRepository;
import org.assertj.core.api.SoftAssertions;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DisplayName("DBにデータをInsertできることを確認する")
@Testcontainers
@ExtendWith(SpringExtension.class)
@Import(StudyRepositoryImpl.class)
class StudyRepositoryImplTest {

  @Container
  static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql")
      .withUsername("docker")
      .withPassword("docker")
      .withDatabaseName("sample_db")
      .withExposedPorts(3306);

  @Autowired
  private StudyRepository studyRepository;

  @Test
  // TODO: DI周りのエラーが出る。
  @Disabled
  void t0() {
    //起動したMYSQLコンテナでマイグレーションを実行する
    Flyway.configure()
        .dataSource(mySQLContainer.getJdbcUrl(), mySQLContainer.getUsername(),
            mySQLContainer.getPassword())
        .load()
        .migrate();

    SoftAssertions softAssertions = new SoftAssertions();

    String subject = "世界史";
    double studyHour = 2.4;
    String subjectDetail = "中国紙のまとめを勉強した。";
    String tag = "tag";
    String inputDateTime = "2023-01-11 09:50:01";

    Study study = new Study(subject, studyHour, subjectDetail, tag, inputDateTime);
    studyRepository.save(study);
    Study result = studyRepository.findAll().get(0);

    softAssertions.assertThat(result.getSubject()).isEqualTo(subject);
    softAssertions.assertThat(result.getSubjectDetail()).isEqualTo(subjectDetail);
    softAssertions.assertThat(result.getTag()).isEqualTo(tag);
    softAssertions.assertThat(result.getStudyHour()).isEqualTo(studyHour);
    softAssertions.assertThat(result.getInputDateTime()).isEqualTo(LocalDateTime.parse(
        inputDateTime,
        DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

    softAssertions.assertAll();
  }
}
