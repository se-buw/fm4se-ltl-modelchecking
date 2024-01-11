package de.buw.fm4se.modelchecking.exec;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Simple utility to execute the Nuxmv executable
 * 
 * @author janri
 *
 */
public class NuxmvExecutor {

  private static String NUXMV_EXE = "";
  private static final String osName = System.getProperty("os.name").toLowerCase();
  
  private static final String IN_FILE = "in.txt";
  private static final String OUT_FILE = "out.txt";
  private static final String ERROR_FILE = "error.txt";

  /**
   * A basic example of how to run Nuxmv
   * @param args
   */
  public static void main(String[] args) {
    String specification = "MODULE main\r\n" + //
        "\r\n" + //
        "VAR\r\n" + //
        "  carsGo : boolean;\r\n" + //
        "  trainComing : boolean;\r\n" + //
        "  trainsGo : boolean;\r\n" + //
        "  \r\n" + //
        "TRANS -- trains signal\r\n" + //
        "  trainComing -> next(trainsGo);\r\n" + //
        "TRANS -- one or the other go\r\n" + //
        "  carsGo = ! trainsGo;  \r\n" + //
        "\r\n" + //
        "LTLSPEC\r\n" + //
        "  G F trainComing -> G F trainsGo;\r\n" + //
        "\r\n" + //
        "LTLSPEC\r\n" + //
        "  G F carsGo;\r\n" + //
        "";
    try {
      System.out.println(runNuxmv(specification));
    } catch (Exception e) {
      System.out.println("Invocation of Nuxmv on the following specification failed: " + specification);
      e.printStackTrace();
    }
  }

  /**
   * 
   * @param spec  a ltl specification in Nuxmv syntax
   * @return output produced by Nuxmv
   * @throws IOException
   * @throws InterruptedException
   * @throws RuntimeException     in case Nuxmv produces any errors
   */
  public static String runNuxmv(String spec) throws IOException, InterruptedException {
    if (osName.contains("win")) {
      NUXMV_EXE = "lib/nuXmv.exe";
    }else if (osName.contains("linux")) {
      NUXMV_EXE = "lib/nuXmv";
    } 
    Files.deleteIfExists(Paths.get(IN_FILE));
    Files.deleteIfExists(Paths.get(OUT_FILE));
    Files.deleteIfExists(Paths.get(ERROR_FILE));

    Files.writeString(Paths.get(IN_FILE), spec);

    ProcessBuilder pb = new ProcessBuilder();
    pb.command(NUXMV_EXE, "-dynamic", IN_FILE);

    // redirect output and error to files
    pb.redirectOutput(Redirect.appendTo(new File(OUT_FILE)));
    pb.redirectError(Redirect.appendTo(new File(ERROR_FILE)));

    Process p = pb.start();
    p.waitFor();

    String errors = Files.readString(Paths.get(ERROR_FILE));
    if (errors.length() != 0) {
      throw new RuntimeException("Nuxmv call produced errors: " + errors);
    }
    return Files.readString(Paths.get(OUT_FILE));

  }
}
