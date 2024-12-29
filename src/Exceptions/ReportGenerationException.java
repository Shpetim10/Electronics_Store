package Exceptions;

public class ReportGenerationException extends Exception {
    public ReportGenerationException() {
        super("Could not generate a report! ");
    }
}
