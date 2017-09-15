package com.coderising.dp.decorator;

public class EmailStatementDecorator extends EmailDecorator{
	private String statement;
	
	public EmailStatementDecorator(Email e) {
		super(e);
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}
	
	public String getContent() {
		return this.email.getContent() + this.statement;
	}

}
