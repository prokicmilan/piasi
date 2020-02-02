package rs.ac.bg.etf.pm160695.business.testquestionaire.service;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonString;
import javax.json.JsonValue;

import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TQFormField;
import rs.ac.bg.etf.pm160695.business.testquestionaire.entity.TestQuestionFormField;

public class TestQuestionaireService {

	public JsonValue generateQuestionJsonData(List<? extends TQFormField> questions) {
		JsonArrayBuilder questionBuilder = Json.createArrayBuilder();
		for (TQFormField tqff : questions) {
			JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
			jsonObjectBuilder.add("question", tqff.getValue());
			jsonObjectBuilder.add("type", tqff.getInputType().getValue());
			if (tqff instanceof TestQuestionFormField) {
				jsonObjectBuilder.add("correctAnswer", ((TestQuestionFormField) tqff).getCorrectAnswer());
			}

			String answers = tqff.getAnswers();
			// samo da ne bi bio null
			String[] answerArray = new String[0];
			if (answers != null && !answers.isBlank()) {
				answerArray = answers.trim().split(",");
				JsonArrayBuilder answerBuilder = Json.createArrayBuilder();
				for (String answer : answerArray) {
					if (!answer.isBlank()) {
						JsonString value = Json.createValue(answer.trim());
						answerBuilder.add(value);
					}
				}
				jsonObjectBuilder.add("answers", answerBuilder.build());
			}
			questionBuilder.add(jsonObjectBuilder);
		}
		return questionBuilder.build();
	}

}
