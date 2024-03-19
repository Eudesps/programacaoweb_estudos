package ifsertaoresponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

// 1 - ANOTAÇÕES
	 /*Essa é uma implementação de anotações, que permite anotar uma classe de servlet 
	 * essa em especifico é usada para declarar um servlet*/
@WebServlet("/valide")
public class Processa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
// 2- CONTADOR DE ACESSO 
	/*Como o ciclo de vida de um servlet é controlado pelo contâiner que o gerência (nesse caso o Tomcat)
	 * é possível contar a quantidade de acessos que esse página web tem, é o que esse contador está fazendo*/
	private int contador;
	public void iniciar() {
		contador = 0;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Processa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String titulo = "Seus dados";
// 3 - RASTREAMENTO DE SESSÃO 
		/*Esees métodos retornam a data que foi iniciada e a última data que ocorreu a sessão */
		HttpSession sessao = request.getSession(false);
		Date sessaoInicial = new Date(sessao.getCreationTime());
		Date ultimoAcesso = new Date(sessao.getLastAccessedTime());
		int tempoinativo = sessao.getMaxInactiveInterval();
	
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		contador++;
		
		if(sessao.isNew()) {
			titulo = "Bem vindo!! Seus dados";
		}
		
		response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"pt-br\">");
            out.println("<head>");
            out.println("<meta charset=\"ISO-8859-1\">");
            out.println("<title>Dados</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+ titulo + "</h1>");
            out.println("<p><b>Login: </b>" + login + "</p>");
            out.println("<p><b>Senha: </b>" + senha + "</p>");
            out.println("<br>");
            out.println("<p><b>Contador: </b>" + contador + "</p>");
            out.println("<br>");
            out.println("<p><b>Acesso inicial: </b>" + sessaoInicial + "</p>");
            out.println("<p><b>Ultimo acesso: </b>" + ultimoAcesso + "</p>");
            out.println("<p><b>Tempo Inativo: </b>" + tempoinativo + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
