package jumpei.matsuo.DiaryServer.repository;

import static jumpei.matsuo.DiaryServer.diary_db.Tables.DIARY_HISTORY;

import java.util.List;
import jumpei.matsuo.DiaryServer.domain.Study;
import jumpei.matsuo.DiaryServer.domain.StudyRepository;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudyRepositoryImpl implements StudyRepository {

  @Autowired
  DSLContext dslContext;

  @Override
  public void save(Study study) {
    dslContext.
        insertInto(DIARY_HISTORY)
        .set(DIARY_HISTORY.USERID,study.getUserId())
        .set(DIARY_HISTORY.SUBJECT, study.getSubject())
        .set(DIARY_HISTORY.SUBJECTDETAIL, study.getSubjectDetail())
        .set(DIARY_HISTORY.STUDYHOUR, study.getStudyHour())
        .set(DIARY_HISTORY.TAG,study.getTag())
        .set(DIARY_HISTORY.INPUT_DATETIME, study.getInputDateTime())
        .execute();
  }

  @Override
  public List<Study> findAll() {

    Result<Record> recordResult = dslContext.select().from(DIARY_HISTORY).fetch();
    return recordResult.map(record -> new Study(
        record.getValue(DIARY_HISTORY.SUBJECT),
        record.getValue(DIARY_HISTORY.STUDYHOUR),
        record.getValue(DIARY_HISTORY.SUBJECTDETAIL),
        record.getValue(DIARY_HISTORY.TAG),
        record.getValue(DIARY_HISTORY.INPUT_DATETIME),
        record.getValue(DIARY_HISTORY.USERID)));
  }

  @Override
  public List<Study> findByUserId(int userId) {
    Result<Record> recordResult = dslContext.select().from(DIARY_HISTORY).where(DIARY_HISTORY.USERID.eq(userId)).fetch();
    return recordResult.map(record -> new Study(
        record.getValue(DIARY_HISTORY.SUBJECT),
        record.getValue(DIARY_HISTORY.STUDYHOUR),
        record.getValue(DIARY_HISTORY.SUBJECTDETAIL),
        record.getValue(DIARY_HISTORY.TAG),
        record.getValue(DIARY_HISTORY.INPUT_DATETIME),
        record.getValue(DIARY_HISTORY.USERID)));
  }

}
