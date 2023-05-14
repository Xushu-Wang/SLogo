package Controller.Actions;

import Controller.Controller;
import Controller.Exception.InvalidFileException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class LoadAction implements Action {
  public LoadAction() {
    // Does not do anything
  }
  @Override
  public void execute(Controller controller) {
    File selectedFile = controller.getUserInputFile();
    String path = selectedFile.getPath();
    String filePath = path.substring(path.length() - 6);
    if (! filePath.equals(".slogo")) {
      controller.displayError(String.format("Invalid file type: %s", filePath));
    }
    else {
      try {
        controller.sendCommandsToView(extractTextFromFile(selectedFile));
      }
      catch (InvalidFileException e) {
        controller.displayError("Something went wrong while reading the file.");
      }
    }
  }

  private String extractTextFromFile(File file) throws InvalidFileException {
    try {
      String fileContents = new String(Files.readAllBytes(file.toPath()));
      return fileContents;
    } catch (IOException e) {
      throw new InvalidFileException();
    }
  }
}
