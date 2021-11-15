import java.io.*;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


@WebServlet("/")
public class Home extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println(request.getQueryString());
            String mouseX = request.getParameter("mouseX");
            String mouseY = request.getParameter("mouseY");
            String click = request.getParameter("click");
            String keystrokes = request.getParameter("keystrokes");
            int delay = request.getParameter("delay") == null? 0: Integer.parseInt(request.getParameter("delay"));
            int delayFlux = request.getParameter("delayFlux") == null? 0: Integer.parseInt(request.getParameter("delayFlux"));

            Robot robot = new Robot();
            if (mouseX != null && mouseY != null)
                robot.mouseMove(Integer.parseInt(mouseX), Integer.parseInt(mouseY));
            if (click != null) {
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
            if (keystrokes != null)
                for (char k : keystrokes.toCharArray()){
                    for (int l : type(k)) {
                        sleep(delay, delayFlux);
                        robot.keyPress(l);
                    }
                    for (int l : type(k)) {
                        sleep(delay, delayFlux);
                        robot.keyRelease(l);
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sleep(int delay){
        try{
            TimeUnit.MILLISECONDS.sleep(delay);
        }catch (Exception e){

        }
    }

    public void sleep(int delay, int flux){
        sleep((int) (delay + Math.random()*flux));
    }

    public int[] type(char character) {
        switch (character) {
            case 'a': return new int []{KeyEvent.VK_A};
            case 'b': return new int []{KeyEvent.VK_B};
            case 'c': return new int []{KeyEvent.VK_C};
            case 'd': return new int []{KeyEvent.VK_D};
            case 'e': return new int []{KeyEvent.VK_E};
            case 'f': return new int []{KeyEvent.VK_F};
            case 'g': return new int []{KeyEvent.VK_G};
            case 'h': return new int []{KeyEvent.VK_H};
            case 'i': return new int []{KeyEvent.VK_I};
            case 'j': return new int []{KeyEvent.VK_J};
            case 'k': return new int []{KeyEvent.VK_K};
            case 'l': return new int []{KeyEvent.VK_L};
            case 'm': return new int []{KeyEvent.VK_M};
            case 'n': return new int []{KeyEvent.VK_N};
            case 'o': return new int []{KeyEvent.VK_O};
            case 'p': return new int []{KeyEvent.VK_P};
            case 'q': return new int []{KeyEvent.VK_Q};
            case 'r': return new int []{KeyEvent.VK_R};
            case 's': return new int []{KeyEvent.VK_S};
            case 't': return new int []{KeyEvent.VK_T};
            case 'u': return new int []{KeyEvent.VK_U};
            case 'v': return new int []{KeyEvent.VK_V};
            case 'w': return new int []{KeyEvent.VK_W};
            case 'x': return new int []{KeyEvent.VK_X};
            case 'y': return new int []{KeyEvent.VK_Y};
            case 'z': return new int []{KeyEvent.VK_Z};
            case 'A': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_A};
            case 'B': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_B};
            case 'C': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_C};
            case 'D': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_D};
            case 'E': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_E};
            case 'F': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_F};
            case 'G': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_G};
            case 'H': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_H};
            case 'I': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_I};
            case 'J': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_J};
            case 'K': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_K};
            case 'L': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_L};
            case 'M': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_M};
            case 'N': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_N};
            case 'O': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_O};
            case 'P': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_P};
            case 'Q': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_Q};
            case 'R': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_R};
            case 'S': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_S};
            case 'T': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_T};
            case 'U': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_U};
            case 'V': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_V};
            case 'W': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_W};
            case 'X': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_X};
            case 'Y': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_Y};
            case 'Z': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_Z};
            case '`': return new int []{KeyEvent.VK_BACK_QUOTE};
            case '0': return new int []{KeyEvent.VK_SHIFT,KeyEvent.VK_0};
            case '1': return new int []{KeyEvent.VK_SHIFT,KeyEvent.VK_1};
            case '2': return new int []{KeyEvent.VK_SHIFT,KeyEvent.VK_2};
            case '3': return new int []{KeyEvent.VK_SHIFT,KeyEvent.VK_3};
            case '4': return new int []{KeyEvent.VK_SHIFT,KeyEvent.VK_4};
            case '5': return new int []{KeyEvent.VK_SHIFT,KeyEvent.VK_5};
            case '6': return new int []{KeyEvent.VK_SHIFT,KeyEvent.VK_6};
            case '7': return new int []{KeyEvent.VK_SHIFT,KeyEvent.VK_7};
            case '8': return new int []{KeyEvent.VK_SHIFT,KeyEvent.VK_8};
            case '9': return new int []{KeyEvent.VK_SHIFT,KeyEvent.VK_9};
            case '-': return new int []{KeyEvent.VK_MINUS};
            case '=': return new int []{KeyEvent.VK_EQUALS};
            case '~': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE};
            case '!': return new int []{KeyEvent.VK_EXCLAMATION_MARK};
            case '@': return new int []{KeyEvent.VK_AT};
            case '#': return new int []{KeyEvent.VK_NUMBER_SIGN};
            case '$': return new int []{KeyEvent.VK_DOLLAR};
            case '%': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_5};
            case '^': return new int []{KeyEvent.VK_CIRCUMFLEX};
            case '&': return new int []{KeyEvent.VK_AMPERSAND};
            case '*': return new int []{KeyEvent.VK_ASTERISK};
            case '(': return new int []{KeyEvent.VK_LEFT_PARENTHESIS};
            case ')': return new int []{KeyEvent.VK_RIGHT_PARENTHESIS};
            case '_': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS};
            case '+': return new int []{KeyEvent.VK_PLUS};
            case '\t': return new int []{KeyEvent.VK_TAB};
            case '\n': return new int []{KeyEvent.VK_ENTER};
            case '[': return new int []{KeyEvent.VK_OPEN_BRACKET};
            case ']': return new int []{KeyEvent.VK_CLOSE_BRACKET};
            case '\\': return new int []{KeyEvent.VK_BACK_SLASH};
            case '{': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET};
            case '}': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET};
            case '|': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH};
            case ';': return new int []{KeyEvent.VK_SEMICOLON};
            case ':': return new int []{KeyEvent.VK_COLON};
            case '\'': return new int []{KeyEvent.VK_QUOTE};
            case '"': return new int []{KeyEvent.VK_QUOTEDBL};
            case ',': return new int []{KeyEvent.VK_COMMA};
            case '<': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA};
            case '.': return new int []{KeyEvent.VK_SHIFT,KeyEvent.VK_SEMICOLON};
            case '>': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD};
            case '/': return new int []{KeyEvent.VK_SLASH};
            case '?': return new int []{KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH};
            case ' ': return new int []{KeyEvent.VK_SPACE};
            default:
                throw new IllegalArgumentException("Cannot type character " + character);
        }
    }


}
