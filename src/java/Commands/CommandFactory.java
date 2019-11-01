/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

/**
 *
 * @author Tom
 */
public class CommandFactory {
    
    private CommandFactory(){
        
    }
    
    public static Command createCommand(String action){
        Command c = null;
        // If there was an action provided, choose which method should run and where the user should go next
        if(action != null){
            switch(action){
                case "login":
                    // Handle where the user wants to login
                    c = new LoginCommand();
                    break;
                case "register":
                    // Handle where the user wants to login
                    c = new RegisterCommand();
                    break;
                // Deal with where the client wants to change language    
                case "changeLanguage":
                    c = new ChangeLanguageCommand();
                    break;
                // Deal with where the client wants to change account details   
                case "updateUser":
                    c = new updateUserCommand();
                    break;
                case "logout":
                    // Do all logic for logging out user
                    c = new LogoutCommand();
                    break;
                    
                case "loanBook":
                    c = new LoanBookCommand();
                    break;
                    
                case "returnBook":
                    c = new ReturnBookCommand();
                    break;
                    
                case "leaveReview":
                    c = new LeaveReviewCommand();
                    break;
                    
                case "searchBook":
                    c = new SearchBookCommand();
                    break;
                    
                default:
                    // Do all logic for incorrect action processing 
                    c = new NoActionSuppliedCommand();
                    break;
            }
        }else{
            c = new NoActionSuppliedCommand();
        }
        
        return c;
    }    
}
