package com.krivonosovandmarkov;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CsvWriter {

  public static void write(
      final String filename,
      final Calculator calculator,
      final Double from,
      final Double to,
      final Double step)
      throws IOException {
    final Path path = Paths.get(filename);
    final File file = new File(path.toUri());
    if (file.exists()) {
      file.delete();
    }
    file.createNewFile();
    final PrintWriter printWriter = new PrintWriter(file);
    for (Double current = from; current.compareTo(to) <= 0; current += step) {
      printWriter.println(current + "," + calculator.calc(current));
    }
    printWriter.close();
  }

}
