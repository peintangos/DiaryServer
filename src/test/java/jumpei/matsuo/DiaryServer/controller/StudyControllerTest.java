package jumpei.matsuo.DiaryServer.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jumpei.matsuo.DiaryServer.controller.dto.CreateStudyDto;
import jumpei.matsuo.DiaryServer.domain.StudyRepository;
import jumpei.matsuo.DiaryServer.repository.StudyRepositoryImpl;
import jumpei.matsuo.DiaryServer.service.StudyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@DisplayName("StudyControllerについてのAPIテスト。リクエストが正しい場合に、status codeが200を返すことを確認する")
@WebMvcTest(controllers = StudyController.class)
// Importは、ControllerでDIしているStudyServiceやさらに、StudyServiceがDIしているStudyRepository(StudyRepositoryImpl)をDIコテンナに登録するためにつけるアノテーション
@Import({StudyService.class, StudyRepositoryImpl.class})
class StudyControllerTest {

  @Autowired
  MockMvc mockMvc;


  @Test
  @DisplayName("/study にPOSTできることを確認する。")
  void t0() throws Exception{
    // JacksonでオブジェクトをJson形式に変換
    CreateStudyDto createStudyDto = new CreateStudyDto();
    ObjectMapper mapper = new ObjectMapper();
    // 空のクラスからJSONに変換しようとするとエラーになるワークアラウンド
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    String json = mapper.writeValueAsString(createStudyDto);

    mockMvc.perform(
        post("/study")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
    ).andExpect(status().is(200)
    );
  }
}
