package de.buw.fm4se.modelchecking.exec;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class NuxmvExecutorTest {
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

  @Test
  void testnuXmvWorking() {
    try {
      NuxmvExecutor.runNuxmv(specification);
    } catch (IOException | InterruptedException e) {
      fail(e);
    }
  }

}
