import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QuestionServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    
		    DBAbstractionLayer db = new DBAbstractionLayer();
		    
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Question Servlet</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<h1> Questions</h1>");
		    out.println("<form action=\"QuestionServlet\" method=\"GET\">");
		    out.println("<input type=\"text\" name=\"newQuestion\" />");
		    out.println("<input type=\"submit\" value=\"Add Question\" />");

		    int questionCount = 1;
		    String question = db.getQuestion(questionCount);
		    while(question != null){
		    	out.println("<p>" + questionCount + ". <a href=\"AnswerServlet?question=" +
		    			questionCount + "\">" + question + "</a></p>");
		    	questionCount++;
		    	question = db.getQuestion(questionCount);
		    }
		    
		    out.println("</form>");
		    out.println("</body>");
		    out.println("</html>");
		
		    String newQuestion = request.getParameter("newQuestion");
		    if (newQuestion != null && (!newQuestion.equals(db.getQuestion(questionCount-1)))){
		    	db.addQuestion(newQuestion);
		    	question = db.getQuestion(questionCount);
		    	out.println("<p>" + questionCount + ". <a href=\"AnswerServlet?question=" +
		    			questionCount + "\">" + question + "</a></p>");
		    	
		    }
		    newQuestion = null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
