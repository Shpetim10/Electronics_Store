package Exceptions;

public class ReportGenerationException extends RuntimeException {
    public ReportGenerationException() {
        super("Could not generate a report! ");
    }
}
