/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import stackexchangews.services.SQLException_Exception;

/**
 *
 * @author davidkwan
 */
@WebServlet(name = "UpdateQuestion", urlPatterns = {"/UpdateQuestion"})
public class UpdateQuestion extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String topic = request.getParameter("topic");
        String content = request.getParameter("content");
        
        try {
            editQuestion(questionId, topic, content);
        } catch (SQLException_Exception ex) {
            Logger.getLogger(UpdateQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String token = request.getParameter("token");
        response.sendRedirect("ViewQuestion?id=" + questionId + "&token=" + token);
        
    }
    
    private static int editQuestion(int questionId, String topic, String content) throws SQLException_Exception {
        stackexchangews.services.QuestionHandler_Service service = new stackexchangews.services.QuestionHandler_Service();
        stackexchangews.services.QuestionHandler port = service.getQuestionHandlerPort();
        return port.editQuestion(questionId, topic, content);
    }

}
