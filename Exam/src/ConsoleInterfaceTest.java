import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleInterfaceTest {

    @Test
    public void testSearchProperties() {
        // Given
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ConsoleInterface consoleInterface = new ConsoleInterface();

        // When
        consoleInterface.searchProperties();

        // Capture the actual output
        String actualOutput = outputStreamCaptor.toString().trim();

        // Debugging: Print the actual output
        System.err.println("Actual Output:");
        System.err.println(actualOutput);

        // Then
        String expectedOutput = "Available properties:\n" +
                "Property ID: 1, Name: Ocean View Apartment, Description: A beautiful apartment with an ocean view., Price per night: $150.0\n" +
                "Property ID: 2, Name: Mountain Cabin, Description: A cozy cabin in the mountains., Price per night: $100.0\n" +
                "Property ID: 3, Name: City Center Studio, Description: A modern studio in the city center., Price per night: $200.0";

        // Normalize line endings to avoid discrepancies
        expectedOutput = expectedOutput.replace("\r\n", "\n");
        actualOutput = actualOutput.replace("\r\n", "\n");

        // Debugging: Print the expected output
        System.err.println("Expected Output:");
        System.err.println(expectedOutput);

        assertEquals(expectedOutput, actualOutput);
    }
}
