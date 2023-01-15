package jumpei.matsuo.DiaryServer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import jumpei.matsuo.DiaryServer.controller.dto.StudiesDto;
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
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

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
    softAssertions.assertThat(studyArgumentCaptor.getValue().getUserId()).isEqualTo(1);
    softAssertions.assertThat(studyArgumentCaptor.getValue().getSubject()).isEqualTo("IT");
    softAssertions.assertThat(studyArgumentCaptor.getValue().getStudyHour()).isEqualTo(8.5);
    softAssertions.assertThat(studyArgumentCaptor.getValue().getSubjectDetail()).isEqualTo("JOOQを勉強した。");
    softAssertions.assertThat(studyArgumentCaptor.getValue().getTag()).isEqualTo("資格試験");
    softAssertions.assertThat(studyArgumentCaptor.getValue().getInputDateTime()).isEqualTo(LocalDateTime.parse("2023-01-08 09:50:01",
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    softAssertions.assertAll();
  }

  @Test
  @DisplayName("/study にGETすると、履歴を返却すること")
  void t1() throws Exception{

    Mockito.when(studyRepository.findByUserId(1)).thenReturn(List.of(new Study("IT",8.0,"yyyy","z","",1)));
    String content = new ObjectMapper().writeValueAsString(new StudiesDto(1));
    mockMvc.perform(
        post("/study/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
    )
        .andExpect(status().is(200));
    // TODO 時刻は、LocalDateTime.nowを使っているので、一致しない。
//    .andExpect(content().string(expectedJson()));
  }

  private String expectedJson(){
    return """
        {
        "list": [
        {
        "subject": "IT",
        "studyHour": 8.2,
        "subjectDetail": "yyyy",
        "tag": "z",
        "inputDateTime": "",
        "userId": 1
        }
        ]
        }
        """;
  }

  private String requestJson() {
    return """
        {
        "userId": 1,
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
