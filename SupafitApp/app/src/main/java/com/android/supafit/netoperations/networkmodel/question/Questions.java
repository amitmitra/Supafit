package com.android.supafit.netoperations.networkmodel.question;

import java.io.Serializable;

public class Questions implements Serializable{
	private long id;
	private long questionTypeId;
	private String question;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}
