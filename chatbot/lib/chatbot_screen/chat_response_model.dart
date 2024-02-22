class ChatResponse {
  String? answer;
  String? answerEn;
  String? language;
  String? question;
  String? questionEn;

  ChatResponse({this.answer, this.answerEn, this.language, this.question, this.questionEn});

  ChatResponse.fromJson(Map<String, dynamic> json) {
    answer = json['answer'];
    answerEn = json['answer_en'];
    language = json['language'];
    question = json['question'];
    questionEn = json['question_en'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['answer'] = answer;
    data['answer_en'] = answerEn;
    data['language'] = language;
    data['question'] = question;
    data['question_en'] = questionEn;
    return data;
  }
}
