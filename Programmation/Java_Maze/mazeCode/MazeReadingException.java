package mazeCode;

public final class MazeReadingException extends Exception {
	
	
	public MazeReadingException(final String FileName, final int line, final int colonne, final String error) {
		super(FileName + ", " + "ligne : " + line + ", colonne : " + colonne + ", " + error);
		}
	
	public MazeReadingException(final String FileName, final int line, final String error) {
		super(FileName + ", " + "ligne : " + line + ", " + error);
		}
	
	public MazeReadingException(final String error) {
		super(error);
		}
}
