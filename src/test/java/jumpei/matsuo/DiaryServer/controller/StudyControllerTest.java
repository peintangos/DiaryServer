package jumpei.matsuo.DiaryServer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jumpei.matsuo.DiaryServer.domain.Study;
import jumpei.matsuo.DiaryServer.domain.StudyRepository;
import jumpei.matsuo.DiaryServer.service.StudyService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("StudyControllerについてのAPIテスト。リクエストが正しい場合に、status codeが200を返すことを確認する。また、Repository層までデータを伝搬できていることを確認する。")
@WebMvcTest(controllers = StudyController.class)
// @Importは、ControllerでDIしているStudyServiceやさらに、StudyServiceがDIしているStudyRepository(StudyRepositoryImpl)をDIコテンナに登録するためにつけるアノテーション
// なお、Mockしたい場合は@MockBeanをつければ、DIする必要がない。
@Import({StudyService.class})
class StudyControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  StudyRepository studyRepository;


  @Test
  @DisplayName("/study にPOSTできることと、Repository層までデータを伝搬できていることを確認する。")
  void t0() throws Exception{
    SoftAssertions softAssertions = new SoftAssertions();

    ArgumentCaptor<Study> studyArgumentCaptor = ArgumentCaptor.forClass(Study.class);
    Mockito.doNothing().when(studyRepository).save(studyArgumentCaptor.capture());

    mockMvc.perform(
        post("/study")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson())
    ).andExpect(status().is(200));


    // 引数の検証
    softAssertions.assertThat(studyArgumentCaptor.getValue().getSubject()).isEqualTo("IT");
    softAssertions.assertThat(studyArgumentCaptor.getValue().getStudyHour()).isEqualTo(8.5);
    softAssertions.assertThat(studyArgumentCaptor.getValue().getSubjectDetail()).isEqualTo("JOOQを勉強した。");
    softAssertions.assertThat(studyArgumentCaptor.getValue().getTag()).isEqualTo("資格試験");
    softAssertions.assertThat(studyArgumentCaptor.getValue().getInputDateTime()).isEqualTo(LocalDateTime.parse("2023-01-08 09:50:01",
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    softAssertions.assertAll();
  }

  private String requestJson() {
    return """
        {
        "subject": "IT",
        "studyHour": 8.5,
        "subjectDetail": "JOOQを勉強した。",
        "tag": "資格試験",
        "inputDateTime": "2023-01-08 09:50:01"
        }
        """;
  }
  // TODO: バリデーションのテスト(Not Nullにする。)
  // TODO: TestContainerによるテストを追加
  // TODO: JOOQのEveryビルドでの自動生成を止める。
}
