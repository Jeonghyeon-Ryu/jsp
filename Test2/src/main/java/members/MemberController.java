package members;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class MemberController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDAO = MemberDAO.getInstance();
		Member member = new Member();
		member.setId(request.getAttribute("id").toString());
		String password = SHA256.encodeSha256(request.getAttribute("pw").toString());
		member.setPw(password);
		String identification = request.getAttribute("birth1").toString()+request.getAttribute("birth2").toString();
		member.setIdentification(Integer.parseInt(identification));
		
		response.getOutputStream().print(member.getId());
	}
}
