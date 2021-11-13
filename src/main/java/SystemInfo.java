import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@WebServlet("/SystemInfo")
public class SystemInfo extends HttpServlet {
    public String systemOut =Math.random() + "\n";
    ConsoleOutputCapturer consoleOutputCapturer = new ConsoleOutputCapturer();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    boolean f = true;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (f)
            consoleOutputCapturer.start();
        f = false;
        systemOut += consoleOutputCapturer.stop();
        request.setAttribute("info", systemOut.replace("\n", "<br>"));
        consoleOutputCapturer.start();

        request.setAttribute("usage", getUsage().replace("\n", "<br>"));

        request.getRequestDispatcher("systemInfo.jsp").forward(request, response);
    }

    private String getUsage() {
        try{
        StringBuilder out = new StringBuilder();
        /* Total number of processors or cores available to the JVM */
        out.append("Available processors (cores): ").append(Runtime.getRuntime().availableProcessors()).append("\n");

        /* Total amount of free memory available to the JVM */
        out.append("Free memory: " + formatFileSize(Runtime.getRuntime().freeMemory())).append("\n");

        /* This will return Long.MAX_VALUE if there is no preset limit */
        long maxMemory = Runtime.getRuntime().maxMemory();
        /* Maximum amount of memory the JVM will attempt to use */
        out.append("Maximum memory: " + formatFileSize((maxMemory == Long.MAX_VALUE ? -1 : maxMemory))).append("\n");

        /* Total memory currently in use by the JVM */
        out.append("Total memory: " + formatFileSize(Runtime.getRuntime().totalMemory())).append("\n");

        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();

        /* For each filesystem root, print some info */
        int i =0;
        for (File root : roots) {
            out.append("------------------------").append("\n");
            out.append("File system root"+i+ ": " + root.getAbsolutePath()).append("\n");
            out.append("Total space: " + formatFileSize(root.getTotalSpace())).append("\n");
            out.append("Free space: " + formatFileSize(root.getFreeSpace())).append("\n");
            out.append("Usable space: " + formatFileSize(root.getUsableSpace())).append("\n");
            i++;
        }
        return out.toString();}
        catch (Exception e){
            e.printStackTrace();System.out.println("<error>"+ Arrays.toString(e.getStackTrace()) +"</error>");
        }
        return "Something went wrong getting system info";
    }

    public static String formatFileSize(long size) {
        String hrSize = null;

        double b = size;
        double k = size/1024.0;
        double m = ((size/1024.0)/1024.0);
        double g = (((size/1024.0)/1024.0)/1024.0);
        double t = ((((size/1024.0)/1024.0)/1024.0)/1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");

        if ( t>1 ) {
            hrSize = dec.format(t).concat(" TB");
        } else if ( g>1 ) {
            hrSize = dec.format(g).concat(" GB");
        } else if ( m>1 ) {
            hrSize = dec.format(m).concat(" MB");
        } else if ( k>1 ) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }

        return hrSize;
    }

    public class ConsoleOutputCapturer {
        private ByteArrayOutputStream baos;
        private PrintStream previous;
        private boolean capturing;

        public void start() {
            if (capturing) {
                return;
            }

            capturing = true;
            previous = System.out;
            baos = new ByteArrayOutputStream();

            OutputStream outputStreamCombiner =
                    new OutputStreamCombiner(Arrays.asList(previous, baos));
            PrintStream custom = new PrintStream(outputStreamCombiner);

            System.setOut(custom);
        }

        public String stop() {
            if (!capturing) {
                return "";
            }

            System.setOut(previous);

            String capturedValue = baos.toString();

            baos = null;
            previous = null;
            capturing = false;

            return capturedValue;
        }

        private class OutputStreamCombiner extends OutputStream {
            private List<OutputStream> outputStreams;

            public OutputStreamCombiner(List<OutputStream> outputStreams) {
                this.outputStreams = outputStreams;
            }

            public void write(int b) throws IOException {
                for (OutputStream os : outputStreams) {
                    os.write(b);
                }
            }

            public void flush() throws IOException {
                for (OutputStream os : outputStreams) {
                    os.flush();
                }
            }

            public void close() throws IOException {
                for (OutputStream os : outputStreams) {
                    os.close();
                }
            }
        }
    }

}