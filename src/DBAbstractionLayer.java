import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAbstractionLayer {

	private Connection conn;
	private Statement stmt;
	
	/*
	***SQL Inital Setup***
	create table Questions (question_id INT NOT NULL AUTO_INCREMENT, question VARCHAR(264), PRIMARY KEY (question_id));
	create table Answers (answer_id INT NOT NULL AUTO_INCREMENT, question_id_fk INT NOT NULL, answer VARCHAR(264), PRIMARY KEY (answer_id));
	INSERT into Questions (question) VALUES("Have you started on the assignment?");
	INSERT into Questions (question) VALUES("What IDE are you using?");
	INSERT into Answers (question_id_fk,answer) VALUES(1,"Yes");
	INSERT into Answers (question_id_fk,answer) VALUES(2,"Eclipse");
	 */
	
	public DBAbstractionLayer(){
		String url = "jdbc:mysql://kc-sce-appdb01.kc.umkc.edu/agbvg9";
		String userID = "agbvg9";
		String password = "QBAtdq8UbU";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,userID,password);
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getQuestion(int questNum){
		String query = "select question from questions where question_id=" + 
				Integer.toString(questNum);
		try {
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public void addQuestion(String question){
		String update = "insert into questions (question) values (\"" + question + "\")";
		try{
			stmt.executeUpdate(update);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public String[] getAnswers(int questionNum){
		String[] answers = new String[10];
		int count = 0;
		String query = "select * from answers where question_id_fk=" + 
				Integer.toString(questionNum);
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
			 answers[count] = rs.getString(3);
			 count++;
			}
		} catch (SQLException e) {
			return null;
		}
		return answers;
	}
	public void addAnswer(String answer,int question){
		String update = "insert into answers (answer,question_id_fk) values (\"" + answer + "\",\"" 
				+ question + "\")";
		try{
			stmt.executeUpdate(update);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}


