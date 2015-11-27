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
@WebServlet(name = "UpVoteAnswer", urlPatterns = {"/UpVoteAnswer"})
public class UpVoteAnswer extends HttpServlet {


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
        int answerId = Integer.parseInt(request.getParameter("id"));
        
        // Get User ID from token
        int userId = 3;
        
        if(userId>=0){
            try {
                votesUpAnswer(answerId, userId);
            } catch (SQLException_Exception ex) {
                Logger.getLogger(UpVoteQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int questionId = getQuestionId(answerId);
            response.sendRedirect("ViewQuestion?id=" + questionId);
        }
        else
            response.sendRedirect("");
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

    }
    
    private static int votesUpAnswer(int answerId, int answererId) throws SQLException_Exception {
        stackexchangews.services.AnswerHandler_Service service = new stackexchangews.services.AnswerHandler_Service();
        stackexchangews.services.AnswerHandler port = service.getAnswerHandlerPort();
        return port.votesUpAnswer(answerId, answererId);
    }
    
    private static int getQuestionId(int answerId) {
        stackexchangews.services.AnswerHandler_Service service = new stackexchangews.services.AnswerHandler_Service();
        stackexchangews.services.AnswerHandler port = service.getAnswerHandlerPort();
        return port.getQuestionId(answerId);
    }
    
}
