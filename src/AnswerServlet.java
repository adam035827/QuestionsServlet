

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnswerServlet
 */
@WebServlet("/AnswerServlet")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    response.setContentType("text/html");
		    int questionNum = Integer.parseInt(request.getParameter("question"));
		    PrintWriter out = response.getWriter();
		
		    DBAbstractionLayer db = new DBAbstractionLayer();
		    
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Answers Servlet</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<h1>Answers</h1>");
		    out.println("<h2>" + db.getQuestion(questionNum) + "</h1>");
		    
		    out.println("<form action=\"AnswerServlet?question=" + questionNum + "\" method=\"GET\">");
		    out.println("<input type=\"text\" name=\"newAnswer\" />");
		    out.println("<input type=\"hidden\" name=\"question\" value="+ questionNum + ">");
		    out.println("<input type=\"submit\" value=\"Add Answer\" />");
		    out.println("</form>");
		    out.println("</body>");
		    out.println("</html>");
		    
		    int answerCount = 1;
		    String[] answers = db.getAnswers(questionNum);
		    
		    while(answers[answerCount-1] != null){
		    	out.println("<p>" + answerCount + ". " + answers[answerCount-1] + "</p>");
		    	answerCount++;
		    }
		
		    String newAnswer = request.getParameter("newAnswer");
		    
		    
		    if (newAnswer != null){
		    	if (answerCount > 1){
		    		if (!newAnswer.equals(answers[answerCount-2])){
		    			db.addAnswer(newAnswer, questionNum);
				    	answers = db.getAnswers(questionNum);
				    	out.println("<p>" + answerCount + ". " + answers[answerCount-1] + "</p>");
		    		}
		    	}
		    	else{
				    	db.addAnswer(newAnswer, questionNum);
				    	answers = db.getAnswers(questionNum);
				    	out.println("<p>" + answerCount + ". " + answers[answerCount-1] + "</p>");
		    	}
		    }
		    newAnswer = null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
