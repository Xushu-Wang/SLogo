public interface History {

  public void add(Command command);
  public Command getLatest();
  public void undoLatest();
}